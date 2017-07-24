package cn.ht.web;

import cn.ht.dto.Exposure;
import cn.ht.dto.SeckillExecution;
import cn.ht.dto.SeckillResult;
import cn.ht.entity.Seckill;
import cn.ht.entity.SuccessKilled;
import cn.ht.enums.SeckillStateEnums;
import cn.ht.exception.RepeatKillException;
import cn.ht.exception.SeckillCloseException;
import cn.ht.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by nunu on 4/17/17.
 */
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    //秒杀列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", seckillService.queryAll());
        return "/seckill/list";
    }

    //获取秒杀商品详情
    @RequestMapping(value = "/{seckillId}/details", method = RequestMethod.GET)
    public String details(@PathVariable("seckillId") Long seckillId, Model model) {

        if (null == seckillId) {
            return "redirect:seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        if (null == seckill) {
            return "forward:seckill/list";
        }
        model.addAttribute("details", seckill);
        return "/seckill/details";
    }

    //获取秒杀地址
    @RequestMapping(value = "/{seckillId}/url", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Exposure> exposure(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposure> result;
        Date killTime = new Date();
        try {
            Exposure exposure = seckillService.exposSeckillUrl(seckillId, killTime);
            result = new SeckillResult<Exposure>(true, exposure);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new SeckillResult<Exposure>(false, e.getMessage());
        }

        return result;
    }

    //开始秒杀
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                @PathVariable("md5") String md5,
                                                @CookieValue(value = "killPhone", required = false) Long phone) {
       SeckillResult<SeckillExecution> result;
        if(null == phone){
            return new SeckillResult<SeckillExecution>(false, "没有注册！");
        }
        try {
            SeckillExecution execution = seckillService.execute(seckillId, phone, md5);
            return result = new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStateEnums.END);
            return result = new SeckillResult<SeckillExecution>(true,execution);
        }catch (RepeatKillException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStateEnums.REPEAT);
            return result = new SeckillResult<SeckillExecution>(true,execution);
        }
        catch (Exception e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStateEnums.INNER_ERROR);
            return result = new SeckillResult<SeckillExecution>(true,execution);
        }
    }

    //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET,
                    produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Long>  time(){
        Date nowTime = new Date();
        return new SeckillResult<Long>(true,nowTime.getTime());
    }
}
