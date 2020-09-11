package com.openlabs.sample.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openlabs.sample.secure.SecureKeyHandler;

@Configuration
public class JasyptConfig {

	@Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(SecureKeyHandler.getKey());
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
	
	public static void main(String[] args) {
		String password = "test";
		
		JasyptConfig jasyptConfig = new JasyptConfig();
		StringEncryptor encryptor = jasyptConfig.stringEncryptor();
		String enc = encryptor.encrypt(password);
		System.out.println(enc);
	}
}
