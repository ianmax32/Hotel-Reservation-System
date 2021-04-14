/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import java.util.Random;
import java.sql.*;

/**
 *
 * @author ian
 */
public class Employee extends Person{
    private String employee_id;
    private String password;
    private double salary;
    private String shift;
    private int hours;
    Database database = new Database();

    public Employee(String id,String name, String surname, int age, String address,String status,double salary, String shift, int hours) {
        super(id,name,surname,age,address,status);
        setEmployee_id(name, surname);
        setPassword(name);
        setHours(hours);
        setShift(shift);
        setSalary(salary);
         database.connect();
        database.insert("Insert into employee values('"+getEmployee_id()+"','"+getPassword()+"','"+getSalary()+"','"+getShift()+"','"+getHours()+"','"+getStatus()+"')");
    }

    public Employee() {
       
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public double getSalary() {
        return salary;
    }

    public String getShift() {
        return shift;
    }

    public int getHours() {
        return hours;
    }
    

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getPassword() {
        return password;
    }

    
  
    public void setEmployee_id(String name, String surname) {
        this.employee_id =  name.substring(0,2) + surname.substring(0,2) + new Random().nextInt(99);
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
    //connecting and getting infomration from the database

    @Override
    public String toString() {
        return "Employee{" + "employee_id=" + employee_id + ", password=" + password + ", salary=" + salary + ", shift=" + shift + ", hours=" + hours + '}';
    }
    
    
}
