package com.assetmanagement.exception;

@SuppressWarnings("serial")
public class WarehouseNotFoundException extends RuntimeException {

	public WarehouseNotFoundException( String msg) {
		super(msg);
	}

}
