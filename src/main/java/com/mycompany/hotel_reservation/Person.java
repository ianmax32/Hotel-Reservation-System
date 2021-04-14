/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import java.util.ArrayList;

/**
 *
 * @author ian
 */
public class Person {
    private String name;
    private String surname;
    private String id;
    private int age;
    private String address;
    private String status;
    Database db = new Database();

    public Person() {
        
    }

    public Person(String id,String name, String surname, int age, String address,String status) {
        setId(id);
        setName(name);
        setStatus(status);
        this.surname = surname;
        this.age = age;
        this.address = address;
        db.connect();
        db.insert("Insert into person values('"+getId()+"','"+getName()+"','"+getSurname()+"','"+getAge()+"','"+getAddress()+"','"+getStatus()+"')");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", surname=" + surname + ", id=" + id + ", age=" + age + ", address=" + address + '}';
        //ArrayList<String> people = new ArrayList<>();
        //people = db.getAll("select * from person");
        //return people.toString();
    }
    
    
    
}
