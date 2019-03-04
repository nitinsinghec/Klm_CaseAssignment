package com.afkl.cases.df.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.afkl.cases.df.auth.AuthenticationClient;
import com.afkl.cases.df.common.AppProperties;
import com.afkl.cases.df.model.FareVO;
import com.afkl.cases.df.model.OAuthTokenVO;

@Controller
public class FareController {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private AuthenticationClient authenticationClient;

	/**
	 * This method returns fare
	 * 
	 * @param originCode
	 * @param destinationCode
	 * @param currency
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "fares/{originCode}/{destinationCode}")
	public ResponseEntity<FareVO> getFareByAirportCode(@PathVariable("originCode") String originCode,
			@PathVariable("destinationCode") String destinationCode,
			@RequestParam(value = "currency", required = false, defaultValue = "EUR") String currency)
			throws Exception {

		OAuthTokenVO lOAuthTokenVO = authenticationClient.getOAuthToken(appProperties.getBaseRestApiUrl());
		RestTemplate lRestTemplate = new RestTemplate();
		String resourceUrl = appProperties.getBaseRestApiUrl() + "fares/" + originCode + "/" + destinationCode
				+ "?currency=" + currency + "&access_token=" + lOAuthTokenVO.getAccess_token();
		ResponseEntity<FareVO> responseEntity = lRestTemplate.getForEntity(resourceUrl, FareVO.class);
		FareVO fareVO = responseEntity.getBody();
		return new ResponseEntity<FareVO>(fareVO, HttpStatus.OK);
	}
}
