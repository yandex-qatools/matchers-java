package ru.yandex.qatools.matchers.webdriver.driver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.matchers.webdriver.driver.CanFindElementMatcher.canFindElement;

public class CanFindElementMatcherTest {

    public static final By XPATH_EXISTS = By.xpath("//a");
    public static final By XPATH_NOT_EXISTS = By.xpath("//aa");

    @Test
    public void worksPositive() {
        WebDriver driver = mock(WebDriver.class);
        WebElement element = mock(WebElement.class);

        when(driver.findElement(XPATH_EXISTS)).thenReturn(element);

        assertThat("Element should be found", driver, canFindElement(XPATH_EXISTS));
    }

    @Test
    public void worksNegative() {
        WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(XPATH_NOT_EXISTS)).thenThrow(new NoSuchElementException(""));

        assertThat("Element should not be found",
                driver, not(canFindElement(XPATH_NOT_EXISTS)));
    }
}
