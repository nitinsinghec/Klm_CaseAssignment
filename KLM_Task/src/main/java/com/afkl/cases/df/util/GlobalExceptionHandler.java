package com.afkl.cases.df.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.afkl.cases.df.model.ErrorDetailsVO;

/**
 * @author NitinSingh
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * It handles exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetailsVO> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetailsVO lErrorDetailsVO = new ErrorDetailsVO(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(lErrorDetailsVO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
