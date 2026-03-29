package com.example.banking.model.dto;

import com.example.banking.model.enums.AccountType;
import com.example.banking.model.enums.CardBrand;
import com.example.banking.model.enums.CardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApplyDto {
	private long customerId;
	private AccountType accountType;
	private String branch;
	private char currency;
	private CardType cardType;
	private CardBrand cardBrand;
	private String cardHolder;
}
