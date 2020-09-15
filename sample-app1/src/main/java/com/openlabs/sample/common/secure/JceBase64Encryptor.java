package com.openlabs.sample.common.secure;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.openlabs.sample.common.secure.key.SecureKeyProvider;
import com.openlabs.sample.common.secure.key.SecureKeyProviderFactory;

public class JceBase64Encryptor implements IEncryptor {

	private static final String algorithm = "AES";
	private static final String transformation = "AES/CBC/PKCS5Padding";
	
	private Cipher encCipher;
	private Cipher decCipher;
	
	public JceBase64Encryptor() {
		initialize();
	}
	
	private void initialize() {
		SecureKeyProvider secureKeyProvider = SecureKeyProviderFactory.getInstance().getProvider();
		byte[] key = secureKeyProvider.getKey();
		byte[] ivParameter = secureKeyProvider.getIvParameter();

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter);
		
		try {
			
			encCipher = Cipher.getInstance(transformation);
			encCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			decCipher = Cipher.getInstance(transformation);
			decCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public String encrypt(String message) {
		try {
			byte[] encrypted = encCipher.doFinal(message.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String decrypt(String encryptedMessage) {
        try {
    		byte[] ciphered = Base64.getDecoder().decode(encryptedMessage.getBytes());
            return new String(decCipher.doFinal(ciphered));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
}
