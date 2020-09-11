package com.openlabs.sample.common.secure;

public class SecureKeyProviderFactory {

	public static SecureKeyProvider getProvider() {
		return new DefaultSecureKeyProvider();
	}
}
