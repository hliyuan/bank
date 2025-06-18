package com.demo.bank.exception;

public class ResourceNotFoundException extends RuntimeException {

    // 构造方法：接收错误消息
    public ResourceNotFoundException(String message) {
        super(message); // 传递给父类 RuntimeException
    }

    // 可选：扩展构造方法（例如带ID和资源类型）
    public ResourceNotFoundException(String resourceName, String id) {
        super(String.format("%s not found with id: %s", resourceName, id));
    }
}
