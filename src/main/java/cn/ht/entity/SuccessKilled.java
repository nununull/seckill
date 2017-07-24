package cn.ht.entity;

/**
 * Created by nunu on 17-4-13.
 */
public class SuccessKilled {

    private long seckillId;

    private long userPhone;

    private short state;

    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "\n用户电话号码：" + getUserPhone()
                + ",\n 秒杀状态：" + getState()
                + ",\n" +getSeckill().toString();
    }
}
