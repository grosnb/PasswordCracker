package cracker;

import hasher.SHA256Hash;

public class BruteForceAlgorithm implements CrackingAlgorithm {

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
    
    private static String stringIncrement(char[] string, int pos, String hash, String salt) {
        if (pos < 0) {
            return null;
        }
        while (true) {
            // Hash string and check
            if ((new SHA256Hash()).hash(String.valueOf(string), salt).equals(hash)) {
                return String.valueOf(string);
            }

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
        return null;
    }
}
