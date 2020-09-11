package com.openlabs.sample.common.secure;

public class SecureKeyHandler {

	public static String getKey() {
		SecureKeyProvider provider = SecureKeyProviderFactory.getProvider();
		return provider.getKey();
	}

}
