package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/24/13, 3:19 PM
 */
public class TagNameMatcher extends TypeSafeMatcher<WebElement> {

    private final Matcher<String> textMatcher;

    public TagNameMatcher(Matcher<String> matcher) {
        this.textMatcher = matcher;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return textMatcher.matches(item.getTagName());
    }

    public void describeTo(Description description) {
        description.appendText("element tag name is ").
                appendDescriptionOf(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).
                appendText(" tag name is not ").
                appendValue(item.getTagName());
    }

    @Factory
    public static TagNameMatcher tagName(String value) {
        return tagName(is(value));
    }

    @Factory
    public static TagNameMatcher tagName(Matcher<String> valueMatcher) {
        return new TagNameMatcher(valueMatcher);
    }

}
