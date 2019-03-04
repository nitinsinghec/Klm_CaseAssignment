package com.afkl.cases.df.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author NitinSingh
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class AppProperties {

	private final String baseUrl;
	private final String baseRestApiUrl;
	private final String credential;

	/**
	 * It populate properties from application.properties
	 * 
	 * @param env
	 */
	@Autowired
	public AppProperties(Environment env) {
		this.baseUrl = env.getRequiredProperty("base.url");
		this.baseRestApiUrl = env.getRequiredProperty("base.rest.api.url");
		this.credential = env.getRequiredProperty("app.credential.username") + ":"
				+ env.getRequiredProperty("app.credential.pwd");
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getBaseRestApiUrl() {
		return baseRestApiUrl;
	}

	public String getCredential() {
		return credential;
	}

}
