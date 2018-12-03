package org.jabref.model.util;

import org.jabref.Globals;
import org.jabref.model.database.BibDatabaseContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FileHelperTest {
    private static File file, file2;

    @BeforeAll
    static void setUp() {
        try {
            file = new File("test.txt");
            file2 = new File("test");
            file.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanUp(){
        file.delete();
        file2.delete();
    }

    @Test
    void testFileExtension(){
        Optional<String> optional = Optional.of("txt");
        assertEquals(optional, FileHelper.getFileExtension(file.getName()));
    }

    @Test
    void testFileWithoutExtension(){
        Optional<String> optional = Optional.empty();
        assertEquals(optional, FileHelper.getFileExtension(file2.getName()));
    }

    @Test
    void testFilePathExtension(){
        Path path = file.toPath();
        Optional<String> optional = Optional.of("txt");
        assertEquals(optional, FileHelper.getFileExtension(path));
    }

    @Test
    void testFilePathWithoutExtension(){
        Path path = file2.toPath();
        Optional<String> optional = Optional.empty();
        assertEquals(optional, FileHelper.getFileExtension(path));
    }

    @Test
    void testExpandFilenameAsPath(){
        List<Path> directories = new ArrayList<>();
        directories.add(Paths.get(file.getAbsolutePath().
                substring(0,file.getAbsolutePath().lastIndexOf(File.separator))));

        assertEquals(Optional.of(file.getAbsoluteFile()).toString(), FileHelper.expandFilenameAsPath("test.txt", directories).toString());
    }

    @Test
    void testExpandFilenameAsPathEmptyResult(){
        List<Path> directories = new ArrayList<>();
        directories.add(Paths.get(file.getAbsolutePath().
                substring(0,file.getAbsolutePath().lastIndexOf(File.separator))));

        assertEquals(Optional.empty().toString(), FileHelper.expandFilenameAsPath("test2.txt", directories).toString());
    }
}
