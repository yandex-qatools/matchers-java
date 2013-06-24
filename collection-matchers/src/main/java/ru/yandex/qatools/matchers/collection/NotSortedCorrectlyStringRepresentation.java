package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.function.convert.StringConverter;

import java.util.List;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 27.05.13
 * Time: 0:30
 */
public class NotSortedCorrectlyStringRepresentation<T> implements StringConverter<Wrapper<T>> {
    private List<Wrapper<T>> listToFind;

    public NotSortedCorrectlyStringRepresentation(List<Wrapper<T>> listToFind) {
        this.listToFind = listToFind;
    }


    public static <T> StringConverter<Wrapper<T>> asStringWithFind(List<Wrapper<T>> listToFind) {
        return new NotSortedCorrectlyStringRepresentation<T>(listToFind);
    }

    @Override
    public String convert(Wrapper nextExpected) {
        String elementFound = "list has only " + listToFind.size() + " items";
        if (nextExpected.getPosition() < listToFind.size()) {
            elementFound = listToFind.get(nextExpected.getPosition()).toString();
        }
        return format("Expected %s on position [%d], but was - %s",
                nextExpected, nextExpected.getPosition(), elementFound);
    }
}
