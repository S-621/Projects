package com.capgemini.lenskart.exception;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.capgemini.lenskart.response.CommonResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	 GlobalException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse> ExceptionHandler(Exception se, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(new Date());
		err.setMessage(se.getMessage());
		err.setDescription(req.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), se.getMessage()));

	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CommonResponse>CustomExceptionHandler(CustomException ce, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(new Date());
		err.setMessage(ce.getMessage());
		err.setDescription(req.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CommonResponse(HttpStatus.BAD_REQUEST.value(),ce.getMessage()));
	}
	
}
