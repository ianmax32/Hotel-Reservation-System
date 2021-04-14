/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import java.sql.Date;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author ian
 */
public class ReserveRoomGui extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Add");
        btn.setFont(Font.font("Modern No. 20",FontWeight.EXTRA_BOLD,20));
        btn.setMinSize(300,100);
        btn.setStyle("-fx-background-color : #5BB06E");
        TextField customerId = new TextField();
       TextField customerName = new TextField();
       TextField customerSurname = new TextField();
       TextField customerAge = new TextField();
       TextArea customerAddress = new TextArea();
       TextField customerBankName = new TextField();
       TextField customerCredNum = new TextField();
       DatePicker customerExpDate = new DatePicker();
       customerExpDate.setValue(LocalDate.now());
       TextField durationOfStay = new TextField();
       Label total = new Label();
       
       if(durationOfStay.getText().equals("")){
           total.setText(String.format("%.2f",RoomsGui.table.getSelectionModel().selectedItemProperty().get().getRooms_price()));
       }else{
           durationOfStay.textProperty().addListener(e ->{
                total.setText(String.format("$%f.2",RoomsGui.table.getSelectionModel().selectedItemProperty().get().getRooms_price() * Integer.parseInt(durationOfStay.getText())));
           });
           
       }
       
       Label roomId = new Label();
       roomId.setText(String.format("%d",RoomsGui.table.getSelectionModel().selectedItemProperty().get().getRoom_id()));
       
        GridPane grid = new GridPane();
        grid.setStyle("-fx-font-family: Elephant;");
        
        grid.add(new Label("Customer ID:"), 0, 0);
        grid.add(customerId,1,0);
        grid.add(new Label("Customer Name: "),0,1);
        grid.add(customerName,1,1);
        grid.add(new Label("Customer Surname:"),0,2);
        grid.add(customerSurname,1,2);
        grid.add(new Label("Customer Age: "),0,3);
        grid.add(customerAge,1,3);
        grid.add(new Label("Customer Address: "),0,4);
        grid.add(customerAddress,1,4);
        grid.add(new Label("Bank Name: "),0,5);
        grid.add(customerBankName,1,5);
        grid.add(new Label("Credit Card Number:"),0,6);
        grid.add(customerCredNum,1,6);
        grid.add(new Label("Expiry Date: "),0,7);
        grid.add(customerExpDate,1,7);
        grid.add(new Label("Room Booked:"),0,8);
        grid.add(new Label("Duration: "),0,9);
        grid.add(durationOfStay,1,9);
        grid.add(new Label("Amount:"),0,10);
        grid.add(total,1,10);
        grid.add(new Label("Room Id:"),0,11);
        grid.add(roomId,1,11);
        grid.setHgap(10);
        grid.setVgap(10);
        
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid,btn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color : #B1E0BB");
        layout.setPadding(new Insets(15,15,15,15));
        Scene scene = new Scene(layout, 770, 650);
        
        btn.setOnAction(e ->{
            System.out.println(customerBankName.getText()+customerCredNum.getText()+ customerExpDate.getValue().toString()+ customerId.getText()+ customerName.getText()+ customerSurname.getText()+ Integer.parseInt(customerAge.getText())+customerAddress.getText());
            Customer cust = new Customer(customerBankName.getText(), customerCredNum.getText(), customerExpDate.getValue().toString(), customerId.getText(), customerName.getText(), customerSurname.getText(), Integer.parseInt(customerAge.getText()), customerAddress.getText(),"Customer");
            cust.toString();
            Database db = new Database();
            db.update("update rooms set available='0' where room_id='"+Integer.parseInt(roomId.getText())+"'");
            db.insert("insert into booking values('"+customerId.getText()+"','1','"+durationOfStay.getText()+"','"+(Double.parseDouble(total.getText()) * Double.parseDouble(durationOfStay.getText()))+"','"+Integer.parseInt(roomId.getText())+"')");
            RoomsGui rooms = new RoomsGui();
            Stage st = new Stage();
            primaryStage.close();
            rooms.start(st);
        });
        
        primaryStage.setTitle("Reserve Room");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
}
