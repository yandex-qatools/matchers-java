package ru.yandex.qatools.matchers.collection;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 24.05.13
 * Time: 19:16
 */
public class ObjectMethodsWrapper extends Wrapper<Object> implements WrapperFactory<Object> {
    @Override
    public boolean safelyEquals(Object actual, Object expected) {
        return actual.equals(expected);
    }

    @Override
    public Wrapper<Object> newWrapper() {
        return new ObjectMethodsWrapper();
    }

    @Override
    public String asString(Object obj) {
        return obj.toString();
    }

    public static WrapperFactory<Object> standardMethods() {
        return new ObjectMethodsWrapper();
    }
}
