package ru.yandex.qatools.matchers.nio;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.newDirectoryStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 09.10.15
 */
public class FilesCount extends TypeSafeMatcher<Path> {

    public static final String DEFAULT_GLOB = "*";

    private final String glob;

    private final int expectedCount;

    private int count = 0;

    public FilesCount(int expectedCount) {
        this(expectedCount, DEFAULT_GLOB);
    }

    public FilesCount(int expectedCount, String glob) {
        this.expectedCount = expectedCount;
        this.glob = glob;
    }

    @Override
    protected boolean matchesSafely(Path item) {
        count = 0;
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(item, glob)) {
            for (Path path : directoryStream) {
                if (!Files.isDirectory(path)) {
                    count++;
                }
            }
        } catch (IOException e) { //NOSONAR
            return false;
        }
        return expectedCount == count;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("directory contains ").appendValue(expectedCount)
                .appendText(" files");
        if (!DEFAULT_GLOB.equals(glob)) {
            description.appendText(" with glob ").appendValue(glob);
        }
    }

    @Override
    protected void describeMismatchSafely(Path item, Description mismatchDescription) {
        mismatchDescription.appendText("directory contains ").appendValue(count)
                .appendText(" files");
        if (!DEFAULT_GLOB.equals(glob)) {
            mismatchDescription.appendText(" with glob ").appendValue(glob);
        }
    }
}
