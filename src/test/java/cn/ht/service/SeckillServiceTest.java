package cn.ht.service;

import cn.ht.dto.Exposure;
import cn.ht.dto.SeckillExecution;
import cn.ht.entity.Seckill;
import com.mysql.cj.core.log.Slf4JLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nunu on 4/17/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml","classpath:/spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillService.queryAll();
        logger.info("list={}",seckills);

    }

    @Test
    public void queryById() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillService.queryById(seckillId);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exposSeckillUrl() throws Exception {

        //md5是：4774388ed9b82ee0408fd1e8ba429981

        long seckillId = 1000L;
        Date nowTime = new Date();
        Exposure exposure = seckillService.exposSeckillUrl(seckillId, nowTime);
        logger.info("exposure={}",exposure);
    }

    @Test
    public void execute() throws Exception {
        String md5 = "4774388ed9b82ee0408fd1e8ba429981";
        long seckillId = 1000L;
        long userPhone = 18673851517L;
        SeckillExecution execution = seckillService.execute(seckillId, userPhone, md5);
        logger.info("execution={}",execution);
    }

}