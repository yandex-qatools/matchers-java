package ru.yandex.qatools.matchers.colection.sort;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 6:21 PM
 */
public class SortedBy<T> extends TypeSafeMatcher<Iterable<T>> {

    private SortedCriteria criteria;

    public SortedBy(SortedCriteria criteria) {
        if (criteria == null) throw new NullPointerException("Sorted Criteria can't be null value");
        this.criteria = criteria;
    }

    @Override
    protected boolean matchesSafely(Iterable<T> list) {
        for (T item : list) {
            System.out.println(criteria);
            System.out.println(item);
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }

    @Override
    protected void describeMismatchSafely(Iterable<T> item, org.hamcrest.Description mismatchDescription) {

    }

}
