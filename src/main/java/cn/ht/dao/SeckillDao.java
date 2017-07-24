package cn.ht.dao;

import cn.ht.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nunu on 17-4-13.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId 商品ID
     * @param killTime 秒杀的时间（用来跟数据库时间做对比）
     * @return
     */
    int reduceNuber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据商品ID查询商品信息
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询所有
     * @param offset
     * @param limit
     * @return
     */
    ArrayList<Seckill> queryAll(@Param("offset") int offset ,@Param("limit") int limit);
}
