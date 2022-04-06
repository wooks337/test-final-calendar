package com.larry.fc.finalproject.core.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class EncryptorTest {

	@Test
	void bcryptTest() {
		final String origin = "my_password";
		final Encryptor encryptor = new BCryptEncryptor();
		final String hashed = encryptor.encrypt(origin);

		assertTrue(encryptor.isMatch(origin, hashed));

		final String wrong = "my_passwordd";
		assertFalse(encryptor.isMatch(wrong, hashed));
	}
}