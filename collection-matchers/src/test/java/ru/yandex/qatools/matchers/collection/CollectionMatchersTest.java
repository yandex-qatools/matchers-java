package ru.yandex.qatools.matchers.collection;


import org.junit.Test;
import ru.yandex.qatools.matchers.colection.CollectionMatchers;
import ru.yandex.qatools.matchers.colection.sort.SortedBy;
import ru.yandex.qatools.matchers.colection.sort.SortedCriteria;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 7:38 PM
 */
public class CollectionMatchersTest {

    @Test
    public void sortedByFactoryMethodShouldCreateSortedByMatcher() {
        SortedCriteria criteria = new SortedCriteria();
        SortedBy matcher = CollectionMatchers.sortedBy(criteria);
        assertThat(matcher, not(nullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void sortedByFactoryMethodWithNullSortedCriteriaShouldProvideNullPointerException() {
        CollectionMatchers.sortedBy(null);
    }
}
