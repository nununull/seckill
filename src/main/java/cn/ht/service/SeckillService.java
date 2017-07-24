package cn.ht.service;

import cn.ht.dto.Exposure;
import cn.ht.dto.SeckillExecution;
import cn.ht.entity.Seckill;
import cn.ht.entity.SuccessKilled;
import cn.ht.exception.RepeatKillException;
import cn.ht.exception.SeckillCloseException;
import cn.ht.exception.SeckillException;

import java.util.Date;
import java.util.List;

/**
 * Created by nunu on 4/14/17.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀商品
     * @return
     */
    List<Seckill> queryAll();

    /**
     * 根据ID查询秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 暴露秒杀地址
     * @param seckillid
     * @param killTime
     * @return
     */
    Exposure exposSeckillUrl(long seckillid, Date killTime);

    /**
     * 开始秒杀
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillExecution execute(long seckillId, long userPhone, String md5)
    throws SeckillException,RepeatKillException,SeckillCloseException;
}
