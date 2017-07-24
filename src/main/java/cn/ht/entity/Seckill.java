package cn.ht.entity;

import java.util.Date;

/**
 * Created by nunu on 17-4-13.
 */
public class Seckill {

    private long seckillId;

    private String seckillName;

    private int seckillNumber;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getSeckillName() {
        return seckillName;
    }

    public void setSeckillName(String seckillName) {
        this.seckillName = seckillName;
    }

    public int getSeckillNumber() {
        return seckillNumber;
    }

    public void setSeckillNumber(int seckillNumber) {
        this.seckillNumber = seckillNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {

        return  "商品ID：" + getSeckillId()
                + "\n商品名称：" + getSeckillName()
                + ",\n库存量：" + getSeckillNumber()
                + "\n创建时间：" + getCreateTime()
                + ",\n秒杀开始时间：" + getStartTime()
                + ",秒杀结束时间：" + getEndTime();
    }
}
