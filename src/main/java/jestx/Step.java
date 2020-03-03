package jestx;

import java.util.function.*;

/**
 * 链式
 * 
 */
public class Step<P> {
    private P param;

    /**
     * 构造子
     * 
     * @param param 传递的参数
     */
    public Step(P param) {
        this.param = param;
    }

    /**
     * 调用。
     * 
     * @param <R> 返回类型
     * @param next 回调
     * @return 下一步
     * @throws Exception
     */
    public <R> Step<R> then(Function<P, R> next) {
        R result = next.apply(this.param);
        return new Step<>(result);
    }

    /**
     * 无返回值调用。
     * 
     * @param next 下一步
     * @return 本身
     */
    public Step<P> then(Consumer<P> next) {
        next.accept(this.param);
        return this;
    }

    /**
     * 查看参数
     * 
     * @return 参数
     */
    public P peek() {
        return this.param;
    }
}