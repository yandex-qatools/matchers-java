# Matchers Java
[![release](http://github-release-version.herokuapp.com/github/yandex-qatools/matchers-java/release.svg?style=flat)](https://github.com/yandex-qatools/matchers-java/releases/latest)
[![Coverage Status](https://coveralls.io/repos/yandex-qatools/matchers-java/badge.svg)](https://coveralls.io/r/yandex-qatools/matchers-java)

Collection of useful Hamcrest's matchers for everyday testers life.

### Usage:
Choose one of the modules and add to your `pom.xml`

```xml
<dependency>
    <groupId>ru.yandex.qatools.matchers</groupId>
    <artifactId>{module.artifactId}</artifactId>
    <version>{lib.release.version}</version>
</dependency>
```


- [**WebDriver Matchers**](https://github.com/yandex-qatools/matchers-java/tree/master/webdriver-matchers)  
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/webdriver-matchers/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/webdriver-matchers)  
  `webdriver-matchers` - Matchers library to work with WebDriver WebElement interface.

- [**Collections Matchers**](https://github.com/yandex-qatools/matchers-java/tree/master/collection-matchers)  
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/collection-matchers/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/collection-matchers)  
  `collection-matchers` - Matchers library to work with collections.

- [**Common Matchers**](https://github.com/yandex-qatools/matchers-java/tree/master/common-matchers)  
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/common-matchers/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/common-matchers)  
  `common-matchers` - Matchers library with matchers for common use cases.

- [**Matcher Decorators**](https://github.com/yandex-qatools/matchers-java/tree/master/matcher-decorators)  
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/matcher-decorators/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.matchers/matcher-decorators)  
  `matcher-decorators` - Decorators which allows handle additional condition issues (e.g. timeouts) directly in the assertion statements.


### Also:
  You can find more useful libs and examples on [wiki](https://github.com/yandex-qatools/matchers-java/wiki)
