package ru.yandex.qatools.matchers.webdriver.driver;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebDriver;

public class HasTextMatcher extends TypeSafeMatcher<WebDriver> {
    private Matcher<String> matcher;

    protected HasTextMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WebDriver wd) {
        return matcher.matches(wd.getPageSource());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("webdriver has page with text ").appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WebDriver item, Description mismatchDescription) {
        matcher.describeMismatch(item.getPageSource(), mismatchDescription);
    }

    @Factory
    public static Matcher<WebDriver> textOnCurrentPageContains(String text) {
        return textOnCurrentPage(CoreMatchers.containsString(text));
    }

    @Factory
    public static Matcher<WebDriver> textOnCurrentPage(Matcher<String> text) {
        return new HasTextMatcher(text);
    }
}