package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.collection.LambdaCollection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Collection;

import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.isIn;

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


    @SuppressWarnings("unchecked")
    private void appendMismatch(Description mismatchDescription, String comment, Collection listToPrint) {
        if (listToPrint.size() > 0) {
            mismatchDescription.appendText("\n(").appendText(comment)
                    .appendText(") [")
                    .appendValue(listToPrint.size())
                    .appendText("]:\n")
                    .appendValueList("-> ", "\n-> ", "", listToPrint);
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("collection same as expected");
    }


    @Factory
    public static <T> Matcher<Collection<? extends T>> hasSameItemsAsCollection(Collection<? extends T> correctList) {
        return new HasSameItemsAsCollectionMatcher<T>(correctList);
    }

}

