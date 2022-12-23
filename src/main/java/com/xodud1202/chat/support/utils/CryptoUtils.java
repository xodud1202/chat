package com.xodud1202.chat.support.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 암호화 Util Class
 * @author xodud1202
 * @since  2022.12.22
 */
@Slf4j
public class CryptoUtils {
	// AES키 (다른 프로젝트와 같은 테이블 데이터를 사용해야하므로 AES_KEY 통일)
	private static final String AES_KEY = "zmfltmahf202201!";

	/**
	 * JAVA에서는 AES키는 16byte이지만, MySQL에서는 16byte 이상도 지원하기에 MySQL의 16byte 이상의 키를 생성해야 한다.
	 * 16byte 이상은 잘라서 다시 앞쪽부터 byte 단위로 XOR 시켜서 16byte 키로 생성
	 * @param encoding
	 * @return
	 */
	private static SecretKeySpec generateMysqlAESKey(final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for (byte b : AES_KEY.getBytes(encoding)) {
				finalKey[i++ % 16] ^= b;
			}
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * MySQL AES128 암호화 처리
	 * 		MySQL) HEX(AES_ENCRYPT('테스트', AES_KEY))
	 * @param rawValue - 원시문자열
	 * @return String - 암호화문자열
	 */
	public static String encryptAES(String rawValue) {
		String encryptedValue = "";

		try {
			if (StringUtils.isNotBlank(rawValue)) {
				final Cipher cipher = Cipher.getInstance("AES"); // Cipher.getInstance("AES/ECB/PKCS5Padding")와 같음. ECB 모드의 경우 cipher.init 시 IvParameterSpec을 넣음 안 된다.
				cipher.init(Cipher.ENCRYPT_MODE, generateMysqlAESKey("UTF-8"));
				encryptedValue = new String(Hex.encodeHex(cipher.doFinal(rawValue.getBytes("UTF-8")))).toUpperCase();
			}
		} catch (Exception e) {
//			log.error(e.getMessage());
			encryptedValue = rawValue;
		}

		return encryptedValue;
	}

	/**
	 * MySQL AES128 복호화 처리
	 * 		MySQL) CAST(AES_DECRYPT(UNHEX('0EF41E8ED0A5D40D2F8DC4C0A3A9C722'),AES_KEY) AS CHAR)
	 * @param encryptedValue - 암호화된 문자열
	 * @return String - 복호화문자열
	 */
	public static String decryptAES(String encryptedValue) {
		String decryptedValue = "";

		try {
			if (StringUtils.isNotBlank(encryptedValue)) {
				final Cipher cipher = Cipher.getInstance("AES"); // Cipher.getInstance("AES/ECB/PKCS5Padding")와 같음
				cipher.init(Cipher.DECRYPT_MODE, generateMysqlAESKey("UTF-8"));
				decryptedValue = new String(cipher.doFinal(Hex.decodeHex(encryptedValue.toCharArray())), "UTF-8");
			}
		} catch (Exception e) {
//			log.error(e.getMessage());
			decryptedValue = encryptedValue;
		}

		return decryptedValue;
	}
}
