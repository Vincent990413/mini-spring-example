package com.example.minispringexample.beans.exceptions.base;


import com.example.minispringexample.exceptions.enumerate.IResponseEnum;

/**
 * 业务型异常类 由<code>BaseException</code>继承而来
 *
 * @author vincent
 * @since 2022-03-17
 */
public class BusinessException extends BaseException {
    //避免对象在序列化过程中 出现版本不一致的异常
    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum resp) {
        super(resp, null);
    }

    public BusinessException(IResponseEnum resp, Object ob) {
        super(resp, ob);
    }
}
