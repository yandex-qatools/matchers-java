package ru.yandex.qatools.matchers.nio;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 09.10.15
 */
public class Exists extends TypeSafeMatcher<Path> {

    private final LinkOption[] options;

    public Exists(LinkOption... options) {
        this.options = options;
    }

    @Override
    protected boolean matchesSafely(Path item) {
        return Files.exists(item, options);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("file exists");
    }
}
