package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 10:33 PM
 * @author Artem Koshelev artkoshelev@gmail.com
 *         4/13/16
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
        description.appendText("element ").
                appendValue(name).
                appendText(" attribute ").
                appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).
                appendText(" ").
                appendValue(name).
                appendText(" attribute is ").
                appendValue(item.getAttribute(name));
    }

    @Factory
    public static AttributeMatcher attribute(final String name, final Matcher<String> valueMatcher) {
        return new AttributeMatcher(name, valueMatcher);
    }

    @Factory
    public static AttributeMatcher attribute(final String name, final String value) {
        return new AttributeMatcher(name, is(value));
    }

    @Factory
    public static AttributeMatcher attr(final String name, final Matcher<String> valueMatcher) {
        return attribute(name, valueMatcher);
    }

    @Factory
    public static AttributeMatcher attr(final String name, final String value) {
        return attribute(name, value);
    }

    @Factory
    public static AttributeMatcher id(final Matcher<String> valueMatcher) {
        return attr("id", valueMatcher);
    }

    @Factory
    public static AttributeMatcher id(final String value) {
        return id(is(value));
    }

    @Factory
    public static AttributeMatcher value(final Matcher<String> valueMatcher) {
        return attr("value", valueMatcher);
    }

    @Factory
    public static AttributeMatcher value(final String value) {
        return value(is(value));
    }

    @Factory
    public static AttributeMatcher name(final Matcher<String> valueMatcher) {
        return attr("name", valueMatcher);
    }

    @Factory
    public static AttributeMatcher name(final String value) {
        return name(is(value));
    }

    @Factory
    public static AttributeMatcher className(final Matcher<String> valueMatcher) {
        return attr("class", valueMatcher);
    }

    @Factory
    public static AttributeMatcher className(final String value) {
        return className(is(value));
    }

    @Factory
    public static AttributeMatcher href(final Matcher<String> valueMatcher) {
        return attr("href", valueMatcher);
    }

    @Factory
    public static AttributeMatcher href(final String value) {
        return href(is(value));
    }

    @Factory
    public static AttributeMatcher src(final Matcher<String> valueMatcher) {
        return attr("src", valueMatcher);
    }

    @Factory
    public static AttributeMatcher src(final String value) {
        return src(is(value));
    }

    @Factory
    public static AttributeMatcher alt(final Matcher<String> valueMatcher) {
        return attr("alt", valueMatcher);
    }

    @Factory
    public static AttributeMatcher alt(final String value) {
        return alt(is(value));
    }

    @Factory
    public static AttributeMatcher title(final Matcher<String> valueMatcher) {
        return attr("title", valueMatcher);
    }

    @Factory
    public static AttributeMatcher title(final String value) {
        return title(is(value));
    }

    @Factory
    public static AttributeMatcher target(final Matcher<String> valueMatcher) {
        return attr("target", valueMatcher);
    }

    @Factory
    public static AttributeMatcher target(final String value) {
        return target(is(value));
    }

    @Factory
    public static AttributeMatcher action(final Matcher<String> valueMatcher) {
        return attr("action", valueMatcher);
    }

    @Factory
    public static AttributeMatcher action(final String value) {
        return action(is(value));
    }

    @Factory
    public static AttributeMatcher placeholder(final Matcher<String> valueMatcher) {
        return attr("placeholder", valueMatcher);
    }

    @Factory
    public static AttributeMatcher placeholder(final String value) {
        return placeholder(is(value));
    }

    @Factory
    public static AttributeMatcher size(final Matcher<String> valueMatcher) {
        return attr("size", valueMatcher);
    }

    @Factory
    public static AttributeMatcher size(final String value) {
        return size(is(value));
    }

    @Factory
    public static Matcher<WebElement> checked() {
        return attr("checked", "true");
    }

    @Factory
    public static Matcher<WebElement> selected() {
        return attr("selected", "true");
    }

    @Factory
    public static Matcher<WebElement> focused() {
        return attr("focused", "true");
    }
}
