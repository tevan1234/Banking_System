package com.example.banking.model.dto;

import com.example.banking.model.enums.CardBrand;
import com.example.banking.model.enums.CardStatus;
import com.example.banking.model.enums.CardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountDetailDto extends AccountDto {
	private long cardId;
	private String cardNumber;
	private CardType cardType;
	private CardBrand cardBrand;
	private String cardHolder;	
	private CardStatus cardStatus;
}
