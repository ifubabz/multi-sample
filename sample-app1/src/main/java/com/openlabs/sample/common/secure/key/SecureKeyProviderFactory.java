package com.openlabs.sample.common.secure.key;

public class SecureKeyProviderFactory {

	private SecureKeyProvider secureKeyProvider;
	
	private SecureKeyProviderFactory() {
		initialize();
	}

	private void initialize() {
		secureKeyProvider = new DefaultSecureKeyProvider();
	}
	
	private static class InnerInstanceClazz {
		private static final SecureKeyProviderFactory instance = new SecureKeyProviderFactory();
	}
	
	public static SecureKeyProviderFactory getInstance() {
		return InnerInstanceClazz.instance;
	}
	
	public SecureKeyProvider getProvider() {
		return secureKeyProvider;
	}
}
