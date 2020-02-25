package jestx;

import java.util.function.*;

/**
 * 
 */
public class Step<P> {
    private P param;

    /**
     * 
     * @param param
     */
    public Step(P param) {
        this.param = param;
    }

    /**
     * 
     * @param <R>
     * @param next
     * @return
     * @throws Exception
     */
    public <R> Step<R> then(Function<P, R> next) {
        R result = next.apply(this.param);
        return new Step<>(result);
    }

    /**
     * 
     * @param next
     * @return
     */
    public Step<P> then(Consumer<P> next) {
        next.accept(this.param);
        return this;
    }

    /**
     * 
     * @return
     */
    public P peek() {
        return this.param;
    }
}