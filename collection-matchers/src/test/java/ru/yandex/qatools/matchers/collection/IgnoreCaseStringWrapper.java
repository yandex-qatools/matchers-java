package ru.yandex.qatools.matchers.collection;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 28.05.13
 * Time: 13:10
 */
public class IgnoreCaseStringWrapper extends Wrapper<String> implements WrapperFactory<String> {
    @Override
    public boolean safelyEquals(String actual, String expected) {
        return actual.toLowerCase().equals(expected.toLowerCase());
    }

    @Override
    public String asString(String obj) {
        return obj;
    }

    @Override
    public Wrapper<String> newWrapper() {
        return new IgnoreCaseStringWrapper();
    }

    public static WrapperFactory<String> ignoringCase() {
        return new IgnoreCaseStringWrapper();
    }
}
