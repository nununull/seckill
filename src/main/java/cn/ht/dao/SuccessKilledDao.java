package cn.ht.dao;

import cn.ht.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nunu on 17-4-13.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，数据库双主键可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId ,@Param("userPhone") long userPhone);

    /**
     * 根据ID查询SuccessKilled 并携带Seckill
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
