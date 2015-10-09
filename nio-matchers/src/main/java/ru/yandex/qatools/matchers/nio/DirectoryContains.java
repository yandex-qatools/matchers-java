package ru.yandex.qatools.matchers.nio;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 09.10.15
 */
public class DirectoryContains extends TypeSafeMatcher<Path> {

    private String fileName;

    public DirectoryContains(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected boolean matchesSafely(Path directory) {
        return Files.exists(directory.resolve(fileName));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("contains file ").appendValue(fileName);
    }

    @Override
    protected void describeMismatchSafely(Path item, Description description) {
        description.appendText("not contains file ").appendValue(fileName);
    }
}
