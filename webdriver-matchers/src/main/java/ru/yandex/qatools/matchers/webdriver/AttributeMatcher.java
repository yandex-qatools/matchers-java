package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 10:33 PM
 */
public class AttributeMatcher extends TypeSafeMatcher<WebElement> {

    private final String name;
    private final Matcher<String> matcher;

    public AttributeMatcher(String name, Matcher<String> matcher) {
        this.matcher = matcher;
        this.name = name;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return matcher.matches(item.getAttribute(name));
    }

    public void describeTo(Description description) {
        description.appendText("attribute ").
                appendValue(name).
                appendText(" ").
                appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("attribute ").
                appendValue(name).
                appendText(" of element ").
                appendValue(item).
                appendText(" not ").
                appendDescriptionOf(matcher);
    }

}
