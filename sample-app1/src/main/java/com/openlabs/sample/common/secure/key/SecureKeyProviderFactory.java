package com.openlabs.sample.common.secure.key;

import java.util.HashMap;
import java.util.Map;

public class SecureKeyProviderFactory {

	private Map<SECURE_KEY_TYPE, SecureKeyProvider> map = new HashMap<>();
	
	private SecureKeyProviderFactory() {
		initialize();
	}

	private void initialize() {
		map.put(SECURE_KEY_TYPE.DEFAULT, new DefaultSecureKeyProvider());
		map.put(SECURE_KEY_TYPE.JCE, new JceSecureKeyProvider());
	}
	
	private static class InnerInstanceHolder {
		public static final SecureKeyProviderFactory instance = new SecureKeyProviderFactory();
	}
	
	public static SecureKeyProviderFactory getInstance() {
		return InnerInstanceHolder.instance;
	}
	
	public SecureKeyProvider getProvider() {
		return map.get(SECURE_KEY_TYPE.DEFAULT);
	}
	
	public SecureKeyProvider getProvider(SECURE_KEY_TYPE secureKeyType) {
		return map.get(secureKeyType);
	}
	
	public enum SECURE_KEY_TYPE {
		DEFAULT, JCE
	}
}
