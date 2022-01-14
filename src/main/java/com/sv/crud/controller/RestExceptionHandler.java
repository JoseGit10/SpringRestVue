package com.sv.crud.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sv.crud.dto.ErrorResponse;
import com.sv.crud.util.ValidExceptionData;


/**
 * @author Jose Moran
 * @version 1.0
 * @apiNote Sirve para tomar todas las excepciones 
 * de la aplicacion en general y mostrar mejor informacion al usuario
 * 
 * */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @author Jose Moran
	 * @param  NoSuchElementException 
	 * Sirve para verificar si algun parametro del request no se encuentra 
	 * @return responseEntity<ErrorResponse>
	 * */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException excep){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return construirResponseEntity(httpStatus, excep);
	}
	
	
	/** 
	 * @author Jose Moran	 
	 * @param DuplicateKeyException
	 * Muestra errores con problemas al insertar o actualizar y su llave primaria
	 * no se encuentra 
	 * @return responseEntity<ErrorResponse>
	 */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException excep){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return construirResponseEntity(httpStatus, excep);
	}
	
	/** 
	 * @author Jose Moran	 
	 * @param IllegalArgumentException
	 * Muestra errores cuando algun parametro esta mal
	 * @return responseEntity<ErrorResponse>
	 */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException excep){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return construirResponseEntity(httpStatus, excep);
	}
	
	/** 
	 * @author Jose Moran
	 * @param ValidExceptionData
	 * Muestra los error
	 * */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(ValidExceptionData excep){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> listErr = excep.getResult().getFieldErrors()
								.stream().map(FieldError::getDefaultMessage)
								.collect(Collectors.toList());
		return construirResponseEntity(httpStatus,new RuntimeException("Favor completar todos los datos"),listErr);
	}
	
	
	/** 
	 * @author Jose Moran	 
	 * @param IllegalArgumentException
	 * Muestra errores cuando el parametro de la url no se encuentra o es de otro tipo
	 * @return responseEntity<ErrorResponse>
	 */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException excep){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return construirResponseEntity(httpStatus, new RuntimeException("Tipo de argumento invalido"));
	}
	
	
	/**
	 * @author Jose Moran
	 * @param Exception 
	 * Error que no se encuentra mapeado en el codigo.
	 * @return ResponseEntity<ErrorResponse> 
	 * */
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(Exception excep){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return construirResponseEntity(httpStatus, new RuntimeException("Se presento un problema, intente luego y reporte el problema"));
	}
	
	private ResponseEntity<ErrorResponse> construirResponseEntity(HttpStatus httpStatus,Exception excep){
		return construirResponseEntity(httpStatus,excep,null);
	}
	
	/**
	 * @param HttpStatus
	 * @param Exception
	 * @param List<String>
	 * Construir response segun los datos enviados
	 * @return ResponseEntity<ErrorResponse>
	 * */
	private ResponseEntity<ErrorResponse> construirResponseEntity(HttpStatus httpStatus,Exception excep,List<String> errors){
		ErrorResponse errorRes = new ErrorResponse();
		errorRes.setMensaje(excep.getMessage());
		errorRes.setEstatus(httpStatus.value());
		errorRes.setTimestap(new Date());
		errorRes.setErrors(errors);
		
		return new ResponseEntity<>(errorRes, httpStatus);
	}
	
}
