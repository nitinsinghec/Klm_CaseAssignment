package com.afkl.cases.df.auth;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.afkl.cases.df.model.OAuthTokenVO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author NitinSingh
 *
 */
@Component("authenticationClient")
public class AuthenticationClient {

	private static final String AUTH_TOKEN_URL = "oauth/token?grant_type=client_credentials";

	/**
	 * @return
	 */
	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	/**
	 * @return
	 */
	private static HttpHeaders populateHeadersWithClientCredentials() {
		String plainClientCredentials = "travel-api-client:psw";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}

	/**
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static OAuthTokenVO getOAuthToken(String url) throws Exception {
		OAuthTokenVO lOAuthTokenVO = null;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		HttpEntity<String> request = new HttpEntity<String>(populateHeadersWithClientCredentials());
		String access_token_url = url + AUTH_TOKEN_URL;
		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		lOAuthTokenVO = objectMapper.readValue(response.getBody(), OAuthTokenVO.class);
		return lOAuthTokenVO;
	}
}
