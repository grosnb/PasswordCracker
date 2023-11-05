package hasher;

import dataType.Triplet;

public class HashInterface {
    public static void main(String[] args) {
        Hasher h = new SHA256Hash();
        Triplet<String, String, String> t = h.randomHash();
        System.out.println("PW: " + t.getLeft() + "\t|\t" + "Salt: " + t.getMid() + "\t|\t" + "Hash: " + t.getRight());
    }
}
