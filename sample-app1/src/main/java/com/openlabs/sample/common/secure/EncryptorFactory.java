package com.openlabs.sample.common.secure;

public class EncryptorFactory {
	
	private IEncryptor encryptor;
	
	private EncryptorFactory() {
		initialize();
	}
	
	private void initialize() {
		encryptor = new DefaultStringEncryptor();
	}

	private static class InnerInstanceClazz {
		private static final EncryptorFactory instance = new EncryptorFactory();
	}
	
	public static EncryptorFactory getInstance() {
		return InnerInstanceClazz.instance;
	}
	
	public IEncryptor getEncryptor() {
		return encryptor;
	}
	
}
