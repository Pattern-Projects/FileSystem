package fileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TextFile {
    Path path;

    public TextFile (Path location) {
        path = location.resolve(
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("MM-dd-yy-hh-mm-ss"))
                        + ".txt");
        createFile();
    }

    public TextFile (Path location, String name) {
        path = location.resolve(name);
        createFile();
    }

    public void createFile () {

        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path))
                Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append (String line){
        try {
            Files.writeString(path, line+"\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read () throws IOException{
        try (var reader = Files.newBufferedReader(path)) {
            System.out.println("Reading from file: ");
            String currentLine;
            while ((currentLine = reader.readLine()) != null)
                System.out.println(currentLine);
        }

    }
}
