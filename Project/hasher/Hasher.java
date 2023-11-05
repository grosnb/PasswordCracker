package hasher;

import dataType.Triplet;
import dataType.Pair;

public interface Hasher {
    /**
     * Generates a random password of size n to be hashed using a salt.
     * @return A triplet of strings where the left value is the original
     * password, the middle string is the salt and the right string
     * is the hashed password.
     */
    public Triplet<String, String, String> randomHashN(int n);

    /**
     * Generates a random password to be hashed using a salt.
     * @return A triplet of strings where the left value is the original
     * password, the middle string is the salt and the right string
     * is the hashed password.
     */
    public Triplet<String, String, String> randomHash();

    /**
     * Hashes a given password with a random salt.
     * @param password
     * @return A pair of strings where the left value is the salt and the
     * right value is the hashed password.
     */
    public Pair<String, String> hashRandomSalt(String password);

    /**
     * Hashes a given password with a given salt.
     * @param password
     * @param salt
     * @return The hashed password as a String
     */
    public String hash(String password, String salt);

    /**
     * Hashes a given password with no salt.
     * @param password
     * @return The hashed password as a String
     */
    public String hash(String password);
} 
