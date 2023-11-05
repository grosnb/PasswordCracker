package cracker;

import dataSet.FileScanner;
import hasher.SHA256Hash;

public class DictionaryAlgorithm implements CrackingAlgorithm {
    String fileName;

    public DictionaryAlgorithm(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String crackNoSalt(String hash, int lower, int upper) {
        return crackSalt(hash, null, lower, upper);
    }

    @Override
    public String crackSalt(String hash, String salt, int lower, int upper) {
        FileScanner fs = new FileScanner(fileName);
        int i = 0;
        while (fs.hasNext()) {
            String line = fs.nextLine();
            if (line.length() >= lower && line.length() <= upper &&
                (new SHA256Hash()).hash(line, salt).equals(hash)) {
                return line;
            }
            ++i;
        }
        System.out.println(i);
        return "No String Found\n";
    }    
}
