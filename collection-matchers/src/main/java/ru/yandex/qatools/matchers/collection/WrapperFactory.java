package ru.yandex.qatools.matchers.collection;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 28.05.13
 * Time: 13:22
 */
public interface WrapperFactory<T> {
    public Wrapper<T> newWrapper();
}
