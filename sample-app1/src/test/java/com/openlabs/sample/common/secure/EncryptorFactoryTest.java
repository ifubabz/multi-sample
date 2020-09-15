package com.openlabs.sample.common.secure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.openlabs.sample.common.secure.EncryptorFactory.ENCRYPT_TYPE;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EncryptorFactoryTest {

	@Test
	void test() throws NoSuchAlgorithmException {
		String text = "test";
		IEncryptor encryptor = EncryptorFactory.getInstance().getEncryptor();
		String encrypted = encryptor.encrypt(text);
		log.debug("encrypted:{}", encrypted);
		String decrypted = encryptor.decrypt(encrypted);
		log.debug("decrypted:{}", decrypted);
		
		assertEquals(text, decrypted);
	}
	
	@Test
	void test1() throws NoSuchAlgorithmException {
		String text = "test";
		IEncryptor encryptor = EncryptorFactory.getInstance().getEncryptor(ENCRYPT_TYPE.JCE);
		String encrypted = encryptor.encrypt(text);
		log.debug("encrypted:{}", encrypted);
		String decrypted = encryptor.decrypt(encrypted);
		log.debug("decrypted:{}", decrypted);
		
		assertEquals(text, decrypted);
	}

}
