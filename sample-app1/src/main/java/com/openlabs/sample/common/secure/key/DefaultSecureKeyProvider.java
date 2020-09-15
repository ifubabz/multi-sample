package com.openlabs.sample.common.secure.key;

public class DefaultSecureKeyProvider implements SecureKeyProvider {

	@Override
	public byte[] getKey() {
		return "passwordpassword".getBytes();
	}

	@Override
	public byte[] getIvParameter() {
		return new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	}
}
