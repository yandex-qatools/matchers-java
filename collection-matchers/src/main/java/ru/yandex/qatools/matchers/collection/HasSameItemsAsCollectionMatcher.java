package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.collection.LambdaCollection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Collection;

import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.isIn;
import static ru.yandex.qatools.matchers.collection.MismatchHelper.appendMismatch;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 09.05.13
 * Time: 14:21
 */
public class HasSameItemsAsCollectionMatcher<T> extends TypeSafeDiagnosingMatcher<Collection<? extends T>> {

    private Collection<? extends T> expect;
    @SuppressWarnings("unchecked")
    private WrapperFactory<T> wrapperFactory = (WrapperFactory<T>) ObjectMethodsWrapper.standartMethods();


    public HasSameItemsAsCollectionMatcher(Collection<? extends T> expected) {
        this.expect = expected;
    }


    @Override
    protected boolean matchesSafely(Collection<? extends T> actual, Description mismatchDescription) {
        LambdaCollection<Wrapper<T>> wrappedActual = with(actual).convert(WrapperConverter.wrapWith(wrapperFactory));
        LambdaCollection<Wrapper<T>> wrappedExpected = with(expect).convert(WrapperConverter.wrapWith(wrapperFactory));

        LambdaCollection<Wrapper<T>> inActualButNotInExpected = wrappedActual.clone().remove(isIn(wrappedExpected));
        LambdaCollection<Wrapper<T>> inExpectedButNotInActual = wrappedExpected.clone().remove(isIn(wrappedActual));


        appendMismatch(mismatchDescription, "In Actual but not in Expected", inActualButNotInExpected);
        appendMismatch(mismatchDescription, "In Expected but not in Actual", inExpectedButNotInActual);
        return inActualButNotInExpected.size() + inExpectedButNotInActual.size() == 0;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("collections contains same items");
    }


    @Factory
    public static <T> Matcher<Collection<? extends T>> hasSameItemsAsCollection(Collection<? extends T> correctList) {
        return new HasSameItemsAsCollectionMatcher<T>(correctList);
    }

}

