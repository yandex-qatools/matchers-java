package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 11:07 PM
 */
public class TextMatcher extends TypeSafeMatcher<WebElement> {

    private final Matcher<String> textMatcher;

    public TextMatcher(Matcher<String> textMatcher) {
        this.textMatcher = textMatcher;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return textMatcher.matches(item.getText());
    }

    public void describeTo(Description description) {
        description.appendText("element text ").
                appendDescriptionOf(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).
                appendText(" text is ").
                appendValue(item.getText());
    }

    @Factory
    public static TextMatcher text(String value) {
        return text(is(value));
    }

    @Factory
    public static TextMatcher text(Matcher<String> valueMatcher) {
        return new TextMatcher(valueMatcher);
    }


}
