package com.example.minispringexample.beans.exceptions.base;

import com.example.minispringexample.beans.exceptions.enumerate.IResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 自定义的基本异常 属于运行时异常
 * 可以通过继承这个 <code>BaseException </code>来通知定制化的业务异常
 *
 * @author vincent
 * @since 2022-03-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {

    //错误码
    private int errorCode;

    //错误消息
    private String errorMessage;

    //错误结果体
    private Object errorResult;

    /**
     * 调用 RuntimeException的构造函数 并指定这个运行时异常的错误消息
     *
     * @param resp 异常枚举模板接口
     * @param ob   异常的消息结果
     */
    public BaseException(IResponseEnum resp, Object ob) {
        super(resp.getMessage());

        this.errorCode = resp.getCode();
        this.errorMessage = resp.getMessage();
        this.errorResult = ob;
    }
}
