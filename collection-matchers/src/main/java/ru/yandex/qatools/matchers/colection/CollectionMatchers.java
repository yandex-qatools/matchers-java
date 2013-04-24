package ru.yandex.qatools.matchers.colection;

import org.hamcrest.Factory;
import ru.yandex.qatools.matchers.colection.sort.SortedBy;
import ru.yandex.qatools.matchers.colection.sort.SortedCriteria;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 6:10 PM
 */
public class CollectionMatchers {

    @Factory
    public static SortedBy sortedBy(SortedCriteria criteria) {
        return new SortedBy(criteria);
    }

}
