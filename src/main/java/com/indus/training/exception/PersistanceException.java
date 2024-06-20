package com.indus.training.exception;

import java.io.IOException;

public class PersistanceException  extends IOException{

	private static final long serialVersionUID = 315634733262550810L;

	public PersistanceException(String message) {
		super(message);
	}

	
}
