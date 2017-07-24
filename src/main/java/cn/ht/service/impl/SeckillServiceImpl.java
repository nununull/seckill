package cn.ht.service.impl;

import cn.ht.dao.SeckillDao;
import cn.ht.dao.SuccessKilledDao;
import cn.ht.dto.Exposure;
import cn.ht.dto.SeckillExecution;
import cn.ht.entity.Seckill;
import cn.ht.entity.SuccessKilled;
import cn.ht.enums.SeckillStateEnums;
import cn.ht.exception.RepeatKillException;
import cn.ht.exception.SeckillCloseException;
import cn.ht.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by nunu on 4/14/17.
 */
@Service
public class SeckillServiceImpl implements cn.ht.service.SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String salt = "../';'][++%^%$#@$&*)(*()&^*%^%&$^%#%^#$^#";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Seckill> queryAll() {
        return seckillDao.queryAll(0, 10);
    }

    public Seckill queryById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposure exposSeckillUrl(long seckillid, Date killTime) {
        Seckill seckill = seckillDao.queryById(seckillid);
        Date nowTime = new Date();
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposure(false, nowTime, startTime, endTime);
        }

        String md5 = getMD5(seckillid);
        return new Exposure(seckillid, true, md5);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SeckillExecution execute(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillCloseException("数据篡改！");
        }
        Date now = new Date();
        try {
            int updateNum = seckillDao.reduceNuber(seckillId, now);
            if (updateNum <= 0) {
                throw new SeckillCloseException("秒杀已结束");
            } else {
                int insertNum = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertNum <= 0) {
                    throw new RepeatKillException("重复秒杀");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnums.SUCCESS,successKilled);
                }
            }
        } catch (SeckillCloseException e) {
            logger.error("seckill closed",e);
            throw new SeckillCloseException(e.getMessage());
        } catch (RepeatKillException e) {
            logger.error("data rewrite",e);
            throw new RepeatKillException(e.getMessage());
        } catch (Exception e) {
            logger.error("data inner error",e);
            throw new SeckillException("data inner error" + e.getMessage());
        }
    }
}
