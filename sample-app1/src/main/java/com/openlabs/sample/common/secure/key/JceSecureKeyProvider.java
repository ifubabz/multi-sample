package com.openlabs.sample.common.secure.key;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

public class JceSecureKeyProvider implements SecureKeyProvider {

	@Override
	public byte[] getKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return keyGenerator.generateKey().getEncoded();
	}

	@Override
	public byte[] getIvParameter() {
		return "1234567891234567".getBytes();
	}

}
