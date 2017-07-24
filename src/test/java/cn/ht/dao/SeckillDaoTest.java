package cn.ht.dao;

import cn.ht.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by nunu on 17-4-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNuber() throws Exception {
        long seckill_id = 1000L;
        Date killTime = new Date();
        int i = seckillDao.reduceNuber(seckill_id,killTime);
        System.out.println("减库存结果：" + i);
    }

    @Test
    public void queryById() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillDao.queryById(seckillId);
        System.out.println(seckill.toString());
    }

    @Test
    public void queryAll() throws Exception {
        int offset = 0;
        int limit = 10;
        ArrayList<Seckill> seckills = seckillDao.queryAll(offset,limit);
        for (Seckill seckill:seckills) {
            System.out.println(seckill.toString());
        }
    }

}