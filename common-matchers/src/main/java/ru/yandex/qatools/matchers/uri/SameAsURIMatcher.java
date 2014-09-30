package ru.yandex.qatools.matchers.uri;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import ru.lanwen.diff.uri.UriDiffer;
import ru.lanwen.diff.uri.core.UriDiff;
import ru.lanwen.diff.uri.core.filters.UriDiffFilter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * User: lanwen
 * Date: 30.09.14
 * Time: 20:01
 * Uses lib: https://github.com/yandex-qatools/uri-differ as core to compare URIs
 * Can use filters to prepare URIs
 *
 * Provide message:
 * {@code Expected: URI same as: <http://ya.ru>
 *             but: was <http://yandex.ru>
 *            diff: "http://[ya->yandex].ru"
 * }
 */
public class SameAsURIMatcher extends TypeSafeDiagnosingMatcher<URI> {

    private URI expectedUri;
    private List<UriDiffFilter> filters = new ArrayList<>();

    private SameAsURIMatcher(URI expectedUri) {
        this.expectedUri = expectedUri;
    }

    @Override
    protected boolean matchesSafely(URI actual, Description mismatchDescription) {
        UriDiff changes = UriDiffer.diff().expected(expectedUri).actual(actual).filter(filters).changes();
        mismatchDescription.appendText("was ").appendValue(actual)
                .appendText("\n\tdiff: ").appendValue(changes.report()).appendText("\n");
        return !changes.hasChanges();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("URI same as: ").appendValue(expectedUri);
    }


    public SameAsURIMatcher filteredWith(UriDiffFilter... filters) {
        this.filters.addAll(asList(
                noNullElements(notNull(filters, "Argument <filters> can't be null"), "Can't use null-filters")
        ));
        return this;
    }

    @Factory
    public static SameAsURIMatcher sameAsURI(URI expectedUri) {
        return new SameAsURIMatcher(expectedUri);
    }
}
