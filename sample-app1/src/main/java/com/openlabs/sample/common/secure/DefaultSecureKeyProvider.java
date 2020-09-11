package com.openlabs.sample.common.secure;

public class DefaultSecureKeyProvider implements SecureKeyProvider {

	@Override
	public String getKey() {
		return "password";
	}

}
