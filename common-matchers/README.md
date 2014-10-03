# Common Matchers  
 
Same as URI matcher
-------------------

Usage: 
```java
assertThat(URI.create("http://yandex.ru"), sameAsURI(URI.create("http://ya.ru")));
``` 

It writes message: 
```
Expected: URI same as: <http://ya.ru>
     but: was <http://yandex.ru>
	diff: "http://[ya->yandex].ru" 
``` 

Can be used with filters:    
```java
assertThat(URI.create("https://ya.ru/?param=1"), sameAsURI(URI.create("http://ya.ru")))
                .filteredWith(param("param"), scheme(HTTP_SCHEME, HTTPS_SCHEME));
``` 


Uses [ru.lanwen.diff:uri-differ-lib](https://github.com/yandex-qatools/uri-differ) as backend



