package ru.yandex.qatools.matchers.webdriver;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/23/13, 10:59 PM
 */
public class ExistsMatcher extends TypeSafeMatcher<WebElement> {

    @Override
    protected boolean matchesSafely(WebElement element) {
        try {
            element.findElement(By.xpath("self::*"));
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public void describeTo(Description description) {
        description.appendText("element existing on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendValue(element).
                appendText(" not existing on page");
    }

    @Factory
    public static ExistsMatcher exists() {
        return new ExistsMatcher();
    }

}
