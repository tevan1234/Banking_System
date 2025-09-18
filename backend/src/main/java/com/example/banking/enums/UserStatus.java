package com.example.banking.enums;

public enum UserStatus {
	ACTIVE,
	LOCKED,
	INACTIVE,
	SUSPENDED;
	
	public static UserStatus fromString(String value) {
        for (UserStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown UserStatus: " + value);
    }
}
