package cracker;

/**
 * For the purposes of this project/program, only the SHA-256 hashing algorithm will be used.
 */
public interface CrackingAlgorithm {

    /**
     * Attempts to crack a hashed password that has a length between the lower and upper bounds.
     * @param hash
     * @param lower
     * @param upper
     * @return
     */
    public String crackNoSalt(String hash, int lower, int upper);

    /**
     * Attempts to crack a password hashed with a salt that has a length between the lower and upper bounds.
     * @param hash
     * @param salt
     * @param lower
     * @param upper
     * @return
     */
    public String crackSalt(String hash, String salt, int lower, int upper);
}
