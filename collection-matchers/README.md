Collection Matchers
==========================

Матчер ContainsUniqueItems
---------------------------------

Матчер проверяет содержание в коллекции только уникальных элементов

```java
    @Test
    public void assertionShouldNotBeThrownWhenThereAreNoDuplicates() {
        List<String> collectionWithDuplicates = asList("veni", "vidi", "vici");
        assertThat(collectionWithDuplicates, containsUniqueItems());
    }
```

Пример использования HasSameItemsAsListMatcher'а
---------------------------------

Что делать, если есть два списка, и их нужно сравнить? Но при этом недостаточно узнать, что они просто
имеют разный размер или содержат не все элементы. Что делать, если нужно знать все различия двух списков?

Использовать HasSameItemsAsListMatcher!

```java
    @Test(expected = AssertionError.class)
    public void listNotEqualSortedAndNotContainsSomeItems() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1", "4");

        assertThat(actual, hasSameItemsAsList(expected));
    }
```

Выведет

```
Expected: Lists contains same items
     but:
(In Expected but not in Actual) [<1>]:
-> <4>
```

Если нужно еще и проверять что списки идентично отсортированы - укажем это при помощи ``.sameSorted()``

```java
    @Test
    public void listNotSameOrderButEqual() throws Exception {
        List<String> actual = asList("1", "2", "3");
        List<String> expected = asList("3", "2", "1");

        assertThat(actual, hasSameItemsAsList(expected).sameSorted());
    }
```

Выведет

```
Expected: Lists contains same items and sorted equally
     but:
(Not sorted correctly) [<2>]:
-> "Expected 3 on position [0], but was - 1"
-> "Expected 1 on position [2], but was - 3"
```

Матчер HasSameItemsAsCollectionMatcher
---------------------------------
Так как java не гарантирует порядка элементов в коллекции, этот матчер умеет сравнивать две коллекции 
только на отсутствие/присутствие элементов. Синтаксис и вывод аналогичен HasSameItemsAsListMatcher, за исключением того, 
что на вход поступает коллекция и статический метод называется ``hasSameItemsAsCollection``.

Вывод при этом примерно такой:

```
Expected: collections contains same items
     but:
(Not equal frequency) [<2>]:
-> "3 - expected [2] times, but frequency was - [1]"
-> "2 - expected [2] times, but frequency was - [1]"
```



Расширенное применение HasSameItemsAsListMatcher
---------------------------------

Иногда необходимо переопределить вывод каждого элемента в случае ошибки.
Или задать свой критерий равенства двух объектов между собой. Но что делать, если возможности переопределить эти методы
у конечного объекта нет? В этом случае необходимо действовать по следующему алгоритму:

* Начальные данные. Предположим нужно сравнить строковые списки игнорируя регистр элементов.

```java
        List<String> actual = asList("aBc", "DEf", "gHi");
        List<String> expected = asList("Abc", "DeF", "gHI");
```

* Расширим абстрактный класс Wrapper&lt;String&gt; - параметризовав его по типу элементов - String.
В наследнике необходимо реализовать 2 метода - условие равенства и строковый вывод каждого элемента.

```java
public class IgnoreCaseStringWrapper extends Wrapper<String> {
    @Override
    public boolean safelyEquals(String actual, String expected) {
        return actual.toLowerCase().equals(expected.toLowerCase());
    }

    @Override
    public String asString(String obj) {
        return obj;
    }
}
```
Заботиться о проверках элементов на null при сравнении нет необходимости - родительский класс сделает это
до вызова переопределенного метода.

* Создадим реализацию интерфейса фабрики врапперов - для чего переопределим всего 1 метод, который будет возвращать
новые экземпляры врапперов

```java
    public class IgnoreCaseWrapperFactory implements WrapperFactory<String> {
        @Override
        public Wrapper<String> newWrapper() {
            return new IgnoreCaseStringWrapper();
        }
    }
```

* Укажем матчеру использовать фабрику при сравнении

```java
  @Test
    public void useCustomWrapper() throws Exception {
        ...
        assertThat(actual, hasSameItemsAsList(expected).useWrapperFactory(new IgnoreCaseWrapperFactory()));
    }
```
