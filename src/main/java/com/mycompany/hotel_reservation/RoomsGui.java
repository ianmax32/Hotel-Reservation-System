/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ian
 */
public class RoomsGui extends Application{
    private Database db = new Database();
   public static TableView<Rooms> table;
   
    
    @Override
    public void start(Stage primaryStage) {
        Button btnBook = new Button("Book");
        btnBook.setFont(Font.font("Modern No. 20",FontWeight.EXTRA_BOLD,20));
        btnBook.setMinSize(300,100);
        btnBook.setStyle("-fx-background-color : #5BB06E");
        
        TableColumn<Rooms, String> columnRoom_id = new TableColumn<>("Room Number");
        columnRoom_id.setMinWidth(25);
        columnRoom_id.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        
        TableColumn<Customer, String> customer_id = new TableColumn<>("Room Number");
        columnRoom_id.setMinWidth(25);
        columnRoom_id.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        
        TableColumn<Rooms, String> columnRoom_Type = new TableColumn<>("Room Type");
        columnRoom_Type.setMinWidth(200);
        columnRoom_Type.setCellValueFactory(new PropertyValueFactory<>("room_type"));

        TableColumn<Rooms, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rooms_price"));
        
        TableColumn<Rooms, String> columnRoom_Desc = new TableColumn<>("Room Description");
        columnRoom_Desc.setMinWidth(500);
        columnRoom_Desc.setStyle("-fx-cell-size:50px");
        columnRoom_Desc.setCellValueFactory(new PropertyValueFactory<>("room_description"));
        
        TableColumn<Rooms, Boolean> columnRoom_Avail = new TableColumn<>("Room Available");
        columnRoom_Avail.setMinWidth(150);
        columnRoom_Avail.setCellValueFactory(new PropertyValueFactory<>("available"));
        
      TableColumn<Rooms, String> columnRoom_Extras = new TableColumn<>("Room Extras");
        columnRoom_Extras.setMinWidth(125);
        columnRoom_Extras.setCellValueFactory(new PropertyValueFactory<>("extras"));
        
        TableColumn<Rooms,Integer> book = new TableColumn<>("Branch");
        book.setMinWidth(75);
        book.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        
        table = new TableView<>();
        Label roomNotAvailable = new Label("Room currently occupied!!");
        roomNotAvailable.setVisible(false);
        roomNotAvailable.setFont(Font.font("Modern No. 20",15));
         roomNotAvailable.setTextFill(Paint.valueOf("RED"));
        
        
        ObservableList<Rooms> list = db.getData("select * from rooms where available='1'");
        for(Rooms i : list){
            table.getItems().addAll(i);
        }
       
        table.getColumns().addAll(columnRoom_id,columnRoom_Type ,priceColumn,columnRoom_Desc,columnRoom_Avail,columnRoom_Extras,book);
    
        btnBook.setOnAction(e->{
            if(table.getSelectionModel().selectedItemProperty().get().isAvailable()){
                ReserveRoomGui reserve = new ReserveRoomGui();
                Stage r = new Stage();
                r.isAlwaysOnTop();
                reserve.start(r);
                primaryStage.close();
            }else{
                roomNotAvailable.setVisible(true);
            }
            
        });
        Button exit = new Button("Exit");
        exit.setAlignment(Pos.TOP_RIGHT);
        exit.setOnAction(e->{
            MainHomePage home = new MainHomePage();
            Stage homeStage = new Stage();
            primaryStage.close();
            home.start(homeStage);
            
        });
        
        Label roomsLabel = new Label("BEE-BEE Hotel");
        roomsLabel.setFont(Font.font("Impact",50));
        HBox hotelLabel = new HBox(50);
        hotelLabel.setAlignment(Pos.CENTER);
        hotelLabel.getChildren().addAll(roomsLabel,exit);
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color : #B1E0BB");
        layout.getChildren().addAll(hotelLabel,table,roomNotAvailable,btnBook);
        layout.setPadding(new Insets(15,15,15,15));
       
        Scene scene = new Scene(layout, 1400, 700);
        
        primaryStage.setTitle("Rooms Available");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.centerOnScreen();
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }

}
