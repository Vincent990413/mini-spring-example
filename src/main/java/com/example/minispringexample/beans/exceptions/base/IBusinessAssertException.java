package com.example.minispringexample.beans.exceptions.base;


import com.example.minispringexample.beans.exceptions.enumerate.IResponseEnum;

/**
 * <code>BusinessAssertException</code> 是最关键的接口：
 * 这个接口：
 *
 * <p>
 * 1. 具备<code>IResponseEnum</code>的异常枚举实例
 * -> 【用户不存在，10001】 【用户已存在，10002】等等...
 * 2. 同时具备IBaseAssert接口的 assertNotNull 等方法来真正地代码复现
 * </p>
 *
 * @author vincent
 * @since 2022-03-17
 **/
public interface IBusinessAssertException extends IResponseEnum, IBaseBeansAssert {

    @Override
    default BaseException newException() {
        return newException(null, null); //这里调用 IBaseAssert 接口中的默认方法
    }

    @Override
    default BaseException newException(Object args) {
        return newException(null, args);
    }

    @Override
    default BaseException newException(Throwable t, Object args) {
        return new BusinessException(this, args);
    }
}
