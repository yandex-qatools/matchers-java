package ru.yandex.qatools.matchers.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/24/13, 3:49 PM
 */
public class WebElementFactory {

    public static WebElement createWebElement() {
        WebElement element = mock(WebElement.class);
        when(element.toString()).thenReturn("Search Input");
        return element;
    }

    public static WebElement createFilledWebElement() {
        WebElement element = createWebElement();
        when(element.isSelected()).thenReturn(true);
        when(element.isDisplayed()).thenReturn(true);
        when(element.isEnabled()).thenReturn(true);
        when(element.getTagName()).thenReturn("tagName");
        when(element.getText()).thenReturn("text");

        when(element.findElement(By.xpath("self::*"))).thenReturn(element);
//        when(element.findElement(By.xpath("self::*"))).thenThrow(new WebDriverException());

        List<String> attributes = Arrays.asList("id", "value", "name", "type", "class", "href",
                "target", "action", "placeholder", "src", "title");

        for (String name : attributes) {
            when(element.getAttribute(name)).thenReturn(name + " value");
        }

        for (String name : Arrays.asList("width", "height")) {
            when(element.getCssValue(name)).thenReturn(name);
        }
        return element;
    }

}
