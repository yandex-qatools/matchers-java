package ru.yandex.qatools.matchers.nio;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.nio.file.LinkOption;
import java.nio.file.Path;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 04.05.14
 */
public final class PathMatchers {

    PathMatchers() {
    }

    @Factory
    public static Matcher<Path> contains(String fileName) {
        return new DirectoryContains(fileName);
    }

    @Factory
    public static Matcher<Path> isDirectory() {
        return new IsDirectory();
    }

    @Factory
    public static Matcher<Path> exists(LinkOption... options) {
        return new Exists(options);
    }

    @Factory
    public static Matcher<Path> hasFilesCount(int expectedCount) {
        return new FilesCount(expectedCount);
    }

    @Factory
    public static Matcher<Path> hasFilesCount(int expectedCount, String glob) {
        return new FilesCount(expectedCount, glob);
    }

}
