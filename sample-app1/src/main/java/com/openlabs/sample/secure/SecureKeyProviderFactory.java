package com.openlabs.sample.secure;

public class SecureKeyProviderFactory {

	public static SecureKeyProvider getProvider() {
		return new DefaultSecureKeyProvider();
	}
}
