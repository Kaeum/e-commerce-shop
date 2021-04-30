package com.maeng.shop.sales.exception;

public class CannotCancelException extends RuntimeException{
    public static final String CANNOT_CANCEL_MESSAGE = "cannot cancel - order state is not new";

    public CannotCancelException() {
        super(CANNOT_CANCEL_MESSAGE);
    }

    public CannotCancelException(Throwable cause) {
        super(CANNOT_CANCEL_MESSAGE, cause);
    }
}
