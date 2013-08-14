package ru.yandex.qatools.matchers.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Innokenty Shuvalov innokenty-shuvalov
 * @param <T>
 */
public class ContainsUniqueItems<T> extends TypeSafeMatcher<Iterable<? extends T>> {

	public void describeTo(Description description) {
		description.appendText("an iterable object where all elements are different");
	}

	@Override
	protected boolean matchesSafely(Iterable<? extends T> iterable) {
		return Sets.newHashSet(iterable).size() == Lists.newArrayList(iterable).size();
	}

    @Factory
    public static <U> Matcher<Iterable<? extends U>> containsUniqueItems() {
        return new ContainsUniqueItems<U>();
    }
}
