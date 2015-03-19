Matchers decoration
-------------------

[Moved here from Htmlelements - Issue #29](https://github.com/yandex-qatools/htmlelements/issues/29)

Can be used to decorate matcher with waiter, action or condition.

- Decoration of matcher with timeout:

```java
assertThat(element, should(exists()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(5))));
```

- With action:

```java
assertThat(element, should(exists()).after(pageRefresh(driver)));
```

- With condition:

```java
assertThat(element, should(hasText("Text")).inCase(element, exists()));
```

  `inCase` is same as usage of `assumeThat` before assertion, but uses less code and looks much pretty.
  Also can be used with combine of action and waitind decorators

Example of all of them used in one statement:

```java
assertThat(mailsList, should(hasMailWithSubject("Subject")).after(pageRefresh(driver)).whileWaitingUntil(timeoutHasExpired());
```

This example will be refresh page with webdriver every second in 30 sec and try to find element «mail» with subject (custom matcher) on this page.

[*MatcherDecoratorsBuilder samples*](https://github.com/yandex-qatools/matchers-java/tree/master/matcher-decorators/src/test/java/ru/yandex/qatools/matchers/decorators)
