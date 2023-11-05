package hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import dataType.Triplet;
import dataType.Pair;

public class SHA256Hash implements Hasher {

    @Override
    public Triplet<String, String, String> randomHashN(int n) {
        String password = getUniqueStringN(n);
        Pair<String, String> pair = hashRandomSalt(password);
        return new Triplet<String, String, String>(password, pair.getLeft(), pair.getRight());
    }

    @Override
    public Triplet<String, String, String> randomHash() {
        String password = getUniqueString();
        Pair<String, String> pair = hashRandomSalt(password);
        return new Triplet<String, String, String>(password, pair.getLeft(), pair.getRight());
    }

    @Override
    public Pair<String, String> hashRandomSalt(String password) {
        String salt = getUniqueString();
        String hashedPassword = hash(password, salt);
        return new Pair<String, String>(salt, hashedPassword);
    }

    @Override
    public String hash(String password, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if (salt != null) {
                md.update(salt.getBytes());
            }
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; ++i) {
                sb.append(String.format("%02x", bytes[i]));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    @Override
    public String hash(String password) {
        return hash(password, null);
    }
    
    private static String getUniqueString() {
        UUID randomUUID = UUID.randomUUID();
        String string = randomUUID.toString().replaceAll("-", "");
        return string;
    }

    private static String getUniqueStringN(int n) {
        UUID randomUUID = UUID.randomUUID();
        String string = randomUUID.toString().replaceAll("-", "");
        return string.substring(0, n);
    }
}
