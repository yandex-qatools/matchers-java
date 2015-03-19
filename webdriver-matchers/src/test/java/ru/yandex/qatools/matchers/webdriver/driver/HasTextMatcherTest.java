package ru.yandex.qatools.matchers.webdriver.driver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.matchers.webdriver.driver.HasTextMatcher.textOnCurrentPageContains;

public class HasTextMatcherTest {

    public static final String HTML_BODY = "Some html body texting form";
    public static final String TEXT_PART = "text";
    public static final String ANOTHER_HTML_BODY = "Some html body tex";

    @Test
    public void worksPositive() {
        WebDriver driver = mock(WebDriver.class);

        when(driver.getPageSource()).thenReturn(HTML_BODY);


        assertThat("Text should be found", driver, textOnCurrentPageContains(TEXT_PART));
    }

    @Test
    public void worksNegative() {
        final WebDriver driver = mock(WebDriver.class);
        when(driver.getPageSource()).thenReturn(ANOTHER_HTML_BODY);

        assertThat("Should not found text", driver, not(textOnCurrentPageContains(TEXT_PART)));
    }
}