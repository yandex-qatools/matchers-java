package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.function.convert.StringConverter;
import org.hamcrest.Description;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.frequency;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 27.05.13
 * Time: 0:30
 */
public final class MismatchHelper {
    // Util class
    private MismatchHelper() {}

    public static <T> StringConverter<Wrapper<T>> asStringWithFind(final List<Wrapper<T>> listToFind) {
        return new StringConverter<Wrapper<T>>() {
            @Override
            public String convert(Wrapper<T> nextExpected) {
                String elementFound = "list has only " + listToFind.size() + " items";
                if (nextExpected.getPosition() < listToFind.size()) {
                    elementFound = listToFind.get(nextExpected.getPosition()).toString();
                }
                return format("Expected %s on position [%d], but was - %s",
                        nextExpected, nextExpected.getPosition(), elementFound);
            }
        };
    }


    public static <T> StringConverter<Wrapper<T>> asStringWithFrequency(
            final Collection<Wrapper<T>> actualList,
            final Collection<Wrapper<T>> expectedList) {

        return new StringConverter<Wrapper<T>>() {
            @Override
            public String convert(Wrapper<T> nextExpected) {
                return format("%s - expected [%d] times, but frequency was - [%d]",
                        nextExpected, frequency(expectedList, nextExpected), frequency(actualList, nextExpected));
            }
        };
    }


    @SuppressWarnings("unchecked")
    public static void appendMismatch(Description mismatchDescription, String comment, Collection collectionToPrint) {
        if (collectionToPrint.size() > 0) {
            mismatchDescription.appendText("\n(").appendText(comment)
                    .appendText(") [")
                    .appendValue(collectionToPrint.size())
                    .appendText("]:\n")
                    .appendValueList("-> ", "\n-> ", "", collectionToPrint);
        }
    }

}
