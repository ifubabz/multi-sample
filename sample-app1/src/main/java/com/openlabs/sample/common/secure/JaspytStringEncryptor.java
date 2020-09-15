package com.openlabs.sample.common.secure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import com.openlabs.sample.common.secure.key.SecureKeyHandler;

public class JaspytStringEncryptor {

	private PooledPBEStringEncryptor encryptor;

	private JaspytStringEncryptor() {
		initialize();
	}
	
	private void initialize() {
		encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(new String(SecureKeyHandler.getKey()));
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
	}

	private static class InnerInstanceHolder {
		public static final JaspytStringEncryptor instance = new JaspytStringEncryptor();
	}
	
	public static JaspytStringEncryptor getInstance() {
		return InnerInstanceHolder.instance;
	}
	
	public StringEncryptor getEncryptor() {
		return encryptor;
	}
}
