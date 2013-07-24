Matchers decoration
-------------------

[Переехало в этот репозиторий из Htmlelements - Issue #29](https://github.com/yandex-qatools/htmlelements/issues/29)

С помощью этого модуля можно декорировать матчер ожиданием какого-либо события, действием или условием.

Декорирование матчера ожиданием по таймауту выглядит так:

```java
assertThat(element, should(exists()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(5))));
```

Задекорировать матчер действием можно так:

```java
assertThat(element, should(exists()).after(pageRefresh(driver)));
```

А добавить проверку условия перед матчером можно так:

```java
assertThat(element, should(hasText("Text")).inCase(element, exists()));
```

Эта конструкция эквивалентна добавлению `assumeThat` перед проверкой, но гораздо более компактна и позволяет сочетать
декорирование условием с декораторами другого типа (см. ниже).

Также можно комбинировать декораторы друг с другом. Например:

```java
assertThat(mailsList, should(hasMailWithSubject("Subject")).after(pageRefresh(driver)).whileWaitingUntil(timeoutHasExpired());
```

Эта конструкция будет в течении 30 секунд обновлять страницу каждую секунду и проверять, появилось ли на странице в
списке писем нужное письмо. Как только письмо появится, матчер закончит свое выполнение и проверка пройдет успешно.
В противном случае по истечении таймаута будет возвращена ошибка.

[*MatcherDecoratorsBuilder samples*](https://github.com/yandex-qatools/matchers-java/tree/master/matcher-decorators/src/test/java/ru/yandex/qatools/matchers/decorators)
