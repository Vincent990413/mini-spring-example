package com.example.minispringexample.exceptions.impl;

import com.example.minispringexample.beans.exceptions.base.IBusinessAssertException;

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
public enum BasicBeansExceptionImpl implements IBusinessAssertException {

    BEANS_INSTANTIATION_EXCEPTION(20001, "Bean 对象的实例化发生异常"),
    BEANS_ILLEGAL_ACCESS_EXCEPTION(20002, "Bean 对象发生非法获取异常"),
    BEANS_VALUE_NULL_EXCEPTION(20003, "Bean 对象为空"),
            ;


    private int code;

    private String message;

    BasicBeansExceptionImpl(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
