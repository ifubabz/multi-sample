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

import com.openlabs.sample.common.secure.key.SecureKeyHandler;
import com.openlabs.sample.exception.CommonException;
import com.openlabs.sample.exception.ErrorCode;

public class JceBase64Encryptor implements IEncryptor {

	private static final String algorithm = "AES";
	private static final String transformation = "AES/CBC/PKCS5Padding";
	
	private Cipher encCipher;
	private Cipher decCipher;
	
	public JceBase64Encryptor() {
		initialize();
	}
	
	private void initialize() {
		byte[] key = SecureKeyHandler.getKey().getBytes();
		byte[] ivParameter = SecureKeyHandler.getIvParameter().getBytes();

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter);
		
		try {
			
			encCipher = Cipher.getInstance(transformation);
			encCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			decCipher = Cipher.getInstance(transformation);
			decCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	public String encrypt(String message) {
		byte[] encrypted = new byte[0];
		try {
			encrypted = encCipher.doFinal(message.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
        return Base64.getEncoder().encodeToString(encrypted);
	}

	@Override
	public String decrypt(String encryptedMessage) {
		byte[] ciphered = Base64.getDecoder().decode(encryptedMessage.getBytes());
		byte[] decrypted = new byte[0];
        try {
        	decrypted = decCipher.doFinal(ciphered);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
        return new String(decrypted);
	}
	
}
