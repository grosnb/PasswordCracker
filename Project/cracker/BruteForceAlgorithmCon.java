package cracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dataType.Pair;
import hasher.SHA256Callable;

public class BruteForceAlgorithmCon implements CrackingAlgorithm {
    private ExecutorService service = Executors.newFixedThreadPool(8);
    private List<Pair<String, Future<String>>> list = new ArrayList<>();

    @Override
    public String crackNoSalt(String hash, int lower, int upper) {
        return crackSalt(hash, null, lower, upper);
    }

    @Override
    public String crackSalt(String hash, String salt, int lower, int upper) {
        for (int i = lower; i < upper; i++) {
            String s = stringIncrement("a".repeat(i).toCharArray(), i - 1, hash, salt);
            if (s != null) return s;
        }
        return "No String Found\n";
    }
    
    private String stringIncrement(char[] string, int pos, String hash, String salt) {
        if (pos < 0) {
            return null;
        }
        while (true) {
            // Hash string and check
            list.add(new Pair<String, Future<String>>(String.valueOf(string),
                service.submit((new SHA256Callable(String.valueOf(string), salt)))));

            String s = stringIncrement(string.clone(), pos - 1, hash, salt);
            if (s != null) return s;

            if (string[pos] >= 'a' && string[pos] < 'z') {
                string[pos]++;
            } else if (string[pos] == 'z') {
                string[pos] = '0';
            } else if (string[pos] >= '0' && string[pos] < '9') {
                string[pos]++;
            } else if (string[pos] == '9') break;
        }
        for (int i = 0; i < list.size(); ++i) {
            Pair<String, Future<String>> entry = list.remove(0);
            try {
                if (entry.getRight().get().equals(hash)) {
                    service.shutdownNow();
                    return entry.getLeft();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
