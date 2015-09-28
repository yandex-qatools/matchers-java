package ru.yandex.qatools.matchers.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * @author Vladislav Bauer
 */

public class WrapperTest {

    @Test
    public void testEqualsReflexive() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();

        Assert.assertThat(
            "Equals method should be reflexive",
            objectWrapper1, equalTo(objectWrapper1)
        );
    }

    @Test
    public void testEqualsSymmetric() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();
        Wrapper<Object> objectWrapper2 = createTestWrapper().wrap(objectWrapper1);
        Wrapper<Object> objectWrapper3 = createTestWrapper().wrap(objectWrapper1);
        String errorMessage = "Equals method should be symmetric";

        Assert.assertThat(errorMessage, objectWrapper2, equalTo(objectWrapper3));
        Assert.assertThat(errorMessage, objectWrapper3, equalTo(objectWrapper2));

        Assert.assertThat(errorMessage, objectWrapper1, not(equalTo(objectWrapper2)));
        Assert.assertThat(errorMessage, objectWrapper2, not(equalTo(objectWrapper1)));
    }

    @Test
    public void testEqualsTransitive() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();
        Wrapper<Object> objectWrapper2 = createTestWrapper().wrap(objectWrapper1);
        Wrapper<Object> objectWrapper3 = createTestWrapper().wrap(objectWrapper1);
        Wrapper<Object> objectWrapper4 = createTestWrapper().wrap(objectWrapper1);
        String errorMessage = "Equals method should be transitive";

        Assert.assertThat(errorMessage, objectWrapper2, equalTo(objectWrapper3));
        Assert.assertThat(errorMessage, objectWrapper3, equalTo(objectWrapper4));
        Assert.assertThat(errorMessage, objectWrapper2, equalTo(objectWrapper4));
    }

    @Test
    public void testEqualsConsistent() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();
        Wrapper<Object> objectWrapper2 = createTestWrapper().wrap(objectWrapper1);
        Wrapper<Object> objectWrapper3 = createTestWrapper().wrap(objectWrapper1);

        final int attempts = 10;
        for (int i = 0; i < attempts; i++) {
            Assert.assertThat(
                "Equals method should be consistent",
                objectWrapper2, equalTo(objectWrapper3)
            );
        }
    }

    @Test
    public void testEqualsWithNull() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();

        Assert.assertThat(
            "For any non-null reference value x, x.equals(null) should return false",
            objectWrapper1, not(equalTo(null))
        );
    }

    @Test
    public void testEqualsWithWrongArgument() {
        Wrapper<Object> objectWrapper1 = createTestWrapper();

        Assert.assertThat(
            "Equals method should return false for argument with incompatible class",
            objectWrapper1, not(equalTo(new Object()))
        );
    }


    private Wrapper<Object> createTestWrapper() {
        return new Wrapper<Object>() {
            @Override
            public boolean safelyEquals(Object actual, Object expected) {
                return Objects.equals(actual, expected);
            }

            @Override
            public String asString(Object obj) {
                return Objects.toString(obj, null);
            }
        };
    }

}
