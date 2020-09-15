package com.openlabs.sample.common.secure;

import java.util.HashMap;
import java.util.Map;

public class EncryptorFactory {
	
	private Map<ENCRYPT_TYPE, IEncryptor> map = new HashMap<>();
	
	private EncryptorFactory() {
		initialize();
	}
	
	private void initialize() {
		map.put(ENCRYPT_TYPE.DEFAULT, new DefaultStringEncryptor());
		map.put(ENCRYPT_TYPE.JCE, new JceBase64Encryptor());
	}

	private static class InnerInstanceHolder {
		public static final EncryptorFactory instance = new EncryptorFactory();
	}
	
	public static EncryptorFactory getInstance() {
		return InnerInstanceHolder.instance;
	}
	
	public IEncryptor getEncryptor() {
		return map.get(ENCRYPT_TYPE.DEFAULT);
	}
	
	public IEncryptor getEncryptor(ENCRYPT_TYPE encryptType) {
		return map.get(encryptType);
	}

	public enum ENCRYPT_TYPE {
		DEFAULT, JCE
	}
}