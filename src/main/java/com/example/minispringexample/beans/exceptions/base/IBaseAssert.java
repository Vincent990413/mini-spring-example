package com.example.minispringexample.beans.exceptions.base;

/**
 * 该接口仿造 Java 中的 Assert类 对于出现的
 * NotNull NotEmpty 等等情况进行代码复现
 *
 * @author vincent
 * @since 2022-03-17
 */
public interface IBaseAssert {

    BaseException newException();

    BaseException newException(Object args);

    BaseException newException(Throwable t, Object args);

    /**
     * 默认方法 将不会影响到其他已经出现的子类
     * 因为这个方法不需要任何子类去实现
     * <p>
     * 注意到 if 语句块正是Assert类的源码复现
     */
    default void assertNotNull(Object ob) {
        if (ob == null) {
            throw newException(); //抛出异常子类
        }
    }

    /**
     * 当出现自定义消息结果时 <code>BaseException</code>的构造函数将会被触发
     *
     * @param ob   异常的枚举模板
     * @param args 异常的消息结果
     */
    default void assertNotNull(Object ob, Object args) {
        if (ob == null) {
            throw newException(args);
        }
    }
}
