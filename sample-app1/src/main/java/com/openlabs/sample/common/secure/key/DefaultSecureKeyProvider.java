package com.openlabs.sample.common.secure.key;

public class DefaultSecureKeyProvider implements SecureKeyProvider {

	@Override
	public byte[] getKey() {
		return "password".getBytes();
	}

	@Override
	public byte[] getIvParameter() {
		return "1234567891234567".getBytes();
	}
}
