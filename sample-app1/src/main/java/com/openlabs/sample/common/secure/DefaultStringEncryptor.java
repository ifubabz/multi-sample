package com.openlabs.sample.common.secure;

import org.jasypt.encryption.StringEncryptor;

public class DefaultStringEncryptor implements IEncryptor {

	private StringEncryptor encryptor = JaspytStringEncryptor.getInstance().getEncryptor();
	
	@Override
	public String encrypt(String message) {
		return encryptor.encrypt(message);
	}

	@Override
	public String decrypt(String encryptedMessage) {
		return encryptor.decrypt(encryptedMessage);
	}
	
}
