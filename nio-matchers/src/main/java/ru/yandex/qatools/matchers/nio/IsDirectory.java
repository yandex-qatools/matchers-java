package ru.yandex.qatools.matchers.nio;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 09.10.15
 */
public class IsDirectory extends TypeSafeMatcher<Path> {

    @Override
    protected boolean matchesSafely(Path item) {
        return Files.isDirectory(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("to be a directory");
    }

    @Override
    protected void describeMismatchSafely(Path item, Description description) {
        description.appendValue(item).appendText(" is not a directory");
    }
}
