package com.openlabs.sample.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openlabs.sample.common.secure.JaspytStringEncryptor;

@Configuration
public class JasyptConfig {

	@Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        return JaspytStringEncryptor.getInstance().getEncryptor();
    }
	
	public static void main(String[] args) {
		String password = "test";
		
		JasyptConfig jasyptConfig = new JasyptConfig();
		StringEncryptor encryptor = jasyptConfig.stringEncryptor();
		String enc = encryptor.encrypt(password);
		System.out.println(enc);
	}
}
