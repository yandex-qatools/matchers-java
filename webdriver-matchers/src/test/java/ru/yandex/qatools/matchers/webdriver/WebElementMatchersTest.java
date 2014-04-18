package ru.yandex.qatools.matchers.webdriver;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.matchers.webdriver.WebElementFactory.createFilledWebElement;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.*;
import static ru.yandex.qatools.matchers.webdriver.CssValueMatcher.css;
import static ru.yandex.qatools.matchers.webdriver.CssValueMatcher.cssValue;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;
import static ru.yandex.qatools.matchers.webdriver.SelectedMatcher.selected;
import static ru.yandex.qatools.matchers.webdriver.TagNameMatcher.tagName;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/24/13, 3:51 PM
 */
public class WebElementMatchersTest {

    WebElement element = createFilledWebElement();

    @Test
    public void existsMatcherShouldCheckIsElementExists() {
        assertThat(element, exists());
    }

    @Test
    public void enabledMatcherShouldCheckIsEnabledProperty() {
        assertThat(element, enabled());
    }

    @Test
    public void displayedMatcherShouldCheckIsDisplayedProperty() {
        assertThat(element, displayed());
    }

    @Test
    public void selectedMatcherShouldCheckIsSelectedProperty() {
        assertThat(element, selected());
    }

    @Test
    public void textMatcherShouldCheckTextValue() {
        assertThat(element, text(containsString("text")));
    }

    @Test
    public void tagNameMatcherShouldCheckTagNameValue() {
        assertThat(element, tagName(element.getTagName()));
    }

    @Test
    public void attributeMatcherShouldCheckAttribute() {
        assertThat(element, attribute("id", element.getAttribute("id")));
    }

    @Test
    public void attrMatcherShouldCheckAttribute() {
        assertThat(element, attr("id", element.getAttribute("id")));
    }

    @Test
    public void idMatcherShouldCheckIdAttribute() {
        assertThat(element, id(element.getAttribute("id")));
    }

    @Test
    public void nameMatcherShouldCheckNameAttribute() {
        assertThat(element, name(element.getAttribute("name")));
    }

    @Test
    public void valueMatcherShouldCheckValueAttribute() {
        assertThat(element, value(element.getAttribute("value")));
    }

    @Test
    public void classNameMatcherShouldCheckClassAttribute() {
        assertThat(element, className(element.getAttribute("class")));
    }

    @Test
    public void srcMatcherShouldCheckSrcValueAttribute() {
        assertThat(element, src(element.getAttribute("src")));
    }

    @Test
    public void sizeMatcherShouldCheckSizeValueAttribute() {
        assertThat(element, size(element.getAttribute("size")));
    }

    @Test
    public void titleMatcherShouldCheckTitleValueAttribute() {
        assertThat(element, title(element.getAttribute("title")));
    }

    @Test
    public void targetMatcherShouldCheckTargetValueAttribute() {
        assertThat(element, target(element.getAttribute("target")));
    }

    @Test
    public void actionMatcherShouldCheckActionValueAttribute() {
        assertThat(element, action(element.getAttribute("action")));
    }

    @Test
    public void placeholderMatcherShouldCheckPlaceholderValueAttribute() {
        assertThat(element, placeholder(element.getAttribute("placeholder")));
    }

    @Test
    public void altMatcherShouldCheckAltValueAttribute() {
        assertThat(element, alt(element.getAttribute("alt")));
    }

    @Test
    public void cssValueMatcherShouldCheckCssValueAttribute() {
        assertThat(element, cssValue("width", element.getCssValue("width")));
    }

    @Test
    public void cssMatcherShouldCheckCssValueAttribute() {
        assertThat(element, css("width", element.getCssValue("width")));
    }

}
