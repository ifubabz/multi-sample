package com.openlabs.sample.util;

import com.openlabs.sample.common.secure.EncryptorFactory;
import com.openlabs.sample.common.secure.EncryptorFactory.ENCRYPT_TYPE;
import com.openlabs.sample.common.secure.IEncryptor;

public class EncryptUtil {

	private static IEncryptor encryptor = EncryptorFactory.getInstance().getEncryptor();
	
	public static String encode(String text) {
		return encryptor.encrypt(text);
	}
	
	public static String decode(String text) {
		return encryptor.decrypt(text);
	}
}
