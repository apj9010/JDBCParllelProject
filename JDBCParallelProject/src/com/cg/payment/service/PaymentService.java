package com.cg.payment.service;

import com.cg.payment.bean.Payment;
import com.cg.payment.dao.IPaymentDao;
import com.cg.payment.dao.PaymentDao;
import com.cg.payment.exception.PaymentException;

public class PaymentService implements IPaymentService {
IPaymentDao wdao = new PaymentDao();

@Override
public String createAccount(Payment acc) throws PaymentException {
	if (!acc.getMobileNo().matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
		if (acc.getName().isEmpty() || acc.getName() == null) {
		throw new PaymentException("Name cannot be empty");
		} else {
		if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {
		throw new PaymentException(
		"Name should start with capital letter and should contain only alphabets");
		}
		}
		if (acc.getBalance() < 0) {
		throw new PaymentException("Balance should be greater than zero");
		}
		if (!acc.getEmail().matches("[a-z0-9]+@[a-z]+\\.com")) {
		throw new PaymentException("Enter valid emailid");
		}

		return wdao.createAccount(acc);
}

@Override
public double showBalance(String mobileNo) throws PaymentException {
	if (!mobileNo.matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
		return wdao.showBalance(mobileNo);
}

@Override
public Payment deposit(String mobileNo, double depositAmt)
		throws PaymentException {
	if (!mobileNo.matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
	
		if (depositAmt <= 0) {
		throw new PaymentException(
		"Deposit amount must be greater than zero");
		}
		 
		return wdao.deposit(mobileNo,depositAmt);
		
}

@Override
public Payment withdraw(String mobileNo, double withdrawAmt)
		throws PaymentException {
	if (!mobileNo.matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
		
		if ( withdrawAmt <= 0) {
		throw new PaymentException(
		"The amount to be withdrawn should be greater than available balance and greater than zero");
		}
		
		Payment acc1 = wdao.withdraw(mobileNo,withdrawAmt);
		return acc1;
}

@Override
public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
		double transferAmt) throws PaymentException {
	if (!sourceMobileNo.matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
		if (!destMobileNo.matches("\\d{10}")) {
		throw new PaymentException("Mobile number should contain 10 digits");
		}
		IPaymentService service = new PaymentService();
		Payment acc1 = service.withdraw(sourceMobileNo, transferAmt);
		Payment d2 = service.deposit(destMobileNo, transferAmt);
		return true;
}

@Override
public Payment printTransactionDetails(String mobileNo) throws PaymentException {
	return wdao.printTransactionDetails(mobileNo);
}
	

}
