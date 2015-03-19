## WebElement Matchers

Usage:

```java
WebElement title = ...;
assertThat(title, className("big-title");
```
In case of fail it prints:
```
java.lang.AssertionError:
Expected: element "class" attribute is "big-title"
     but: <Title> "class" attribute is "another-class-name"
```

List of all matchers in module.

### Status
* `exists()` - element exists on page
* `displayed()` - element's call `element.isDisplayed()` is true
* `enabled()` - element's call `element.isEnabled()` is true
* `selected()` - element's call `element.isSelected()` is true

> Also can be simplified:
> ```java
> assertThat(button, value("Start Search"));
> assertThat(button, value(containsString("Search")));
> ```

### Text
* `text(Matcher<String> matcher)` - text property of element matches with `matcher`

### TagName
* `tagName(Matcher<String> matcher)` - tag name of element matches with `matcher`

### Attrbutes
* `attr(String name, Matcher<String> matcher)` - attribute `name` matches with `matcher`
* `action(Matcher<String> matcher)` - attribute `action` matches with `matcher`
* `alt(Matcher<String> matcher)` - attribute `alt` matches with `matcher`
* `className(Matcher<String> matcher)` - attribute `class` matches with `matcher`
* `href(Matcher<String> matcher)` - attribute `href` matches with `matcher`
* `id(Matcher<String> matcher)` - attribute `id` matches with `matcher`
* `name(Matcher<String> matcher)` - attribute `name` matches with `matcher`
* `placeholder(Matcher<String> matcher)` - attribute `placeholder` matches with `matcher`
* `size(Matcher<String> matcher)` - attribute `size` matches with `matcher`
* `src(Matcher<String> matcher)` - attribute `src` matches with `matcher`
* `target(Matcher<String> matcher)` - attribute `target` matches with `matcher`
* `title(Matcher<String> matcher)` - attribute `title` matches with `matcher`
* `type(Matcher<String> matcher)` - attribute `type` matches with `matcher`
* `value(Matcher<String> matcher)` - attribute `value` matches with `matcher`

### CSS Property
* `css(String name, Matcher<String> matcher)` - css-property with `name` matches with `matcher`

## WebDriver Matchers

... *(will be soon)*
