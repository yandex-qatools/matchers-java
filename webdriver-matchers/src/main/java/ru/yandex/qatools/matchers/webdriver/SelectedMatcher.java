package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/24/13, 3:18 PM
 */
public class SelectedMatcher extends TypeSafeMatcher<WebElement> {

    @Override
    protected boolean matchesSafely(WebElement element) {
        return element.isSelected();
    }

    public void describeTo(Description description) {
        description.appendText("element is selected on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendValue(element).
                appendText(" is not selected on page");
    }

    @Factory
    public static SelectedMatcher selected() {
        return new SelectedMatcher();
    }

}