package br.com.franca.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptoUtil {
	public static String criptoMD5(String valor) {
		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(valor.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte anArray : array) {
				sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.reverse().toString();
		} catch (NoSuchAlgorithmException e) {

		}
		return null;
	}

}
