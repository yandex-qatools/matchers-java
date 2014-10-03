package ru.yandex.qatools.matchers.uri;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import ru.lanwen.diff.uri.UriDiffer;
import ru.lanwen.diff.uri.core.filters.UriDiffFilter;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static ru.lanwen.diff.uri.core.filters.AnyParamValueFilter.param;
import static ru.lanwen.diff.uri.core.filters.SchemeFilter.scheme;
import static ru.yandex.qatools.matchers.uri.SameAsURIMatcher.sameAsURI;

/**
 * User: lanwen
 * Date: 30.09.14
 * Time: 20:14
 */
public class SameAsURIMatcherTest {

    public static final URI EXPECTED_URI = URI.create("http://ya.ru");
    public static final URI ACTUAL_URI = URI.create("http://yandex.ru");

    public static final URI ACTUAL_AS_EXPECTED_WITH_PARAM_AND_SCHEME_URI = URI.create("https://ya.ru/?param=1");
    public static final String PARAM_NAME = "param";

    public static final String HTTP_SCHEME = "http";
    public static final String HTTPS_SCHEME = "https";

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorOnDifferentURIs() throws Exception {
        assertThat(ACTUAL_URI, sameAsURI(EXPECTED_URI));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorWithoutFilters() throws Exception {
        assertThat(ACTUAL_AS_EXPECTED_WITH_PARAM_AND_SCHEME_URI, sameAsURI(EXPECTED_URI));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWithNullFilter() throws Exception {
        assertThat(ACTUAL_AS_EXPECTED_WITH_PARAM_AND_SCHEME_URI, sameAsURI(EXPECTED_URI)
                .filteredWith(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowNPEWithNullInFilter() throws Exception {
        assertThat(ACTUAL_AS_EXPECTED_WITH_PARAM_AND_SCHEME_URI, sameAsURI(EXPECTED_URI)
                .filteredWith((UriDiffFilter)null));
    }

    @Test
    public void shouldApplyFiltersAndBeSuccessful() throws Exception {
        assertThat(ACTUAL_AS_EXPECTED_WITH_PARAM_AND_SCHEME_URI, sameAsURI(EXPECTED_URI)
                .filteredWith(param(PARAM_NAME), scheme(HTTP_SCHEME, HTTPS_SCHEME)));
    }

    @Test
    public void shouldUseDefaultReport() throws Exception {
        Description description = new StringDescription();

        sameAsURI(EXPECTED_URI).describeMismatch(ACTUAL_URI, description);
        assertThat("Description should contain actual and diff", description.toString(),
                both(containsString(UriDiffer.diff().actual(ACTUAL_URI).expected(EXPECTED_URI).changes().report()))
                        .and(containsString(ACTUAL_URI.toString())));
    }


}
