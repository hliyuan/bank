package com.demo.bank.dto;

import java.io.Serializable;

public class WebResponseVo<T> implements Serializable {

    private T data;

    private ErrorResponseData error;

    public static <T> WebResponseVo<T> buildData(T data) {
        WebResponseVo<T> response = new WebResponseVo<>();
        response.setData(data);
         return response;
    }

    public static <T> WebResponseVo<T> buildError(int code, String msg) {
        WebResponseVo<T> responseVo = new WebResponseVo<>();
        ErrorResponseData errorResponseData = new ErrorResponseData(code, msg);
        responseVo.setError(errorResponseData);
        return responseVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResponseData getError() {
        return error;
    }

    public void setError(ErrorResponseData error) {
        this.error = error;
    }
}
