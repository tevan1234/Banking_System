package com.example.banking.model.entity;

import java.security.Timestamp;
import java.time.LocalDate;

import com.example.banking.model.enums.CardBrand;
import com.example.banking.model.enums.CardStatus;
import com.example.banking.model.enums.CardType;

import lombok.Data;

@Data
public class Cards {
	private long cardId;
	private long accountId;
	private String cardNumber;
	private CardType cardType;
	private CardBrand cardBrand;
	private String cardHolder;
	private LocalDate expirationDate;
	private char cvv;//安全碼
	private CardStatus cardStatus;
	private Timestamp issuedAt;//發行時間戳
}
