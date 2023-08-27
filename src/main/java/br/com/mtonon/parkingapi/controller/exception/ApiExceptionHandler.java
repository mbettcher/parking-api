package br.com.mtonon.parkingapi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe reponsável por tratar os erros do tipo
 * org.springframework.web.bind.MethodArgumentNotValidException
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
	
	/**
	 * Metodo responsável por tratar o erro do tipo MethodArgumentNotValidException,
	 * por meio da anotação @ExceptionHandler faz-se o registro da exceção que deve
	 * ser capturada pela classe e retorne um ResponseEntity
	 * 
	 * @return ResponseEntity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request, BindingResult result) {
		
		log.error("API Error: " + ex);
		return ResponseEntity
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) Invalido(s)", result));
	}

}
