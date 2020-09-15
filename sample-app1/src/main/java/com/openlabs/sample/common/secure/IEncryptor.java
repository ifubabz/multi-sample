package com.openlabs.sample.common.secure;

public interface IEncryptor {
	
    /**
     * Encrypt the input message
     * 
     * @param message the message to be encrypted
     * @return the result of encryption
     */
    public String encrypt(String message);
    
    
    /**
     * Decrypt an encrypted message
     * 
     * @param encryptedMessage the encrypted message to be decrypted
     * @return the result of decryption
     */
    public String decrypt(String encryptedMessage);
}
