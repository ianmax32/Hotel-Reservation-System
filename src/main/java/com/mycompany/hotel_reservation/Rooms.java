/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 *
 * @author ian
 */
public class Rooms {
    private int room_id; 
    private String room_type; 
    private double rooms_price; 
    private String room_description;
    private boolean available; 
    private String extras;
    private int branch_id;
    private Button book;
    private Database db = new Database();

    public Rooms() {
    }

    public Rooms(int room_id, String room_type, double rooms_price, String room_description, boolean availble, String extras, int branch_id) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.rooms_price = rooms_price;
        this.room_description = room_description;
        this.available = availble;
        this.extras = extras;
        this.branch_id = branch_id;
        //db.insert("insert into rooms values('"+getRoom_id()+"','"+getRoom_type()+"','"+getRooms_price()+"','"+getRoom_description()+"','"+isAvailable()+"','"+getExtras()+"','"+getBranch_id()+"')");
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public double getRooms_price() {
        return rooms_price;
    }

    public String getRoom_description() {
        return room_description;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getExtras() {
        return extras;
    }

    public int getBranch_id() {
        return branch_id;
    }

    @Override
    public String toString() {
        ArrayList<String> rooms = new ArrayList<>();
        rooms = db.getAll("select * from rooms");
        return rooms.toString();
        }
    
    
}
