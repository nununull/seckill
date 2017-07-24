package cn.ht.exception;

/**
 * Created by nunu on 4/14/17.
 */
public class RepeatKillException extends  SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
