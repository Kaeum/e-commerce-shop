package com.maeng.shop.common;

public class CommonResponse<T> {
    public static final String SUCCESS_OR_NOT_Y = "Y";
    public static final String SUCCESS_OR_NOT_N = "N";
    public static final String SUCCESS_MESSAGE = "Request has succeeded";

    private String successOrNot;
    private String returnMessage;
    private T returnData;

    public static class Builder<T> {
        private String successOrNot;
        private String returnMessage;
        private T returnData;

        public Builder<T> successOrNot(String returnCode) {
            this.successOrNot = returnCode;
            return this;
        }

        public Builder<T> returnMessage(String returnMessage) {
            this.returnMessage = returnMessage;
            return this;
        }

        public Builder<T> returnData(T returnData) {
            this.returnData = returnData;
            return this;
        }

        public CommonResponse build() {
            return new CommonResponse(this);
        }

        public static <T> Builder<T> builder() {
            return new Builder<>();
        }
    }

    private CommonResponse() {}

    private CommonResponse(Builder builder) {
        this.successOrNot = builder.successOrNot;
        this.returnMessage = builder.returnMessage;
        this.returnData = (T) builder.returnData;
    }

    public static CommonResponse onSuccess() {
        return new Builder<Void>()
                .successOrNot(SUCCESS_OR_NOT_Y)
                .returnMessage(SUCCESS_MESSAGE)
                .build();
    }

    public static <T> CommonResponse onSuccess(T returnData) {
        return new Builder<T>()
                .successOrNot(SUCCESS_OR_NOT_Y)
                .returnMessage(SUCCESS_MESSAGE)
                .returnData(returnData)
                .build();
    }

    public static CommonResponse onFailure(RuntimeException ex) {
        return new Builder<Void>()
                .successOrNot(SUCCESS_OR_NOT_N)
                .returnMessage(ex.getMessage())
                .build();
    }

    public String getSuccessOrNot() {
        return successOrNot;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public T getReturnData() {
        return returnData;
    }
}
