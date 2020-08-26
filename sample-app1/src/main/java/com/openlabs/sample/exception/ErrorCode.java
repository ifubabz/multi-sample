package com.openlabs.sample.exception;

public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
    ;
	
	private int status;
    private final String message;

	ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

	public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return status;
    }
}
