package ru.yandex.qatools.matchers.webdriver.driver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class HasCookieMatcher extends TypeSafeMatcher<WebDriver> {
    private String name;

    protected HasCookieMatcher(String name) {
        this.name = name;
    }

    @Override
    protected boolean matchesSafely(WebDriver wDriver) {
        try {
            wDriver.manage().getCookieNamed(name).toString();
            return true;
        } catch (NullPointerException npe) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("cookie named ").appendValue(name);
    }

    @Override
    protected void describeMismatchSafely(WebDriver item, Description mismatchDescription) {
        mismatchDescription.appendText("was only ");
        List<String> cookNames = new LinkedList<>();
        for (Cookie cook : item.manage().getCookies()) {
           cookNames.add(cook.getName());
        }
        mismatchDescription.appendValueList("[", ", ", "]", cookNames);
    }

    @Factory
    public static Matcher<WebDriver> hasCookie(String name) {
        return new HasCookieMatcher(name);
    }
}