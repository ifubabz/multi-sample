package com.openlabs.sample.common.secure.key;

public class SecureKeyHandler {

	private static SecureKeyProvider provider = SecureKeyProviderFactory.getInstance().getProvider();

	public static String getKey() {
		return provider.getKey();
	}

	public static String getIvParameter() {
		return provider.getIvParameter();
	}
}
