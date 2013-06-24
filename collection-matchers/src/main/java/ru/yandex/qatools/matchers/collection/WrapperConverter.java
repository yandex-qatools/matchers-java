package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.function.convert.Converter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 25.05.13
 * Time: 21:48
 */
public class WrapperConverter<T> implements Converter<T, Wrapper<T>> {
    private List<? extends T> list;

    private WrapperFactory<T> wrapperFactory;

    public WrapperConverter(List<? extends T> list, WrapperFactory<T> wrapperFactory) {
        this.list = list;
        this.wrapperFactory = wrapperFactory;
    }

    public WrapperConverter(WrapperFactory<T> wrapperFactory) {
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public Wrapper<T> convert(T from) {
        Wrapper<T> wrapper = wrapperFactory.newWrapper();

        if(wrapper == null) {
            throw new IllegalStateException("Factory can't produce null wrapper");
        }

        wrapper.wrap(from);

        if (null != list) {
            wrapper.setPosition(list.indexOf(from));
        }

        return wrapper;
    }

    public static <T> WrapperConverter<T> wrap(List<? extends T> list, WrapperFactory<T> factory) {
        return new WrapperConverter<T>(list, factory);
    }

    public static <T> WrapperConverter<T> wrapWith(WrapperFactory<T> factory) {
        return new WrapperConverter<T>(factory);
    }

}
