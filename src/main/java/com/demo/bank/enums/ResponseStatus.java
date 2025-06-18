package com.demo.bank.enums;

import lombok.Getter;

public enum ResponseStatus {

    SUCCESS(200, "success"),
    ILLEGAL_PARAM(201, "illegal param"),
    RESOURCE_NOT_FOUND(202, "resource not found"),

    SYSTEM_ERROR(500, "system error");


    @Getter
    private int code;

    @Getter
    private String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
