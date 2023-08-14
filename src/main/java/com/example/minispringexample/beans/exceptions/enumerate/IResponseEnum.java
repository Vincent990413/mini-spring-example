package com.example.minispringexample.exceptions.enumerate;

/**
 * 响应信息的枚举值模板接口
 * 其中有返回错误码与错误消息的方法交给其子类实现
 *
 * @author vincent
 * @since 2022-03-17
 */
public interface IResponseEnum {
    //返回错误码
    int getCode();

    //返回错误消息
    String getMessage();
}
