package ru.yandex.qatools.matchers.collection;

import ch.lambdaj.collection.LambdaList;
import org.hamcrest.*;

import java.util.List;

import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.*;
import static ru.yandex.qatools.matchers.collection.MismatchHelper.appendMismatch;
import static ru.yandex.qatools.matchers.collection.WrapperConverter.wrap;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 25.05.13
 * Time: 21:35
 */
public class HasSameItemsAsListMatcher<T> extends TypeSafeDiagnosingMatcher<List<? extends T>> {
    private List<? extends T> expected;
    private WrapperFactory<T> wrapperFactory = (WrapperFactory<T>) ObjectMethodsWrapper.standardMethods();

    private boolean sortcheck = false;

    public HasSameItemsAsListMatcher(List<? extends T> expect) {
        this.expected = expect;
    }

    public HasSameItemsAsListMatcher<T> useWrapperFactory(WrapperFactory<T> wrapperFactory) {
        if (wrapperFactory == null) {
            throw new IllegalArgumentException("Wrapper factory can't be null");
        }
        this.wrapperFactory = wrapperFactory;
        return this;
    }

    public HasSameItemsAsListMatcher<T> sameSorted() {
        sortcheck = true;
        return this;
    }


    @Override
    protected boolean matchesSafely(List<? extends T> actual, Description mismatchDescription) {
        LambdaList<Wrapper<T>> wrappedActual = with(actual).convert(wrap(actual, wrapperFactory));
        LambdaList<Wrapper<T>> wrappedExpected = with(expected).convert(wrap(expected, wrapperFactory));

        LambdaList<Wrapper<T>> inActualButNotInExpected = wrappedActual.clone().remove(isIn(wrappedExpected));
        LambdaList<Wrapper<T>> inExpectedButNotInActual = wrappedExpected.clone().remove(isIn(wrappedActual));

        appendMismatch(mismatchDescription, "In Actual but not in Expected", inActualButNotInExpected);
        appendMismatch(mismatchDescription, "In Expected but not in Actual", inExpectedButNotInActual);

        int notsorted = 0;
        if (sortcheck) {
            LambdaList<Wrapper<T>> notCorrectlySorted = wrappedExpected.clone()
                    .remove(sortedEqual(wrappedActual))
                    .remove(not(isIn(wrappedActual)));
            appendMismatch(mismatchDescription, "Not sorted correctly",
                    notCorrectlySorted.convert(MismatchHelper.asStringWithFind(wrappedActual)));
            notsorted = notCorrectlySorted.size();
        }

        return inActualButNotInExpected.size() + inExpectedButNotInActual.size() + notsorted == 0;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("lists contains same items").appendText(sortcheck ? " and sorted equally" : "");
    }

    private Matcher<Wrapper<T>> sortedEqual(final List<Wrapper<T>> expected) {
        return new FeatureMatcher<Wrapper<T>, Boolean>(is(true), "", "") {
            @Override
            protected Boolean featureValueOf(Wrapper<T> actual) {
                //Can be replaced with condition as ~ expected.prev == item.prev && expected.next == item.next
                return expected.indexOf(actual) == actual.getPosition();
            }
        };
    }


    @Factory
    public static <T> HasSameItemsAsListMatcher<T> hasSameItemsAsList(List<? extends T> correctList) {
        return new HasSameItemsAsListMatcher<T>(correctList);
    }
}
