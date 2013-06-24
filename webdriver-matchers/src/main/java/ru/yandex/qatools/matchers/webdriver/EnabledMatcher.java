package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 11:16 PM
 */
public class EnabledMatcher extends TypeSafeMatcher<WebElement> {

    @Override
    protected boolean matchesSafely(WebElement element) {
        return element.isEnabled();
    }

    public void describeTo(Description description) {
        description.appendText("element is enabled on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendValue(element).
                appendText(" is not enabled on page");
    }

    @Factory
    public static Matcher<WebElement> enabled() {
        return new EnabledMatcher();
    }

}
