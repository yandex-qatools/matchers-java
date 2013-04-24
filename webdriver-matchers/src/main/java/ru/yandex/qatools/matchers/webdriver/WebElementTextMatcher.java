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
public class WebElementTextMatcher extends TypeSafeMatcher<WebElement> {
    private final Matcher<String> textMatcher;

    public WebElementTextMatcher(Matcher<String> textMatcher) {
        this.textMatcher = textMatcher;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return textMatcher.matches(item.getText());
    }

    public void describeTo(Description description) {
        description.appendText("text ").appendDescriptionOf(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("text of element ").
                appendValue(item).
                appendText(" not ").
                appendDescriptionOf(textMatcher);
    }

}
