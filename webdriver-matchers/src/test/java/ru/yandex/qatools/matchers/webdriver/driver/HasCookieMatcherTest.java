package ru.yandex.qatools.matchers.webdriver.driver;

import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.matchers.webdriver.driver.HasCookieMatcher.hasCookie;

public class HasCookieMatcherTest {
    private static final String cookieName = "Cookie";

    @Test
    public void shouldFindExistingCookie() {
        WebDriver driver = mock(WebDriver.class);
        Options options = mock(Options.class);
        Cookie cookie = new Cookie(cookieName, "oki-doki");


        when(driver.manage()).thenReturn(options);
        when(options.getCookieNamed(eq(cookieName))).thenReturn(cookie);

        assertThat("Cookie not found!", driver, hasCookie(cookieName));
    }

    @Test
    public void shouldNotFindUnexistingCookie() {
        final WebDriver driver = mock(WebDriver.class);
        Options options = mock(Options.class);

        when(driver.manage()).thenReturn(options);
        when(options.getCookieNamed(eq(cookieName))).thenReturn(null);


        assertThat("Cook should not be found", driver, not(hasCookie(cookieName)));
    }
}