package cracker;

import dataSet.FileScanner;
import dataType.Pair;
import dataType.Triplet;
import hasher.StaticSHA256;

public class Cracker {
    public static void main(String[] args) {
        Triplet<String, String, String> t = StaticSHA256.randomHashN(4);
        System.out.println("PW: " + t.getLeft() + "\t|\t" + "Salt: " + t.getMid() + "\t|\t" + "Hash: " + t.getRight());
        long startT = System.nanoTime();
        String s = (new BruteForceAlgorithm()).crackSalt(t.getRight(), t.getMid(), 3, 8);
        long endT = System.nanoTime();
        System.out.println("Cracked password: " + s + "\t|\t in " + (endT - startT));

        startT = System.nanoTime();
        s = (new BruteForceAlgorithmCon()).crackSalt(t.getRight(), t.getMid(), 3, 8);
        endT = System.nanoTime();
        System.out.println("Cracked password: " + s + "\t|\t in " + (endT - startT));        

        String url = "C:\\Users\\Jared\\Desktop\\University\\COMP6841\\Project\\dataSet\\rockyou.txt";
        FileScanner fs = new FileScanner(url);
        String randomPassword = fs.randomLine();
        Pair<String, String> p = StaticSHA256.hashRandomSalt(randomPassword);
        s = (new DictionaryAlgorithm(url)).crackSalt(p.getRight(), p.getLeft(), 0, 30);
        System.out.println("PW: " + randomPassword + "\t|\t" + "Salt: " + p.getLeft() + "\t|\t" + "Hash: " + p.getRight());
        System.out.println("Cracked password: " + s);
    }
}
