/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 29-Jun-2015 
 */
package com.sanlark.lib.core.util;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
	private static final String KEY = "some-secret-key-of-your-choice";

	public static String encrypt(final String text) {
		return Base64.encodeBase64String(xor(text.getBytes()));
	}

	public static String decrypt(final String hash) {
		try {
			return new String(xor(Base64.decodeBase64(hash.getBytes())),
					"UTF-8");
		} catch (java.io.UnsupportedEncodingException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private static byte[] xor(final byte[] input) {
		final byte[] output = new byte[input.length];
		final byte[] secret = KEY.getBytes();
		int spos = 0;
		for (int pos = 0; pos < input.length; ++pos) {
			output[pos] = (byte) (input[pos] ^ secret[spos]);
			spos += 1;
			if (spos >= secret.length) {
				spos = 0;
			}
		}
		return output;
	}
}
