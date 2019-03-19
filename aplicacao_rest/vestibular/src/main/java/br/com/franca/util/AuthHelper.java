package br.com.franca.util;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.franca.model.TokenUsuario;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;

public class AuthHelper {
	
    private static final String AUDIENCE = "";

    private static final String ISSUER = "franca.com.br";

    private static final String SIGNING_KEY = "yQXBwTmFtZUhlcmUiLCJhdWQi@^($%*$%OiJOb3RSZWFsbHlJbXB";

    private static final Long DURACAO_MOBILE = 1000L * 60 * 60 * 4; // 4 horas
    private static final Long DURACAO_WEB = 1000L * 60 * 60; // 1 HORA
//    private static final Long DURACAO_WEB = 1000L * 60 * 5; // 5 minutos
//    private static final Long DURACAO_WEB = 1000L * 30L; // 30 segundos
    
    
	/**
     * Creates a json web token which is a digitally signed token that contains a payload (e.g. userId to identify 
     * the user). The signing key is secret. That ensures that the token is authentic and has not been modified.
     * Using a jwt eliminates the need to store authentication session information in a database.
     * @param duracao Validade do token em segundos
     * @return String com o token gerado
     */
    
	public static String createJsonWebToken(TokenUsuario tokenUsuario) {
		// Current time and signing algorithm
		Calendar cal = Calendar.getInstance();
		HmacSHA256Signer signer;
		try {
			signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		// Configure JSON token	
		net.oauth.jsontoken.JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
		// token.setAudience(AUDIENCE);
		token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
		
		JsonObject payload = token.getPayloadAsJsonObject();
		payload.add("TokenUsuario", new Gson().toJsonTree(tokenUsuario));

		try {
			return token.serializeAndSign();
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		}
	}
}
