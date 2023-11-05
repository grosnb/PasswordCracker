package hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class SHA256Callable implements Callable<String> {
    String password;
    String salt;

    public SHA256Callable(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String call() throws Exception {
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
}
