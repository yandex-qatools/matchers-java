SortMatcherSamples
==========================


Пример использования SortMatcher'а
---------------------------------

Предположим, что у нас есть список каких-то объектов. Например, объектов ExampleObject.

```java
public class ExampleObject {
      private String firstField;
      private String secondField;
      private int thirdField;
}
```

Рассмотрим способ проверки сортировки списка подобных объектов. Предположим, что сортировка многоуровневая (то есть по нескольким полям).
Также, предположим, что сортировка по разным полям происходит в разных направлениях. Тогда тест будет выглядеть следующим образом:

```java
public class Test {

      @Test
      public void test(){
            List<ExampleClass> exampleList = asList(new ExampleClass(10, "exampleValue1", (long)100),
                    new ExampleClass(10, "exampleValue1", (long)10),
                    new ExampleClass(10, "exampleValue1", (long)-10));
            assertThat(exampleList, sorted(by().asc("fieldNumber1", "fieldNumber2").desc("fieldNumber3")));
      }
}
```
Стоит отметить, что поля, по которым производится сортировка, должны иметь класс, реализующий интерфейс Comparable, или быть простого типа.

Также, есть возможность проверить отсортированность списка объектов, ничего не зная про поля. Для этого нужно, чтобы объекты в списке реализовывали интерфейс Comparable.
Тест для таких полей будет выглядеть так (в матчер в данном случае можно вложить один из критериев: asc() или desc() - по названию понятно, в какую сторону ожидается сортировка.):


```java
public class Test {

      @Test
      public void test(){
            List<ExampleClass> exampleList = asList(new ExampleClass(10, "exampleValue1", (long)100),
                    new ExampleClass(10, "exampleValue1", (long)10),
                    new ExampleClass(10, "exampleValue1", (long)-10));
            assertThat(exampleList, sorted(asc()));
      }
}
```

В случае, если нужно проверить какие-то менее тривиальные сортировки для списка, можно использовать класс com.google.common.collect.Ordering.
Тест в данном случае будет таким:

```java
public class Test {

      @Test
      public void test(){
            List<ExampleClass> exampleList = asList(new ExampleClass(10, "exampleValue1", (long)100),
                    new ExampleClass(10, "exampleValue1", (long)10),
                    new ExampleClass(10, "exampleValue1", (long)-10));
            assertThat(exampleList, sorted(by(Ordering.nullsFirst())));
      }
}
```



