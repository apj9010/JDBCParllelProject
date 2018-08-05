package com.cg.payment.dao;

public interface IQuery {
public String insert="insert into accunt(name,email,mobileno,balance) values(?,?,?,?)";
public String getBal = "select balance from accunt where mobileno=?";
public String getacc = "select * from  accunt";
public String update="update accunt set balance=?,date1=? where mobileno=?";
}
