package com.example.banking.service;

import com.example.banking.exception.CertException;
import com.example.banking.model.dto.UserCertDto;

public interface CertService {
	public UserCertDto getCert(String userName,String password) throws CertException;
}
