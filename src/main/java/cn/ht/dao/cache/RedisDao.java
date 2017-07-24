package cn.ht.dao.cache;

import cn.ht.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by nunu on 17-5-26.
 */
public class RedisDao {

    private JedisPool jedisPool;

    public RedisDao(String host,int port){
        jedisPool = new JedisPool(host,port);
    }

    public String setSeckill(){

        return null;
    }

    public Seckill putSeckill(Seckill seckill){

        return null;
    }
}
