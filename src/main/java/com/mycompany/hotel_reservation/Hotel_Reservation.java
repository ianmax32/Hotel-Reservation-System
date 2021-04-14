package com.mycompany.hotel_reservation;
import javafx.application.Application;
/**
 *
 * @author ian
 */
public class Hotel_Reservation {
    public static void main(String args[]){
        System.out.println("Hello work");
        //Database db = new Database();
        //db.connect();
        
        //Rooms room1 = new Room();
        /*Employee emp1 = new Employee("63457815678","Tanya" , "Miches",30,"5 Glanmore road Chisipite",500,"Day",6);
        Employee emp2 = new Employee("45789698541","Ian" , "Masaga",26,"9729 Glenview 7 Harare",500,"Day",6);
        Employee emp3 = new Employee("69854712457","Babra" , "Chamba",23,"4 Zebra close Borrodale West",500,"Night",6);
        Employee emp4 = new Employee("63547841257","Tino" , "Kitso",28,"16 Coltman Road Mount Pleasant",500,"Day",6);
        Employee emp5 = new Employee("89745136541","Kuda" , "Michesa",32,"5 kent Road Mandara",500,"Night",6);*/
        
        //Customer cust = new Customer("Stanbic Bank", "2145789654782", "12/12/21", "45789632145", "Ian", "Masaga", 26, "address");
        
        
        System.out.println(RoomsGui.table.getSelectionModel().getSelectedItems().toString());
    }
}
