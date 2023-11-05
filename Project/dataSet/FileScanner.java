package dataSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class FileScanner {
    private List<String> lines;
    private String fileName;
    private int lineIndex = 0;

    public FileScanner(String fileName) {
        this.fileName = fileName;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Precondition: hasNext() evaluates to true.
     * @return The next string from the chosen file.
     */
    public String nextLine() {
        String s = lines.get(lineIndex);
        lineIndex += 1;
        return s;
    }

    /**
     *
     * @return A random string from the chosen file.
     */
    public String randomLine() {
        int n = (new Random()).nextInt(lines.size());
        return lines.get(n);
    }

    public String getFileName() {
        return fileName;
    }

    public boolean hasNext() {
        return (lineIndex < lines.size());
    }
}
