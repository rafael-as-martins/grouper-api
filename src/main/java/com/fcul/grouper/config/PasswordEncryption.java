package com.fcul.grouper.config;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

	public String SHA256(String username) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(username.getBytes("iso-8859-1"), 0, username.length());

		byte[] sha1hash = md.digest();

		return convertToHex(sha1hash);

	}

	private static String convertToHex(byte[] data) {

		StringBuilder stringBuilder = new StringBuilder();

		for (byte aData : data) {
			int halfbyte = (aData >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					stringBuilder.append((char) ('0' + halfbyte));
				else
					stringBuilder.append((char) ('a' + (halfbyte - 10)));
				halfbyte = aData & 0x0F;
			} while (two_halfs++ < 1);
		}

		return stringBuilder.toString();
	}
}
