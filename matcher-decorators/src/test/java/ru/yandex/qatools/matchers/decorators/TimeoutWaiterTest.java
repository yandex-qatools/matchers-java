package ru.yandex.qatools.matchers.decorators;

import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * User: lanwen
 * Date: 30.09.14
 * Time: 17:52
 */
public class TimeoutWaiterTest {

    @Test
    public void shouldReturn30SecondsDescriptionByDefault() throws Exception {
        StringDescription description = new StringDescription();
        TimeoutWaiter.timeoutHasExpired().describeTo(description);

        assertThat("Should format 30 seconds by default", description.toString(), equalTo("30 seconds"));
    }

    @Test
    public void shouldReturnMinutesInDescription() throws Exception {
        StringDescription description = new StringDescription();
        TimeoutWaiter.timeoutHasExpired(TimeUnit.MINUTES.toMillis(2)).describeTo(description);

        assertThat("Should format millis as minutes if can", description.toString(), equalTo("2 minutes"));
    }
}
