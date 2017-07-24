package cn.ht.dto;

import java.util.Date;

/**
 * Created by nunu on 4/14/17.
 */
public class Exposure {

    private long seckillId;

    private boolean exposed;

    private Date nowTime;

    private Date startTime;

    private Date endTime;

    private String md5;

    public Exposure(long seckillId, boolean exposed, String md5) {
        this.seckillId = seckillId;
        this.exposed = exposed;
        this.md5 = md5;
    }

    public Exposure(long seckillId, boolean exposed, Date nowTime, Date startTime, Date endTime) {
        this.seckillId = seckillId;
        this.exposed = exposed;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposure(boolean exposed, Date nowTime, Date startTime, Date endTime) {
        this.exposed = exposed;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposure(long seckillId, boolean exposed) {
        this.seckillId = seckillId;
        this.exposed = exposed;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "商品ID：" + getSeckillId()
                + ",是否开启：" + isExposed()
                + ",现在时间：" + getNowTime()
                + ",开启时间：" + getStartTime()
                + ",结束时间：" + getEndTime()
                + "md5是：" + getMd5();
    }
}
