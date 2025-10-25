package com.example.banking.model.dto;

import lombok.Data;

@Data
public class CardDto {
	private long accountId;
	private String cardNumber;
	private String cardType;
	private String cardBrand;
	private String cardHolder;
	private String expirationDate;
	private char cvv;
	private String cardStatus;
}
