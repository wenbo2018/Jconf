package com.github.wenbo2018.jconf.client.exception;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class JconfException extends RuntimeException {
    public JconfException() {
        super();
    }

    public JconfException(String message) {
        super(message);
    }

    public JconfException(String message, Throwable cause) {
        super(message, cause);
    }

    public JconfException(Throwable cause) {
        super(cause);
    }

    protected JconfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
