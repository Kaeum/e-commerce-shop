package com.maeng.shop.common;

public class CommonResponse<T> {
    public static final String COMMON_RETURN_CODE_5000 = "5000";

    private String returnCode;
    private String returnMessage;
    private T returnData;

    public static class Builder<T> {
        private String returnCode;
        private String returnMessage;
        private T returnData;

        public Builder<T> returnCode(String returnCode) {
            this.returnCode = returnCode;
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
        this.returnCode = builder.returnCode;
        this.returnMessage = builder.returnMessage;
        this.returnData = (T) builder.returnData;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public T getReturnData() {
        return returnData;
    }
}
