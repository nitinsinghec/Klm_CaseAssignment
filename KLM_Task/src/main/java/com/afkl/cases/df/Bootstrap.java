package com.afkl.cases.df;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author NitinSingh
 *
 */
@SpringBootApplication
public class Bootstrap {

	/**
	 * It bootstrap application
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Bootstrap.class, args);
	}

}
