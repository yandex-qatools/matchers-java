package ru.yandex.qatools.matchers.nio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static ru.yandex.qatools.matchers.nio.PathMatchers.contains;
import static ru.yandex.qatools.matchers.nio.PathMatchers.exists;
import static ru.yandex.qatools.matchers.nio.PathMatchers.hasFilesCount;
import static ru.yandex.qatools.matchers.nio.PathMatchers.isDirectory;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 09.10.15
 */
public class PathMatchersTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Path directory;

    @Before
    public void setUp() throws Exception {
        directory = folder.newFolder().toPath();
    }

    @Test
    public void shouldContains() throws Exception {
        Files.write(directory.resolve("file"), "content".getBytes(StandardCharsets.UTF_8));
        assertThat(directory, contains("file"));
    }

    @Test(expected = AssertionError.class)
    public void shouldFailIfNotContains() throws Exception {
        assertThat(directory, contains("file"));
    }

    @Test
    public void shouldNotContains() throws Exception {
        assertThat(directory, not(contains("file")));
    }

    @Test
    public void shouldIsDirectory() throws Exception {
        assertThat(directory, isDirectory());
    }

    @Test(expected = AssertionError.class)
    public void shouldFailIfNotADirectory() throws Exception {
        assertThat(directory.resolve("file"), isDirectory());
    }

    @Test
    public void shouldNotIsDirectory() throws Exception {
        Path file = directory.resolve("file");
        Files.write(file, "content".getBytes(StandardCharsets.UTF_8));
        assertThat(file, not(isDirectory()));
    }

    @Test
    public void shouldNotFailIfFileDoesNotExists() throws Exception {
        Path file = directory.resolve("file");
        assertThat(file, not(isDirectory()));
    }

    @Test
    public void shouldExists() throws Exception {
        Path file = directory.resolve("file");
        Files.write(file, "content".getBytes(StandardCharsets.UTF_8));
        assertThat(file, exists());
    }

    @Test(expected = AssertionError.class)
    public void shouldFailIfNotExists() throws Exception {
        assertThat(directory.resolve("file"), exists());
    }

    @Test
    public void shouldNotExists() throws Exception {
        Path file = directory.resolve("file");
        assertThat(file, not(exists()));
    }

    @Test
    public void shouldSymlinkNotExists() throws Exception {
        Path file = directory.resolve("file");
        Path fileLinked = directory.resolve("file-linked");
        Files.createSymbolicLink(file, fileLinked);
        assertThat(file, not(exists()));
    }

    @Test
    public void shouldSymlinkExistsIfOptionConfigured() throws Exception {
        Path file = directory.resolve("file");
        Path fileLinked = directory.resolve("file-linked");
        Files.createSymbolicLink(file, fileLinked);

        System.out.println(Files.isSymbolicLink(file));
        assertThat(file, exists(LinkOption.NOFOLLOW_LINKS));
    }

    @Test
    public void shouldHasFilesCount() throws Exception {
        Files.write(directory.resolve("first"), "content".getBytes(StandardCharsets.UTF_8));
        Files.write(directory.resolve("second"), "content".getBytes(StandardCharsets.UTF_8));
        assertThat(directory, hasFilesCount(2));
    }

    @Test
    public void shouldHasFilesCountByGlob() throws Exception {
        Files.write(directory.resolve("first-file"), "content".getBytes(StandardCharsets.UTF_8));
        Files.write(directory.resolve("second-file"), "content".getBytes(StandardCharsets.UTF_8));
        Files.write(directory.resolve("other"), "content".getBytes(StandardCharsets.UTF_8));
        assertThat(directory, hasFilesCount(2, "*-file"));
    }

    @Test(expected = AssertionError.class)
    public void shouldFailIfNotHasSuchFiles() throws Exception {
        assertThat(directory, hasFilesCount(2));
    }

    @Test(expected = AssertionError.class)
    public void shouldFailIfNotHasSuchFilesByGlob() throws Exception {
        Files.write(directory.resolve("first"), "content".getBytes(StandardCharsets.UTF_8));
        assertThat(directory, hasFilesCount(2, "*-file"));
    }
}