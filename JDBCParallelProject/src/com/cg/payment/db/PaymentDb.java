package com.cg.payment.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;


public class PaymentDb {
	public static Connection getConnection() throws PaymentException{
		
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url,"system","root");
			}catch(ClassNotFoundException e){
			throw new PaymentException(e.getMessage());
			}catch(SQLException e1){
			throw new PaymentException(e1.getMessage());
			}

		}
}