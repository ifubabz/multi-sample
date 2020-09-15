package com.openlabs.sample.common.secure;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import com.openlabs.sample.common.secure.key.SecureKeyHandler;

public class DefaultStringEncryptor implements IEncryptor {

	private PooledPBEStringEncryptor encryptor;
	
	public DefaultStringEncryptor() {
		initialize();
	}
	
	private void initialize() {
        encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(SecureKeyHandler.getKey());
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
    }
	
	@Override
	public String encrypt(String message) {
		return encryptor.encrypt(message);
	}

	@Override
	public String decrypt(String encryptedMessage) {
		return encryptor.decrypt(encryptedMessage);
	}
	
}
