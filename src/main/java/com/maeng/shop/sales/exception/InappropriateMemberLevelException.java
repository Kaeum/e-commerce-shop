package com.maeng.shop.sales.exception;

public class InappropriateMemberLevelException extends RuntimeException{
    public static final String MEMBER_LEVEL_NOT_MATCHES = "member level not matches - check member level";

    public InappropriateMemberLevelException() {
        super(MEMBER_LEVEL_NOT_MATCHES);
    }

    public InappropriateMemberLevelException(Throwable cause) {
        super(MEMBER_LEVEL_NOT_MATCHES, cause);
    }

}
