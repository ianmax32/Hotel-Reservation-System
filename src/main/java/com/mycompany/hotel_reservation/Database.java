package com.mycompany.hotel_reservation;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
/**
 *
 * @author ian
 */
public class Database {
    private String DriverName = "com.mysql.jdbc.Driver";
    private String connectionString= "jdbc:mysql://localhost:3306/beebee_hotel";
    private String username = "root";
    private String password = "6991";
    private Connection conn;
    private ResultSet rsltSet;
    
    public void connect(){
        try{
            Class.forName(DriverName);
            conn = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Connected");
        }catch(SQLException a){
            System.out.println(a.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Class exception");
        }
    }
    
    public void insert(String a){
        try{
            PreparedStatement stment = conn.prepareStatement(a);
            stment.execute();
        }catch(SQLException i){
            System.out.println("Statement did not work " + i.getLocalizedMessage());
        }
    }
    
    public void update(String i){
        connect();
        try{
            PreparedStatement statement = conn.prepareStatement(i);
            statement.execute();
        }catch(SQLException a){
            System.out.println(a.getMessage());
        }
    }
    
    public void delete(String i){
        connect();
        try{
            Statement statement = conn.createStatement();
            statement.execute(i);
        }catch(SQLException a){
            System.out.println(a.getMessage());
        }
    }
    
    public boolean getInfo(String i){
        connect();
        boolean a = false;
        try{
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rsltSet = statement.executeQuery(i);
            
            if(rsltSet.first()){
                a = true;
            }else{
                a = false;
            }
            conn.close();
        }catch(SQLException x){
            System.out.println(x.getMessage());
        }
        return a;
    }
    
    public ArrayList<String> getAll(String a, String ...args){
        connect();
        ArrayList<String> list = new ArrayList<>();
        try{
                Statement statement = conn.createStatement();
                rsltSet = statement.executeQuery(a);

               while(rsltSet.next()){
                   for(String i:args){
                       list.add(rsltSet.getString(i));
                   }
                   
               }
                conn.close();
            }catch(SQLException x){
                System.out.println(x.getMessage());
            }
        return list;
    }
    
    public  ObservableList<Rooms> getData(String i){
        ObservableList<Rooms> getRooms = FXCollections.observableArrayList();
       connect();
        try{
                Statement statement = conn.createStatement();
                rsltSet = statement.executeQuery(i);

               while(rsltSet.next()){
                   getRooms.add(new Rooms(rsltSet.getInt("room_id"),rsltSet.getString("room_type"),rsltSet.getDouble("rooms_price"),rsltSet.getString("room_description"),rsltSet.getBoolean("available"),rsltSet.getString("extras"),rsltSet.getInt("branch_id")));
               }
                conn.close();
            }catch(SQLException x){
                System.out.println(x.getMessage());
            }
        
        return getRooms;
    }
    
     public  ObservableList<String> getAllBookings(String i){
        ObservableList<String> getBookings = FXCollections.observableArrayList();
         getBookings.add(String.format("%-15s%-25s%-25s%-25s%-25s%-11s\n","ID Number","Room Number","Duration of Stay","Name","Surname","Total"));
       connect();
        try{
                Statement statement = conn.createStatement();
                rsltSet = statement.executeQuery(i);

               while(rsltSet.next()){
                   getBookings.add(String.format("%-15s%-35d%-35d%-25s%-25s$%-10.2f\n",rsltSet.getString("Id_number"),rsltSet.getInt("room_id"),rsltSet.getInt("duration_of_stay"),rsltSet.getString("name"),rsltSet.getString("surname"),rsltSet.getDouble("total")));
               }
                conn.close();
            }catch(SQLException x){
                System.out.println(x.getMessage());
            }
        
        return getBookings;
    }
}
