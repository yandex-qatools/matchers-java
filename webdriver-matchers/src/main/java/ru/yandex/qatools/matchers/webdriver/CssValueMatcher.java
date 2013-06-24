package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/24/13, 3:21 PM
 */
public class CssValueMatcher extends TypeSafeMatcher<WebElement> {

    private final String name;
    private final Matcher<String> matcher;

    public CssValueMatcher(String name, Matcher<String> matcher) {
        this.matcher = matcher;
        this.name = name;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return matcher.matches(item.getCssValue(name));
    }

    public void describeTo(Description description) {
        description.appendText("element css property ").
                appendValue(name).
                appendText(" is ").
                appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).
                appendText(" css property ").
                appendValue(name).
                appendText(" is ").
                appendValue(item.getCssValue(name));
    }

    @Factory
    public static CssValueMatcher cssValue(final String name, final Matcher<String> valueMatcher) {
        return new CssValueMatcher(name, valueMatcher);
    }

    @Factory
    public static CssValueMatcher cssValue(final String name, final String value) {
        return cssValue(name, is(value));
    }

    @Factory
    public static CssValueMatcher css(final String name, final Matcher<String> valueMatcher) {
        return cssValue(name, valueMatcher);
    }

    @Factory
    public static CssValueMatcher css(final String name, final String value) {
        return css(name, is(value));
    }
}
