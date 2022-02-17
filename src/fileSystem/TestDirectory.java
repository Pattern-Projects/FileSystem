package fileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestDirectory {
Path path;

    public static TestDirectory allDefault() throws IOException {
        //Default location and file generation
        var directory = TestDirectory.defaultLocation();
        directory.generateDefault();
        return directory;
    }

    public static TestDirectory defaultLocation() {
        //Returns TestDirectory at default location
        var location = (Path.of("..\\test_folder\\output"));
        System.out.println("Test Directory default location is: " + location.toAbsolutePath());
        return new TestDirectory(location);
    }

    public TestDirectory(Path location) {
        path = location;
    }

    public void generate(Stream<String> stream) throws IOException{
        // Create directories from strings
        try {
            stream.forEachOrdered(directory -> {
                try {
                    Files.createDirectories(path.resolve(Path.of(directory)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateFromFile(Path location) throws IOException{
        // Collect directory location strings from file
        try (var reader = Files.newBufferedReader(location)) {
            var lines = reader.lines();
            generate(lines);
        }
    }

    public void generateDefault() throws IOException {
        // Collect default directory location strings
        var directories = new ArrayList<String>();
        directories.add("abc/def/ghi/test1");
        directories.add("abc/def/ghi/test2");
        directories.add("abc/def/ghi/test3");
        directories.add("abc/def/ghi/test4");

        directories.add("abc/def/test1");
        directories.add("abc/def/test2");
        directories.add("abc/def/test3");
        directories.add("abc/def/test4");

        directories.add("abc/test1");
        directories.add("abc/test2");
        directories.add("abc/test3");
        directories.add("abc/test4");

        generate(directories.stream());
    }

}
