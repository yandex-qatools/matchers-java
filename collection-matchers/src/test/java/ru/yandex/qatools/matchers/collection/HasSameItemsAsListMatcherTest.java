package ru.yandex.qatools.matchers.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 27.05.13
 * Time: 2:26
 */
public class HasSameItemsAsListMatcherTest {

    @Test
    public void listNotSameOrderButEqual() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }

    @Test(expected = AssertionError.class)
    public void listNotSameOrderNotEqualWithSortCheck() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndContainsNewItems() throws Exception {
        List<Integer> actual = asList(1, 2, 3, 4);
        List<Integer> expected = asList(3, 2, 1);

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndNotContainsSomeItems() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1", "4");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndContainsNewItemsWithSortCheck() throws Exception {
        List<String> actual = asList("1", "2", "3", "4");
        List<String> expected = asList("3", "2", "1");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndNotContainsSomeItemsWithSortCheck() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1", "4");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndNotStartsWithDifferItemWithSortCheck() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("4", "2", "1", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }

    @Test(expected = AssertionError.class)
    public void actualListHasLastItemTwice() throws Exception {
        List<String> actual = asList("1", "2", "3", "3");
        List<String> expected = asList("1", "2", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }


    @Test(expected = AssertionError.class)
    public void actualListHasItemsTwiceAndNotSameSorted() throws Exception {
        List<String> actual = asList("1", "2", "2", "3", "3");
        List<String> expected = asList("1", "2", "3", "3", "2");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void actualListHasNotLastItemTwice() throws Exception {
        List<String> actual = asList("1", "2", "2", "3");
        List<String> expected = asList("1", "2", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }


    @Test(expected = AssertionError.class)
    public void expectedListHasLastItemTwice() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("1", "2", "3", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void expectedListHasNotLastItemTwice() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("1", "2", "2", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected));
    }


    @Test(expected = AssertionError.class)
    public void expectedListHasNotLastItemTwiceWithSort() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("1", "2", "2", "3");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }

    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndStartsWithDifferItemWithSortCheck() throws Exception {
        List<String> actual = asList("4", "1", "2", "3");
        List<String> expected = asList("3", "2", "1");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void listEqualSortedAndStartsWithDifferItemWithSortCheck() throws Exception {
        List<String> actual = asList("1", "2", "3", "4");
        List<String> expected = asList("5", "1", "2", "3", "4");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void emptyListActual() throws Exception {
        assertThat(new ArrayList<String>(), HasSameItemsAsListMatcher.hasSameItemsAsList(asList("Abc", "DeF", "gHI")).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void emptyListExpected() throws Exception {
        assertThat(asList("Abc", "DeF", "gHI"), HasSameItemsAsListMatcher.hasSameItemsAsList(new ArrayList<String>()).sameSorted());
    }


    @Test(expected = AssertionError.class)
    public void withoutOverrideWrapperGetFail() throws Exception {
        List<String> actual = asList("aBc", "DEf", "gHi");
        List<String> expected = asList("Abc", "DeF", "gHI");

        assertThat(actual, HasSameItemsAsListMatcher.hasSameItemsAsList(expected).sameSorted());
    }


    @Test
    public void overrideWrapperToIgnoreCaseGetSuccess() throws Exception {
        List<String> actual = asList("aBc", "DEf", "gHi");
        List<String> expected = asList("Abc", "DeF", "gHI");

        assertThat(actual, HasSameItemsAsListMatcher
                .hasSameItemsAsList(expected)
                .sameSorted()
                .useWrapperFactory(new WrapperFactory<String>() {
                    @Override
                    public Wrapper<String> newWrapper() {
                        return new Wrapper<String>() {
                            @Override
                            public boolean safelyEquals(String actual, String expected) {
                                return actual.toLowerCase().equals(expected.toLowerCase());
                            }

                            @Override
                            public String asString(String obj) {
                                return obj;
                            }
                        };
                    }
                }));
    }


    @Test(expected = IllegalArgumentException.class)
    public void factoryCantBeNull() throws Exception {
        List<String> actual = asList("aBc", "DEf", "gHi");
        List<String> expected = asList("Abc", "DeF", "gHI");

        assertThat(actual, HasSameItemsAsListMatcher
                .hasSameItemsAsList(expected)
                .sameSorted()
                .useWrapperFactory(null));
    }


    @Test(expected = IllegalStateException.class)
    public void factoryCantProduceNullWrapper() throws Exception {
        List<String> actual = asList("aBc", "DEf", "gHi");
        List<String> expected = asList("Abc", "DeF", "gHI");

        assertThat(actual, HasSameItemsAsListMatcher
                .hasSameItemsAsList(expected)
                .sameSorted()
                .useWrapperFactory(new NullWrapperFactory()));
    }


    private class NullWrapperFactory implements WrapperFactory<String> {
        @Override
        public Wrapper<String> newWrapper() {
            return null;
        }
    }

}
