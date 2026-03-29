package com.example.banking.utils;

import io.micrometer.common.util.StringUtils;

@SuppressWarnings("all")
public class MaskUtils {

	public static String maskIdNumber(String idNumber) {
		if (StringUtils.isEmpty(idNumber)) {
			return idNumber;
		}
		return idNumber.substring(0, 4) + "****" + idNumber.substring(8);
	}
	
	public static String maskPhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return phone;
		}
		return phone.substring(0, 4) + "****" + phone.substring(8);
	}
	
	public static String maskEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return email;
		}
		int atIndex = email.indexOf('@');
        if (atIndex <= 2) return email;
        return email.substring(0, 2) + "***" + email.substring(atIndex);
	}
	
	public static String maskAddress(String address) {
	    if (StringUtils.isEmpty(address)) return address;
	    int maskIndex = address.length() / 2;
	    return address.substring(0, maskIndex) + "****";
	}
	
//	public static String maskAccountNumber(String accountNumber) {
//        if (StringUtils.isEmpty(accountNumber)) return accountNumber;
//        return accountNumber.substring(0, 7) + "****" + accountNumber.substring(11);
//    }
}
