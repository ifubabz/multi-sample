package com.openlabs.sample.common.secure.key;

public interface SecureKeyProvider {

	public byte[] getKey();
	
	public byte[] getIvParameter();
	
}
