package com.cg.payment.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;
import com.cg.payment.service.IPaymentService;
import com.cg.payment.service.PaymentService;

public class PaymentTest {
	private IPaymentService service;

	@Before
	public void init() {
		service = new PaymentService();
	}

	@Test
	public void testCreateAccountForMobile() {
		Payment ac = new Payment();
		ac.setMobileNo("9865686");
		ac.setName("Apj");
		ac.setEmail("apj9010@gmail.com");
		ac.setBalance(3000.0);
		try {
			service.createAccount(ac);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForName() {
		Payment ac = new Payment();
		ac.setMobileNo("9999999999");
		ac.setName("appaji12");
		ac.setEmail("apj@gmail.com");
		ac.setBalance(3000.0);
		try {
			service.createAccount(ac);
		} catch (PaymentException e) {
			assertEquals("Name should start with capital letter and should contain only alphabets", e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForNameIsEmpty() {
		Payment ac = new Payment();
		ac.setMobileNo("8888888888");
		ac.setName("");
		ac.setEmail("apj@gmail.com");
		ac.setBalance(1000.0);
		try {
			service.createAccount(ac);
		} catch (PaymentException e) {
			assertEquals("Name cannot be empty", e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForEmailId() {
		Payment ra = new Payment();
		ra.setMobileNo("7777777777");
		ra.setName("Appaji");
		ra.setEmail("apj@@gmail");
		ra.setBalance(2000.0);
		try {
			service.createAccount(ra);
		} catch (PaymentException e) {
			assertEquals("Enter valid emailid", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {
		Payment ra = new Payment();
		ra.setMobileNo("6666666666");
		ra.setName("Shyam");
		ra.setEmail("shyam@gmail.com");
		ra.setBalance(2500.0);

		try {
			String s=service.createAccount(ra);
			assertNotNull(s);
		} catch (PaymentException e) {
		}

	}

	@Test
	public void testShowBalanceForMobileNo() {
		try {
			service.showBalance("9999589");
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}


	

	@Test
	public void testDepositForMobileNo() {
		Payment ra=new Payment();
		ra.setMobileNo("95059345");
		try {
			service.deposit(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testDepositForDepositAmt1() {
		Payment ra=new Payment();
		ra.setMobileNo("8888888888");
		try {
			service.deposit(ra.getMobileNo(), -230);
		} catch (PaymentException e) {
			assertEquals("Deposit amount must be greater than zero",e.getMessage());
		}
	}

	@Test
	public void testDeposit() {
		Payment ra=new Payment();
		ra.setMobileNo("9505928555");
		try {
			Payment ra1=service.deposit(ra.getMobileNo(), 230);
			assertNotNull(ra1);
		} catch (PaymentException e) {

			//System.out.println(e.getMessage());
		}
	}

	@Test
	public void testWithDrawForMobileNo() {
		Payment ra=new Payment();
		ra.setMobileNo("95059345");
		try {
			service.withdraw(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testWithdrawForAmt() {
		Payment ra=new Payment();
		ra.setMobileNo("9999999999");
		try {
			service.withdraw(ra.getMobileNo(), -230);
		} catch (PaymentException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}


	@Test
	public void testTransferForMobileNo() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("95059345");
		ra2.setMobileNo("1234");
		try {
			service.fundTransfer(ra.getMobileNo(),ra2.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testTransferForAmt() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("9999999999");
		ra2.setMobileNo("6666666666");
		try {
			service.fundTransfer(ra.getMobileNo(), ra2.getMobileNo(),  -230);
		} catch (PaymentException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	@Test
	public void testTransfer() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("9505928555");
		ra2.setMobileNo("9848468242");
		try {
			assertTrue(service.fundTransfer(ra.getMobileNo(), ra2.getMobileNo(),  230));
		} catch (PaymentException e) {
			//System.out.println(e.getMessage());
		}
	}
	@Test
	public void testPrintDetails() {
		Payment ra=new Payment();
		ra.setMobileNo("9848468242");
		try {
			Payment raa=service.printTransactionDetails(ra.getMobileNo());
			assertNotNull(raa);
		} catch (PaymentException e) {
		//	System.out.println(e.getMessage());
		}

	}


	@Test
	public void testWithDrawForMobileNoInDb() {
		Payment acc=new Payment();
		acc.setMobileNo("944167487");
		try {
			service.withdraw(acc.getMobileNo(), 600);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	} 



}

