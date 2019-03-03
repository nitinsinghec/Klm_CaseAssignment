package com.afkl.cases.df.controller;

import java.util.Map;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.afkl.cases.df.auth.AuthenticationClient;
import com.afkl.cases.df.common.AppProperties;
import com.afkl.cases.df.model.OAuthTokenVO;
import com.afkl.cases.df.model.StatisticsVO;

@Controller
public class ApplicationMetricController {
	
	private static final String COUNTER_STATUS = "counter.status";
	private static final String COUNTER_STATUS_5 = "counter.status.5";
	private static final String COUNTER_STATUS_2 = "counter.status.2";
	private static final String COUNTER_STATUS_200_METRICS_ROOT = "counter.status.200.metrics.root";
	private static final String COUNTER_STATUS_4 = "counter.status.4";
	@Autowired
	private AppProperties appProperties;
	
	Predicate<String> containStatus2XX = item -> item.contains(COUNTER_STATUS_2) && !item.equals(COUNTER_STATUS_200_METRICS_ROOT);
	Predicate<String> containStatus4XX = item -> item.contains(COUNTER_STATUS_4);	
	Predicate<String> containStatus5XX = item -> item.contains(COUNTER_STATUS_5);	
	Predicate<String> containStatus200 = item -> item.contains(COUNTER_STATUS) && !item.equals(COUNTER_STATUS_200_METRICS_ROOT);
	
	/**
	 * This method returns the application statistics
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("statistics")
	public ResponseEntity<StatisticsVO> getApplicationStatistics(HttpServletRequest request)  throws Exception{
		OAuthTokenVO lOAuthTokenVO = AuthenticationClient.getOAuthToken(appProperties.getBaseRestApiUrl());
		String url=appProperties.getBaseRestApiUrl()+ "metrics?access_token="+lOAuthTokenVO.getAccess_token();
		RestTemplate lRestTemplate = new RestTemplate();
		Map<String, String> metricsMap = lRestTemplate.getForEntity(url, Map.class).getBody();
		StatisticsVO lStatisticsVO = new StatisticsVO();
		if(null!=metricsMap && !metricsMap.isEmpty()){
			
			metricsMap.entrySet().forEach(entry -> {			    
			    if(containStatus2XX.test(entry.getKey())){
				    	lStatisticsVO.setStatus2xxRequestCount(lStatisticsVO.getStatus2xxRequestCount()+Integer.valueOf(String.valueOf(entry.getValue())));
				    }else if(containStatus4XX.test(entry.getKey())){
				    	lStatisticsVO.setStatus4xxRequestCount(lStatisticsVO.getStatus4xxRequestCount()+Integer.valueOf(String.valueOf(entry.getValue())));
				    }else if(containStatus5XX.test(entry.getKey())){
				    	lStatisticsVO.setStatus5xxRequestCount(lStatisticsVO.getStatus5xxRequestCount()+Integer.valueOf(String.valueOf(entry.getValue())));
				    }			    
				    if(containStatus200.test(entry.getKey())){
				    	lStatisticsVO.setTotalRequestCount(lStatisticsVO.getTotalRequestCount()+Integer.valueOf(String.valueOf(entry.getValue())));
				    }
			}); 		 
		}		 
		return new ResponseEntity<StatisticsVO>(lStatisticsVO,HttpStatus.OK);
	}
}
