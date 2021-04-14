/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_reservation;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author ian
 */
public class Login extends Application {
    private Database db = new Database();
    @Override
    public void start(Stage primaryStage) {
        Label mainLabel = new Label("BEE-BEE HOTEL");
        Label userNotAvailable = new Label("User Not Available: Wrong Password or Username");
         userNotAvailable.setFont(Font.font("Modern No. 20",15));
         userNotAvailable.setTextFill(Paint.valueOf("RED"));
        userNotAvailable.setVisible(false);
        mainLabel.setFont(Font.font("Impact",50));
        Button btn = new Button("Login");
        btn.setFont(Font.font(20));
        
        TextField empId = new TextField();
        empId.setMinSize(400,20);
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
         password.setMinSize(400,18);
        Label lblEmpId = new Label("Employee Id:");
        lblEmpId.setFont(Font.font("Cooper Black",20));
        Label lblPassword = new Label("Password:");
         lblPassword.setFont(Font.font("Cooper Black",20));
         
         GridPane grid = new GridPane();
         grid.setHgap(10);
         grid.setVgap(10);
         grid.add(lblEmpId, 0, 0);
         grid.add(empId, 1, 0);
         grid.add(lblPassword, 0, 1);
         grid.add(password, 1, 1);
   
        VBox main = new VBox(10);
        main.setAlignment(Pos.CENTER);
        main.getChildren().addAll(mainLabel,grid,btn,userNotAvailable);
        main.setPadding(new Insets(50,50,50,50));
        main.setStyle("-fx-background-color : #B1E0BB");
        Scene scene = new Scene(main, 650, 500);
        btn.setOnAction(e -> {
            boolean employeeAvailable = db.getInfo("select * from employee where employee_id ='"+empId.getText()+"'  and password='"+password.getText()+"'");
            if(employeeAvailable){
                MainHomePage home = new MainHomePage();
                home.start(primaryStage);
                empId.setText("");
                password.setText("");
                //this.stop();
            }else{
                userNotAvailable.setVisible(true);
                 empId.setText("");
                password.setText("");
            }
        });
        
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
       
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
