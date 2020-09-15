package com.openlabs.sample.common.secure.key;

public class SecureKeyHandler {

	private static SecureKeyProvider provider = SecureKeyProviderFactory.getInstance().getProvider();

	public static byte[] getKey() {
		return provider.getKey();
	}

	public static byte[] getIvParameter() {
		return provider.getIvParameter();
	}
}
