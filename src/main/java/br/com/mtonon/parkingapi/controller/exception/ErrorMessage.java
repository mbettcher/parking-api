package br.com.mtonon.parkingapi.controller.exception;

/**
 * Classe responsável por criar um objeto de erro personalizado, em substituição
 * ao objeto de erro padrão do Spring, que será retornado ao cliente, caso
 * ocorra algum erro.
 * 
 * @author Marcelo Tonon Bettcher
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
	
	private String path;
	
	private String method;
	
	private int status;
	
	private String statusText;
	
	private String message;
	
	private Map<String, String> errors;
	
	
	public ErrorMessage() {
		super();
	}

	/**
	 * Método construtor da classe ErrorMessage que recebe como parâmetros um objeto tipo
	 * HttpServletRequest, um HttpStatus e uma String. Com o HttpServletRequest() é 
	 * possível obter o path do recurso que gerou o erro a partir do método getRequestURI() 
	 * e, o method por meio do getMethod(), obter o method Http utilizado no recurso.
	 * A partir do HttpStatus é possivel se obter o código doerro por meio
	 * do método value(). Ainda com o HttpStatus é possível obter o Status Http do erro 
	 * com o método getReasonPhrase().
	 * 
	 * @param HttpServletRequest request
	 * @param HttpStatus status
	 * @param String message
	 */
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
		super();
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}

	/**
	  * Método construtor da classe ErrorMessage que recebe como parâmetros um objeto tipo
	 * HttpServletRequest, um HttpStatus e uma String. Com o HttpServletRequest() é 
	 * possível obter o path do recurso que gerou o erro a partir do método getRequestURI() 
	 * e, o method por meio do getMethod(), obter o method Http utilizado no recurso.
	 * A partir do HttpStatus é possivel se obter o código doerro por meio
	 * do método value(). Ainda com o HttpStatus é possível obter o Status Http do erro 
	 * com o método getReasonPhrase(). Com o BindingResult é possível se obter o Array 
	 * com os erros gerados pela validação dos campos. 
	 * 
	 * @param HttpServletRequest request
	 * @param HttpStatus status
	 * @param String message
	 * @param BindingResult result
	 */
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
		super();
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErrors(result);
	}
	
	/**
	 * Método responsável por preencher a variável erros do tipo Map<String, String>
	 * contendo o nome do campo e a mensagem de validação do campo.
	 * 
	 * @param BindingResult result
	 */
	private void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		for(FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}
	
	

}
