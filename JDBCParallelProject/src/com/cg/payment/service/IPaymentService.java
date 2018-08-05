package com.cg.payment.service;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;

public interface IPaymentService {
	String createAccount(Payment acc) throws PaymentException;
	double showBalance(String mobileNo) throws  PaymentException;
	Payment deposit(String mobileNo,double depositAmt) throws  PaymentException;
	Payment withdraw(String mobileNo,double withdrawAmt) throws  PaymentException;
	boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmt) throws  PaymentException;
	Payment printTransactionDetails(String mobileNo) throws  PaymentException;
}
