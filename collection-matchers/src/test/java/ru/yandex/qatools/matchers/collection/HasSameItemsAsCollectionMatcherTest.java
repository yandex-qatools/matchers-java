package ru.yandex.qatools.matchers.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsCollectionMatcher.hasSameItemsAsCollection;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 23.05.13
 * Time: 2:44
 */
public class HasSameItemsAsCollectionMatcherTest {

    @Test(expected = AssertionError.class)
    public void withOnlyOneCommonElement() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3", "4", "7");
        List<String> expected = Arrays.asList("3", "4", "5");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

    @Test(expected = AssertionError.class)
    public void withOnlyOneDifferElementInActual() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3", "4");
        List<String> expected = Arrays.asList("1", "2", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

    @Test(expected = AssertionError.class)
    public void withOnlyOneDifferElementInExpected() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3");
        List<String> expected = Arrays.asList("1", "2", "3", "4");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

    @Test
    public void collectionsEquals() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3");
        List<String> expected = Arrays.asList("1", "2", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }


    @Test
    public void collectionsEqualsWithNotEqualOrder() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3");
        List<String> expected = Arrays.asList("3", "2", "1");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }



    @Test(expected = AssertionError.class)
    public void collectionsNotEqualsWhenLastElTwiceInActual() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3", "3");
        List<String> expected = Arrays.asList("1", "2", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

    @Test(expected = AssertionError.class)
    public void collectionsNotEqualsWhenLastElTwiceInExpected() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3");
        List<String> expected = Arrays.asList("1", "2", "3", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }


    @Test(expected = AssertionError.class)
    public void collectionsNotEqualsWhenNotLastElTwiceInActual() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "2", "3");
        List<String> expected = Arrays.asList("1", "2", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

    @Test(expected = AssertionError.class)
    public void collectionsNotEqualsWhenNotLastElTwiceInExpected() throws Exception {
        List<String> actual = Arrays.asList("1", "2", "3");
        List<String> expected = Arrays.asList("1", "2", "2", "3");

        assertThat(actual, hasSameItemsAsCollection(expected));
    }

}
