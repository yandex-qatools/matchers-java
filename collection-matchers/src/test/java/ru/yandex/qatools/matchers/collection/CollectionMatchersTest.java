package ru.yandex.qatools.matchers.collection;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static ru.yandex.qatools.matchers.collection.ContainsUniqueItems.containsUniqueItems;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 7:38 PM
 * @author Artem Koshelev artkoshelev
 */
public class CollectionMatchersTest {

    @Test
    public void containsUniqueElementsFactoryMethodReturnsMatcher() {
        Matcher<Iterable<? extends Object>> matcher = containsUniqueItems();
        assertThat(matcher, not(nullValue()));
    }

    @Test(expected = AssertionError.class)
    public void assertionShouldThrownWhenDublicatesAppears() {
        List<String> collectionWithDublicates = asList("veni", "vidi", "veni");
        assertThat(collectionWithDublicates, containsUniqueItems());
    }

    @Test
    public void assertionShouldNotBeThrownWhenThereIsNoDublicates() {
        List<String> collectionWithDublicates = asList("veni", "vidi", "vici");
        assertThat(collectionWithDublicates, containsUniqueItems());
    }


}