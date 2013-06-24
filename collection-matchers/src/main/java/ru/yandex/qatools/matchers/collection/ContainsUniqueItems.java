package ru.yandex.qatools.matchers.collection;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Artem Koshelev artkoshelev
 *
 * @param <T>
 */
public class ContainsUniqueItems<T> extends TypeSafeDiagnosingMatcher<Iterable<? extends T>> {

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean matchesSafely(Iterable<? extends T> collection,
			Description mismatchDescription) {
		Set<T> uniqueItems = new HashSet<T>();
		int size = 0;
		for (T item : collection) {
			uniqueItems.add(item);
			size++;
		}
		return uniqueItems.size() == size;
	}

    @Factory
    public static <U> Matcher<Iterable<? extends U>> containsUniqueItems() {
        return new ContainsUniqueItems<U>();
    }


}
