/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import java.sql.Date;

/**
 *
 * @author ian
 */
public class Customer extends Person{
    private String bank_name;
    private String credit_card_number;
    private String expiryDate;
    private Database db = new Database();

    public Customer() {
    }

    public Customer(String bank_name, String credit_card_number, String expiryDate, String id, String name, String surname, int age, String address,String status) {
        super(id, name, surname, age, address,status);
        this.bank_name = bank_name;
        this.credit_card_number = credit_card_number;
        this.expiryDate = expiryDate;
        System.out.println(getId() + " "+ getBank_name() + " " + getCredit_card_number() + " " + getExpiryDate());
        db.connect();
        db.insert("Insert into customer values('"+getId()+"','"+getBank_name()+"','"+getCredit_card_number()+"','"+getExpiryDate()+"')");
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Customer{" + "bank_name=" + bank_name + ", credit_card_number=" + credit_card_number + ", expiryDate=" + expiryDate + '}';
    }
    
    
    
}
