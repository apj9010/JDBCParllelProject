package com.cg.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

import com.cg.payment.bean.Payment;
import com.cg.payment.db.PaymentDb;
import com.cg.payment.exception.PaymentException;

public class PaymentDao implements IPaymentDao {

	@Override
	public String createAccount(Payment acc) throws PaymentException {
		
		Connection con=PaymentDb.getConnection();
		PreparedStatement stat;
		try{
		con.setAutoCommit(false);
		stat = con.prepareStatement(IQuery.insert);
		stat.setString(1, acc.getName());
		stat.setString(2, acc.getEmail());
		stat.setString(3, acc.getMobileNo());
		stat.setDouble(4, acc.getBalance());
		int res=stat.executeUpdate();
		if(res==1){
		con.commit();
		return acc.getMobileNo();
		}else{
		throw new PaymentException("Could not create account");
		}
		 
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new PaymentException(e.getMessage());
		}
	}

	@Override
	public double showBalance(String mobileNo) throws PaymentException {
		Connection con=PaymentDb.getConnection();
		PreparedStatement stat;
		try{
		stat=con.prepareStatement(IQuery.getBal);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		con.commit();
		if(rs!=null){
		rs.next();
		return rs.getDouble("balance");
		}else{
		throw new PaymentException("mobile no does not exists");
		}
		 
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new PaymentException(e.getMessage());
		}
	}

	@Override
	public Payment deposit(String mobileNo, double depAmt)
			throws PaymentException {
		Connection con=PaymentDb.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{
		 
		stat=con.prepareStatement(IQuery.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		if(rs!=null){
		rs.next();
		Payment acc=new Payment();
		double balance=rs.getDouble("balance")+depAmt;
		acc.setName(rs.getString("name"));
		acc.setMobileNo(rs.getString("mobileno"));
		acc.setBalance(balance);
		acc.setEmail(rs.getString("email"));
		acc.setDate(Date.valueOf(LocalDate.now()));
		
		 
		stat1=con.prepareStatement(IQuery.update);
		stat1.setDouble(1, acc.getBalance());
		stat1.setDate(2, acc.getDate());
		stat1.setString(3, acc.getMobileNo());
		int rs1=stat1.executeUpdate();
		 
		 
		if(rs1==1){
		 
		con.commit();
		 
		return acc;
		}else{
		throw new PaymentException("balance is not updated");
		}
		 
		}
		else{
		throw new PaymentException("mobile no does not exists");
		}
		 
		 
		}catch (SQLException e) {
		
		throw new PaymentException(e.getMessage());
		}
		 
	}

	@Override
	public Payment withdraw(String mobileNo, double withdrawAmt)
			throws PaymentException {
		Connection con=PaymentDb.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{
		 
		stat=con.prepareStatement(IQuery.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		if(rs!=null){
		rs.next();
		Payment acc=new Payment();
		double balance=rs.getDouble("balance")-withdrawAmt;
		acc.setName(rs.getString("name"));
		acc.setMobileNo(rs.getString("mobileno"));
		acc.setBalance(balance);
		acc.setEmail(rs.getString("email"));
		acc.setDate(Date.valueOf(LocalDate.now()));
		
		 
		stat1=con.prepareStatement(IQuery.update);
		stat1.setDouble(1, acc.getBalance());
		stat1.setDate(2, acc.getDate());
		stat1.setString(3, acc.getMobileNo());
		int rs1=stat1.executeUpdate();
		 
		 
		if(rs1==1){
		
		con.commit();
		
		return acc;
		}else{
		throw new PaymentException("mobile no does not exists");
		}
		
		}
		else{
		throw new PaymentException("mobile no does not exists");
		}
		 
		 
		}catch (SQLException e) {
		throw new PaymentException(e.getMessage());
		}
	}

	@Override
	public Payment printTransactionDetails(String mobileNo)
			throws PaymentException {
		Connection con=PaymentDb.getConnection();
		PreparedStatement stat;
		try{
		stat=con.prepareStatement(IQuery.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		con.commit();
		if(rs!=null){
		rs.next();
		Payment ac=new Payment();
		ac.setName(rs.getString("name"));
		ac.setMobileNo(rs.getString("mobileno"));
		ac.setEmail(rs.getString("email"));
		ac.setBalance(rs.getDouble("balance"));
		ac.setDate(rs.getDate("date1"));
		return ac;
		}else{
		throw new PaymentException("mobile no does not exists");
		}
		 
		 
		}catch (SQLException e) {
		
		throw new PaymentException(e.getMessage());
		}
	}

	

	}


