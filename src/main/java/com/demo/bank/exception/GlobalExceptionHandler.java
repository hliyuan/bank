package com.demo.bank.exception;

import com.demo.bank.dto.ErrorResponseData;
import com.demo.bank.dto.WebResponseVo;
import com.demo.bank.enums.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 资源未找到的异常处理器
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public WebResponseVo<ErrorResponseData> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        return WebResponseVo.buildError(ResponseStatus.RESOURCE_NOT_FOUND.getCode(), ex.getMessage());
    }

    /**
     * 参数不合法异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(IllegalParamException.class)
    public WebResponseVo<ErrorResponseData> handleIllegalParamException(
            IllegalParamException ex) {
        return WebResponseVo.buildError(ResponseStatus.ILLEGAL_PARAM.getCode(), ex.getMessage());
    }

    /**
     * 其他异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public WebResponseVo<ErrorResponseData> handleGlobalException(
            Exception ex) {
        return WebResponseVo.buildError(ResponseStatus.SYSTEM_ERROR.getCode(), ex.getMessage());
    }
}
