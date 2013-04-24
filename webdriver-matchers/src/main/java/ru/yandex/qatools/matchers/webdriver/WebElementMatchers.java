package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 10:48 PM
 */
public class WebElementMatchers {

    @Factory
    public static Matcher<WebElement> exist() {
        return new WebElementExistMatcher();
    }

    @Factory
    public static Matcher<WebElement> displayed() {
        return new WebElementDisplayedMatcher();
    }

    @Factory
    public static Matcher<WebElement> enabled() {
        return new WebElementEnabledMatcher();
    }

    @Factory
    public static Matcher<WebElement> text(Matcher<String> matcher) {
        return new WebElementTextMatcher(matcher);
    }

    @Factory
    public static Matcher<WebElement> text(String value) {
        return text(is(value));
    }

    @Factory
    public static Matcher<WebElement> id(final Matcher<String> matcher) {
        return attribute("id", matcher);
    }

    @Factory
    public static Matcher<WebElement> id(final String value) {
        return id(is(value));
    }

    @Factory
    public static Matcher<WebElement> name(final Matcher<String> matcher) {
        return attribute("name", matcher);
    }

    @Factory
    public static Matcher<WebElement> name(final String value) {
        return name(is(value));
    }

    @Factory
    public static Matcher<WebElement> className(final Matcher<String> matcher) {
        return attribute("class", matcher);
    }

    @Factory
    public static Matcher<WebElement> className(final String value) {
        return className(is(value));
    }

    @Factory
    public static Matcher<WebElement> value(final Matcher<String> matcher) {
        return attribute("value", matcher);
    }

    @Factory
    public static Matcher<WebElement> value(final String value) {
        return value(is(value));
    }

    @Factory
    public static Matcher<WebElement> attr(final String name, final Matcher<String> valueMatcher) {
        return attribute(name, valueMatcher);
    }

    @Factory
    public static Matcher<WebElement> attr(final String name, final String value) {
        return attr(name, is(value));
    }

    @Factory
    public static Matcher<WebElement> attribute(final String name, final Matcher<String> valueMatcher) {
        return new AttributeMatcher(name, valueMatcher);
    }

    @Factory
    public static Matcher<WebElement> attribute(final String name, final String value) {
        return attribute(name, is(value));
    }

}
