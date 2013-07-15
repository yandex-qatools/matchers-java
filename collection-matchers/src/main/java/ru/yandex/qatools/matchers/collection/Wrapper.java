package ru.yandex.qatools.matchers.collection;

import static org.hamcrest.Matchers.instanceOf;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 24.05.13
 * Time: 16:13
 */
public abstract class Wrapper<T>{
    protected int position;
    protected T wrapped;

    public Wrapper<T> wrap(T wrapped) {
        this.wrapped = wrapped;
        return this;
    }

    public T getWrapped() {
        return wrapped;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        Wrapper<T> comparedWrapper = (Wrapper<T>) obj;
        T toCompare = comparedWrapper.getWrapped();

        if (wrapped == null && toCompare == null) {
            return true;
        }

        if (!(wrapped != null && instanceOf(wrapped.getClass()).matches(toCompare))) {
            return false;
        }

        return safelyEquals(this.wrapped, toCompare);
    }

    @Override
    public String toString() {
        return asString(wrapped);
    }


    public abstract boolean safelyEquals(T actual, T expected);

    public abstract String asString(T obj);
}
