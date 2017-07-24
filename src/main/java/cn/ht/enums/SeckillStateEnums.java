package cn.ht.enums;

/**
 * Created by nunu on 4/14/17.
 */
public enum SeckillStateEnums {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");


    private int state;

    private String stateInfo;

    SeckillStateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static String stateOf(int index) {
        for (SeckillStateEnums state : values()) {
            if (state.getState() == index) {
                return state.getStateInfo();
            }
        }
        return null;
    }
}
