# Collection Matchers

## ContainsUniqueItems#containsUniqueItems

Matches only if collection has unique elements

```java
@Test
public void assertionShouldNotBeThrownWhenThereAreNoDuplicates() {
    List<String> collectionWithDuplicates = asList("veni", "vidi", "vici");
    assertThat(collectionWithDuplicates, containsUniqueItems());
}
```

## HasSameItemsAsListMatcher#hasSameItemsAsList

What if we want to compare two lists. But we also want to know all the difference between them.
HasSameItemsAsListMatcher helps with it:

```java
@Test(expected = AssertionError.class)
public void listNotEqualSortedAndNotContainsSomeItems() throws Exception {
    List<String> actual = asList("1", "2", "3");
    List<String> expected = asList("3", "2", "1", "4");

    assertThat(actual, hasSameItemsAsList(expected));
}
```

It prints:

```
Expected: Lists contains same items
     but:
(In Expected but not in Actual) [<1>]:
-> <4>
```

Also we can check the sorting order with help of mode ``.sameSorted()``

```java
@Test
public void listNotSameOrderButEqual() throws Exception {
    List<String> actual = asList("1", "2", "3");
    List<String> expected = asList("3", "2", "1");

    assertThat(actual, hasSameItemsAsList(expected).sameSorted());
}
```

It prints

```
Expected: Lists contains same items and sorted equally
     but:
(Not sorted correctly) [<2>]:
-> "Expected 3 on position [0], but was - 1"
-> "Expected 1 on position [2], but was - 3"
```

### HasSameItemsAsCollectionMatcher#hasSameItemsAsCollection

This matcher is same as matcher for list, but can't compare sorting order because of use jdk collections classes
It prints:
```
Expected: collections contains same items
     but:
(Not equal frequency) [<2>]:
-> "3 - expected [2] times, but frequency was - [1]"
-> "2 - expected [2] times, but frequency was - [1]"
```



## Extended usage of HasSameItemsAsListMatcher

We can define new output of every element that don't matches in case of failure.
Also we can override the equality method with help of this matcher

* First, we need to override `equals` behaviour. For example we want to compare two lists with non case sensitive matching:
```java
List<String> actual = asList("aBc", "DEf", "gHi");
List<String> expected = asList("Abc", "DeF", "gHI");
```

* Extend abstract class `Wrapper<String>` - don't forget to use generic `String` as we compare strings.
Now we should override 2 methods - `safelyEquals` and `asString`
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
Parent abstract class already check for null all elements before pass it to child methods

* Next, implement interface of wrapper factory - for that override only one method: `Wrapper<String> newWrapper()`

```java
public class IgnoreCaseWrapperFactory implements WrapperFactory<String> {
   @Override
   public Wrapper<String> newWrapper() {
        return new IgnoreCaseStringWrapper();
   }
}
```

* Then, pass factory object to be used for producing new wrappers

```java
@Test
public void useCustomWrapper() throws Exception {
   ...
   assertThat(actual, hasSameItemsAsList(expected).useWrapperFactory(new IgnoreCaseWrapperFactory()));
}
```
