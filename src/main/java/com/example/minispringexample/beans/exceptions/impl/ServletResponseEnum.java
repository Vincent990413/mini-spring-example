package com.example.minispringexample.exceptions.impl;

import com.example.minispringexample.beans.exceptions.base.IBusinessAssertException;
import lombok.Getter;
import lombok.Setter;

/**
 * 该类是最终的枚举实现类 其中也可以调用
 * 其父接口中实现的接口 IBaseAssert 中的相关断言方法（重点）
 *
 * <p>
 * 每当有新的定制化异常实例时 在此添加即可 不必利用以前的类接口
 * 去创建新的子类 MyException extends BaseException...等等
 * </p>
 *
 * @author vincent
 * @since 2022-03-17
 */
public enum ServletResponseEnum implements IBusinessAssertException {
    BAD_REQUEST(400, "服务器拒接请求"),
    UNAUTHORIZED(401, "未授权，请输入相应口令"),
    FORBIDDEN(403, "禁止访问所请求的页面"),
    NOT_FOUND(404, "服务器无法找到所请求的资源"),
    METHOD_NOT_ALLOWED(405, "请求中的方法是不允许的"),
    REQUEST_TIMEOUT(408, "请求超时"),
    UNSUPPORTED_MEDIA_TYPE(415, "媒体类型不支持"),
    INTERNAL_SERVER_ERROR(500, "服务器遇到未知错误"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    ;

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String message;

    ServletResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
