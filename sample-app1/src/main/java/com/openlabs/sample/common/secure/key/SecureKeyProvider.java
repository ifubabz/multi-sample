package com.openlabs.sample.common.secure.key;

public interface SecureKeyProvider {

	public String getKey();
	
	public String getIvParameter();
	
}
