package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.collection.LambdaCollection;
import org.hamcrest.*;

import java.util.Collection;

import static ch.lambdaj.collection.LambdaCollections.with;
import static java.util.Collections.frequency;
import static org.hamcrest.Matchers.is;
import static ru.yandex.qatools.matchers.collection.MismatchHelper.appendMismatch;
import static ru.yandex.qatools.matchers.collection.MismatchHelper.asStringWithFrequency;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 09.05.13
 * Time: 14:21
 */
public class HasSameItemsAsCollectionMatcher<T> extends TypeSafeDiagnosingMatcher<Collection<? extends T>> {

    private Collection<? extends T> expect;
    @SuppressWarnings("unchecked")
    private WrapperFactory<T> wrapperFactory = (WrapperFactory<T>) ObjectMethodsWrapper.standardMethods();


    public HasSameItemsAsCollectionMatcher(Collection<? extends T> expected) {
        this.expect = expected;
    }


    @Override
    protected boolean matchesSafely(Collection<? extends T> actual, Description mismatchDescription) {
        LambdaCollection<Wrapper<T>> wrappedActual = with(actual).convert(WrapperConverter.wrapWith(wrapperFactory));
        LambdaCollection<Wrapper<T>> wrappedExpected = with(expect).convert(WrapperConverter.wrapWith(wrapperFactory));

        LambdaCollection<Wrapper<T>> frequencyNotEqual = wrappedExpected.clone()
                .remove(occurrCountEqual(wrappedActual, wrappedExpected));
        frequencyNotEqual.addAll(wrappedActual.clone().remove(occurrCountEqual(wrappedActual, wrappedExpected)));

        appendMismatch(mismatchDescription, "Not equal frequency",
                frequencyNotEqual.distinct().convert(asStringWithFrequency(wrappedActual, wrappedExpected)));
        return frequencyNotEqual.isEmpty();
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("collections contains same items");
    }


    @Factory
    public static <T> Matcher<Collection<? extends T>> hasSameItemsAsCollection(Collection<? extends T> correctList) {
        return new HasSameItemsAsCollectionMatcher<>(correctList);
    }


    private <T> Matcher<Wrapper<T>> occurrCountEqual(final Collection<Wrapper<T>> actualList,
                                                           final Collection<Wrapper<T>> expectedList) {
        return new FeatureMatcher<Wrapper<T>, Boolean>(is(true), "", "") {
            @Override
            protected Boolean featureValueOf(Wrapper<T> actual) {
                return frequency(expectedList, actual) == frequency(actualList, actual);
            }
        };
    }


}

