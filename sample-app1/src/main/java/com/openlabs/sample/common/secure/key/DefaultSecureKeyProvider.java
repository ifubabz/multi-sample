package com.openlabs.sample.common.secure.key;

public class DefaultSecureKeyProvider implements SecureKeyProvider {

	@Override
	public String getKey() {
		return "password";
	}

	@Override
	public String getIvParameter() {
		return "";
	}
}
