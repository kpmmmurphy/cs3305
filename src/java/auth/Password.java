package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Password class housing methods for password hashing and salt generation
 *
 *
 * @author Kevin Murphy
 * @version 1.0
 * @date 12/2/14
 */
public class Password {

    /* Debug mechinism for testing */
    private final boolean DEBUG_ON = true;
    
    /* Password to be salted and hashed */
    private final String  password;
    /* The result of a salted and hashed password */
    private final String  hashedPassword;
    /* The salt value for stronger encrytion */
    private final String  salt;
    
    /**
     * Creates Password object, calls the getSalt and the 
     * get_SHA_1_SecurePassword methods, generating a secure hashed 
     * value for the given password
     * 
     * @param password 
     */
    public Password(String password){
        salt           = getSalt();
        this.password  = password;
        hashedPassword = get_SHA_1_SecurePassword(this.password, this.salt);
    }
    
    /**
     * Overloaded constructor
     * 
     * Creates Password object, takes the given password and
     * salt values and calls the get_SHA_1_SecurePassword methods, 
     * generating a the  hashed value for the given password
     * 
     * @param password 
     * @param salt 
     */
    public Password(String password, String salt){
        this.salt      = salt;
        this.password  = password;
        hashedPassword = get_SHA_1_SecurePassword(this.password, this.salt);
    }
    
    public static void main(String[] args){
        testPasswordClass();
    }
    
    /**
     * Test method for the class. 
     * Shows how to use the methods present.
     */
    private static void testPasswordClass(){
        Password passwordOne = new Password("password1234");
        System.out.println("Salt value 1: " + passwordOne.getSaltValue());
        System.out.println("Hashed Password 1: " + passwordOne.getHashedPassword());
        
        Password passwordTwo = new Password("password1234");
        System.out.println("Salt value 2: " + passwordTwo.getSaltValue());
        System.out.println("Hashed Password 2: " + passwordTwo.getHashedPassword());
    }
    
    /**
     * Source:
     * http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     *
     * Hashes values using the SHA1 hash function 
     *
     * @param passwordToHash To be hashed
     * @param salt           Salt value to be used when hashing
     * @return
     */
    private static String get_SHA_1_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes     = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * Source: http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     *
     * Generates the salt value to be used in when computing 
     * the hash value of the given password
     * 
     * @return String 
     * @throws NoSuchAlgorithmException 
     */
    private static String getSalt(){
        SecureRandom sr;
        byte[] salt     = new byte[16];
        new Random().nextBytes(salt);
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
        return salt.toString();
    }
   
    /**
     * Retrieves the hashed password value which the method
     * get_SHA_1_SecurePassword has generated 
     * 
     * @return String
     */
    public String getHashedPassword(){
        return hashedPassword;
    }
    
    /**
     * Gets the salt value generated by the getSalt method of this class
     * 
     * @return String 
     */
    public String getSaltValue(){
        return salt;
    }
   
}
