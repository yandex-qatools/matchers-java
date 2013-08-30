## WebElement Matchers

Использовать матчеры легко: 
```java
WebElement title = ...;
assertThat(title, className("big-title");
```
В случае ошибки возникнет исключение: 
```
java.lang.AssertionError:
Expected: element "class" attribute is "big-title"
     but: <Title> "class" attribute is "another-class-name"
```

Ниже приведен полный список доступных матчеров.

### Status
* `exists()` - существование элемента на странице
* `displayed()` - значение свойства `element.isDisplayed()` положительно
* `enabled()` - значение свойства `element.isEnabled()` положительно
* `selected()` - значение свойства `element.isSelected()` положительно

> Так же, каждый метод может получать на вход как текст, так и матчер для проверки этого текста:
> ```java
> assertThat(button, value("Start Search"));
> assertThat(button, value(containsString("Search")));
> ```
> Для краткости будут использоваться методы с матчерами.

### Text
* `text(Matcher<String> matcher)` - текст элемента удовлетворяет матчеру `matcher`

### TagName
* `tagName(Matcher<String> matcher)` - значение имени тега элемента удовлетворяет матчеру `matcher`

### Attrbutes
* `attr(String name, Matcher<String> matcher)` - значение аттрибута `name` удовлетворяет матчеру `matcher`
* `action(Matcher<String> matcher)` - значение аттрибута `action` удовлетворяет матчеру `matcher`
* `alt(Matcher<String> matcher)` - значение аттрибута `alt` удовлетворяет матчеру `matcher`
* `className(Matcher<String> matcher)` - значение аттрибута `class` удовлетворяет матчеру `matcher`
* `href(Matcher<String> matcher)` - значение аттрибута `href` удовлетворяет матчеру `matcher`
* `id(Matcher<String> matcher)` - значение аттрибута `id` удовлетворяет матчеру `matcher`
* `name(Matcher<String> matcher)` - значение аттрибута `name` удовлетворяет матчеру `matcher`
* `placeholder(Matcher<String> matcher)` - значение аттрибута `placeholder` удовлетворяет матчеру `matcher`
* `size(Matcher<String> matcher)` - значение аттрибута `size` удовлетворяет матчеру `matcher`
* `src(Matcher<String> matcher)` - значение аттрибута `src` удовлетворяет матчеру `matcher`
* `target(Matcher<String> matcher)` - значение аттрибута `target` удовлетворяет матчеру `matcher`
* `title(Matcher<String> matcher)` - значение аттрибута `title` удовлетворяет матчеру `matcher`
* `type(Matcher<String> matcher)` - значение аттрибута `type` удовлетворяет матчеру `matcher`
* `value(Matcher<String> matcher)` - значение аттрибута `value` удовлетворяет матчеру `matcher`

### CSS Property
* `css(String name, Matcher<String> matcher)` - значение css-свойства `name` удовлетворяет матчеру `matcher`

## WebDriver Matchers

... *(will be soon)*
