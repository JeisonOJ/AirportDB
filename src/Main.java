import controller.AirplaneController;
import controller.FlightController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String option;
        String message = """
                ....::::::   MENU   ::::::....
                1. Airplane menu.
                2. Flight menu.
                3. Passenger menu.
                4. Booking menu.
                5. Exit.
                                
                ENTER THE OPTION TO CONTINUE...
                """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option) {
                case "1":
                    AirplaneController.menu();
                    break;
                case "2":
                    FlightController.menu();
                    break;
                case "3":
                    System.out.println("passenger");
                    break;
                case "4":
                    System.out.println("booking");
                    break;
            }
        } while (!option.equals("5"));
    }
}