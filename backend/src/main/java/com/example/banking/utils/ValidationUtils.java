package com.example.banking.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.example.banking.model.dto.UserProfileDto;

import io.micrometer.common.util.StringUtils;

public class ValidationUtils {

	// 網銀使用者代號
	private static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";

	// 台灣手機號碼：09開頭，共10碼
	private static final String PHONE_REGEX = "^09\\d{8}$";

	// Email 格式
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	// 身分證：第一碼大寫英文，後9碼數字
	private static final String ID_NUMBER_REGEX = "^[A-Z][0-9]{9}$";

	// 出生日期：yyyy-MM-dd
	private static final String DOB_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

	public static boolean isValidUsername(String username, String personalId) {
        if (StringUtils.isEmpty(username)) return false;

        // 1. 長度與基本格式 (6-16位英數字)
        if (username.length() < 6 || username.length() > 16 || !username.matches(USERNAME_REGEX)) {
            return false;
        }

        // 2. 強度檢查：必須包含英文字母與數字
        boolean hasAlpha = username.matches(".*[a-zA-Z].*");
        boolean hasNum = username.matches(".*[0-9].*");
        if (!hasAlpha || !hasNum) return false;

        // 3. 重複性檢查：不可包含連續 3 個相同字元 (如 aaa, 111)
        if (username.matches(".*(.)\\1\\1.*")) return false;

        // 4. 連續性檢查：不可使用 3 位以上連續遞增/遞減之英數字 (如 abc, 123)
        if (isSequential(username)) return false;

        // 5. 關聯性檢查：不可包含身分證字號
        if (StringUtils.isNotEmpty(personalId) && username.toUpperCase().contains(personalId.toUpperCase())) {
            return false;
        }

        return true;
    }

	public static boolean isValidPhone(String phone) {
		if (StringUtils.isEmpty(phone))
			return false;
		return phone.matches(PHONE_REGEX);
	}

	public static boolean isValidEmail(String email) {
		if (StringUtils.isEmpty(email))
			return false;
		return email.matches(EMAIL_REGEX);
	}

	public static boolean isValidIdNumber(String idNumber) {
		if (StringUtils.isEmpty(idNumber))
			return false;
		return idNumber.matches(ID_NUMBER_REGEX);
	}

	public static boolean isValidDob(String dob) {
		if (StringUtils.isEmpty(dob))
			return false;
		if (!dob.matches(DOB_REGEX))
			return false;
		try {
			LocalDate.parse(dob);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static void validateUserProfile(UserProfileDto dto) {
		if (StringUtils.isNotEmpty(dto.getPhone()) && !isValidPhone(dto.getPhone())) {
			throw new IllegalArgumentException("手機號碼格式錯誤");
		}
		if (StringUtils.isNotEmpty(dto.getEmail()) && !isValidEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email 格式錯誤");
		}
	}
	
	private static boolean isSequential(String str) {
        String s = str.toLowerCase();
        for (int i = 0; i < s.length() - 2; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);
            char c3 = s.charAt(i + 2);
            if ((c2 == c1 + 1 && c3 == c2 + 1) || (c2 == c1 - 1 && c3 == c2 - 1)) {
                return true;
            }
        }
        return false;
    }
	
}
