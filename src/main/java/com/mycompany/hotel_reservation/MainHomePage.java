/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import static com.mycompany.hotel_reservation.RoomsGui.table;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author ian
 */
public class MainHomePage extends Application {
    Database db = new Database();
    private  ListView<String> bookedRoomsList;
    private ObservableList<String> bookings = FXCollections.observableArrayList();
    
    public void loadList(){
        bookings = db.getAllBookings("SELECT booking.Id_number,booking.room_id,booking.duration_of_stay, person.name,person.surname,booking.total FROM beebee_hotel.person join booking on booking.Id_number=person.Id_number;");
         bookedRoomsList = new ListView<>(bookings);
    }
    
    @Override
    public void start(Stage primaryStage) {
          loadList();
        Button btnReserve = new Button("Room Reserve");
        Button btnAddEmp = new Button("Add Employee");
        Button btnExit = new Button("Exit");
       bookedRoomsList.setStyle("-fx-background-color : #5281A5");
        
       Label listOfCustomers = new Label("List of customers currently checked in");
        listOfCustomers.setFont(Font.font("Modern No. 20",15));
       Button checkout = new Button("Check Out"); 
       checkout.setStyle("-fx-background-color : #CAE4AA");
       checkout.setDisable(true);
       checkout.setMinSize(300,80);
       
     
       
       bookedRoomsList.setOnMouseClicked(e ->{
           if(bookedRoomsList.getSelectionModel().getSelectedIndex() != 0){
               checkout.setDisable(false);
                checkout.setStyle("-fx-background-color : #57A7C9");
           }
       });
           
       checkout.setOnAction(e->{
           //System.out.println(bookedRoomsList.getSelectionModel().getSelectedItem().substring(0,11));
           db.delete("delete from customer where Id_number='"+bookedRoomsList.getSelectionModel().getSelectedItem().substring(0,11).toString()+"'");
            db.delete("delete from person where Id_number='"+bookedRoomsList.getSelectionModel().getSelectedItem().substring(0,11).toString()+"'");
           db.update("update rooms set available='1' where room_id='"+Integer.parseInt(bookedRoomsList.getSelectionModel().getSelectedItem().substring(15,16))+"'");
            loadList();
       });
       
       btnReserve.setOnAction(e ->{
           RoomsGui rooms = new RoomsGui();
           rooms.start(primaryStage);
        });
        btnReserve.setMinSize(200,200);
        btnAddEmp.setMinSize(200,200);
        btnExit.setMinSize(200,200);
        btnExit.setOnAction(e ->{
            primaryStage.close();
            Login login = new Login();
            login.start(primaryStage);
        });
        
        HBox allBtns = new HBox(10);
        allBtns.getChildren().addAll(btnReserve, btnAddEmp,btnExit);
        allBtns.setPadding(new Insets(15,15,15,15));
        allBtns.setAlignment(Pos.TOP_CENTER);
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15,15,15,15));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(allBtns,listOfCustomers,bookedRoomsList,checkout);
        layout.setStyle("-fx-background-color : #B1E0BB");
        Scene scene = new Scene(layout, 900, 600);
        
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
}
