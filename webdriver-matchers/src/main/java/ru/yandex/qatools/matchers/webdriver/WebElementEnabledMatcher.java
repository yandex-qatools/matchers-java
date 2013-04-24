package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 11:16 PM
 */
public class WebElementEnabledMatcher extends TypeSafeMatcher<WebElement> {

    @Override
    protected boolean matchesSafely(WebElement element) {
        return element.isEnabled();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is enabled");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendText("element ").
                appendValue(element).
                appendText(" is not enabled");
    }

}
