package ru.yandex.qatools.matchers.webdriver.driver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CanFindElementMatcher extends TypeSafeMatcher<WebDriver> {
    private By by;

    protected CanFindElementMatcher(By by) {
        this.by = by;
    }

    @Override
    protected boolean matchesSafely(WebDriver wd) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element on page ").appendValue(by);
    }

    @Override
    protected void describeMismatchSafely(WebDriver wd, Description mismatchDescription) {
        mismatchDescription.appendText("no any element with selector ").appendValue(by).appendText(" found");
    }

    @Factory
    public static Matcher<WebDriver> canFindElement(By by) {
        return new CanFindElementMatcher(by);
    }
}