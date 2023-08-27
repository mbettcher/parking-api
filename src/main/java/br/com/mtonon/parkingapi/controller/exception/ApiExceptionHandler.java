package br.com.mtonon.parkingapi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mtonon.parkingapi.service.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe reponsável por tratar os erros de Exceção
 * devolvendo ao cliente uma mensagem de erro personalizada.
 *
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
	
	
	/**
	 * Metodo responsável por tratar o erro do tipo UsernameUniqueViolationException
	 * que extends a interface RuntimeException, de modo que se houverem mais de uma
	 * mensagem de erro do tipo Unique, pode-se captura-las a partir da RuntimeException.
	 * Anotação @ExceptionHandler permite o registro da exceção que deve
	 * ser capturada pela classe e retorne um ResponseEntity
	 * 
	 * @return ResponseEntity
	 */
	@ExceptionHandler(UsernameUniqueViolationException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(RuntimeException ex,
			HttpServletRequest request) {
		
		log.error("API Error: " + ex);
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
	}

}
