package br.com.franca.util;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonToken;

public class AuthHelper {
	/**
     * Creates a json web token which is a digitally signed token that contains a payload (e.g. userId to identify 
     * the user). The signing key is secret. That ensures that the token is authentic and has not been modified.
     * Using a jwt eliminates the need to store authentication session information in a database.
     * @param duracao Validade do token em segundos
     * @return String com o token gerado
     */
	public static String createJsonWebToken(TokenUsuarioVO tokenUsuario, boolean mobile) {
		// Current time and signing algorithm
		Calendar cal = Calendar.getInstance();
		HmacSHA256Signer signer;
		try {
			signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		// Configure JSON token	
		JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
		token.setAudience(AUDIENCE);
		token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
		if (mobile) {
			token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + DURACAO_MOBILE));
		} else {
			token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + DURACAO_WEB));
		}

		JsonObject payload = token.getPayloadAsJsonObject();
		payload.add("TokenUsuarioVO", new Gson().toJsonTree(tokenUsuario));

		try {
			return token.serializeAndSign();
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		}
	}
}
