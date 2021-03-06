package com.systempro.stock.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.systempro.stock.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHadler {

	// esta chamando a class StandardError, que foi criada para expor a mensagem
	// necessária
	// dentro deste metodo também é chamado o ObjectNotFoundException.class, pois ha
	// nele mensagem a
	// ser estopada caso haja uma busca por id inexistente.
	// HttpStatus.NOT_FOUND.value(), é o erro 404,
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoud(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
