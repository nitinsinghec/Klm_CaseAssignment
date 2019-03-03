package com.afkl.cases.df.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.afkl.cases.df.model.AirportDetailVO;
import com.afkl.cases.df.model.EmbeddedDetailWrapperVO;
import com.afkl.cases.df.model.EmbeddedWrapperVO;
import com.afkl.cases.df.model.OAuthTokenVO;

/**
 * @author NitinSingh
 *
 */
@Controller
public class AirportController {
	
	@Autowired
	private AppProperties appProperties;
	
	/**
	 * This method returns list of airports
	 * @param request
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("airports")
	public ResponseEntity<EmbeddedWrapperVO> getAirportList(HttpServletRequest request,
			@RequestParam(value = "lang", required = false, defaultValue = "en") String lang)  throws Exception{
		OAuthTokenVO lOAuthTokenVO = AuthenticationClient.getOAuthToken(appProperties.getBaseRestApiUrl());
		RestTemplate lRestTemplate = new RestTemplate();
		String resourceUrl =appProperties.getBaseRestApiUrl()+"airports?lang="+lang+"&access_token="+lOAuthTokenVO.getAccess_token();
		ResponseEntity<EmbeddedWrapperVO> responseEntity = lRestTemplate.getForEntity(resourceUrl , EmbeddedWrapperVO.class);
		EmbeddedWrapperVO lEmbeddedWrapperVO = responseEntity.getBody();
		return new ResponseEntity<EmbeddedWrapperVO>(lEmbeddedWrapperVO,HttpStatus.OK);
	}
	
	/**
	 * This method returns list of airports with detailed information
	 * @param request
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("airportsInfo")
	public ResponseEntity<EmbeddedDetailWrapperVO> getAirportDetailList(HttpServletRequest request,
			@RequestParam(value = "lang", required = false, defaultValue = "en") String lang)  throws Exception{
		OAuthTokenVO lOAuthTokenVO = AuthenticationClient.getOAuthToken(appProperties.getBaseRestApiUrl());
		RestTemplate lRestTemplate = new RestTemplate();
		String resourceUrl =appProperties.getBaseRestApiUrl()+"airports?lang="+lang+"&access_token="+lOAuthTokenVO.getAccess_token();
		ResponseEntity<EmbeddedDetailWrapperVO> responseEntity = lRestTemplate.getForEntity(resourceUrl , EmbeddedDetailWrapperVO.class);
		EmbeddedDetailWrapperVO lEmbeddedDetailWrapperVO = responseEntity.getBody();
		return new ResponseEntity<EmbeddedDetailWrapperVO>(lEmbeddedDetailWrapperVO,HttpStatus.OK);
	}
	
	
	/**
	 * This method returns details of airport for passed {@code}
	 * @param code
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="airportDetail/{code}")
	public ResponseEntity<AirportDetailVO> getAirportById(@PathVariable("code") String code,
			@RequestParam(value = "lang", required = false, defaultValue = "en") String lang)  throws Exception{
		OAuthTokenVO lOAuthTokenVO = AuthenticationClient.getOAuthToken(appProperties.getBaseRestApiUrl());
		RestTemplate lRestTemplate = new RestTemplate();
		String resourceUrl =appProperties.getBaseRestApiUrl()+"airports/"+code+"?lang="+lang+"&access_token="+lOAuthTokenVO.getAccess_token();
		ResponseEntity<AirportDetailVO> responseEntity = lRestTemplate.getForEntity(resourceUrl , AirportDetailVO.class);
		AirportDetailVO lAirportDetailVO = responseEntity.getBody();
		return new ResponseEntity<AirportDetailVO>(lAirportDetailVO,HttpStatus.OK);
	}
	
}
