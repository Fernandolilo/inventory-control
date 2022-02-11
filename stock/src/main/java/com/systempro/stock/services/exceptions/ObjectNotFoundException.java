package com.systempro.stock.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	// sobre carga com uma outra excessão, para uma causa do possivel erro.
	// overload with one other exception, for a possible error cause.
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
