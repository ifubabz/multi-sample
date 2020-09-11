package com.openlabs.sample.secure;

public class DefaultSecureKeyProvider implements SecureKeyProvider {

	@Override
	public String getKey() {
		return "password";
	}

}
