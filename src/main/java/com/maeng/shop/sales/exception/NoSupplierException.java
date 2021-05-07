package com.maeng.shop.sales.exception;

public class NoSupplierException extends RuntimeException{
    public static final String CANNOT_FIND_SUPPLIER = "no supplier found";

    public NoSupplierException() {
        super(CANNOT_FIND_SUPPLIER);
    }

    public NoSupplierException(Throwable cause) {
        super(CANNOT_FIND_SUPPLIER, cause);
    }
}
