package controller;

import entity.Passenger;
import model.PassengerModel;
import utils.Utils;

import javax.swing.*;

public class PassengerController {

    public static PassengerModel instanceModel() {
        return new PassengerModel();
    }

    public static String listAllPassengers() {
        StringBuilder message = new StringBuilder();
        message.append("......:::::::   All PASSENGERS   :::::::......\n");
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Passenger passenger = (Passenger) object;
                message.append(passenger).append("--------------------------\n");
            }
        }
        return message.toString();
    }

    public static void showAllPassengers() {
        JOptionPane.showMessageDialog(null, listAllPassengers());
    }

    public static void createPassenger() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter the passenger name");
            String lastName = JOptionPane.showInputDialog(null, "Enter the passenger last name");
            String identity = JOptionPane.showInputDialog(null, "Enter the passenger identity");

            Passenger passenger = new Passenger();
            passenger.setName(name);
            passenger.setLastName(lastName);
            passenger.setIdentity(identity);

            passenger = (Passenger) instanceModel().insert(passenger);

            if (passenger.getId() != 0) {

                JOptionPane.showMessageDialog(null, passenger);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void updatePassenger() {
        try {
//            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllPassengers() + "\nEnter id to update"));
//            Passenger passenger = (Passenger) instanceModel().findById(number);
            Object[] options = Utils.listToArray(instanceModel().findAll());
            System.out.println(options[0]);
            Passenger passenger = (Passenger) JOptionPane.showInputDialog(null,
                    "Select passenger to update",
                    "Update",
                    JOptionPane.QUESTION_MESSAGE,null,
                    options,
                    options[0]);
            String name = JOptionPane.showInputDialog(null, "Enter the passenger name",passenger.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter the passenger last name",passenger.getLastName());
            String identity = JOptionPane.showInputDialog(null, "Enter the passenger identity",passenger.getIdentity());

            passenger.setName(name);
            passenger.setLastName(lastName);
            passenger.setIdentity(identity);

            if (instanceModel().update(passenger)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void deletePassenger() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllPassengers() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findPassengerById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an passenger"));
            Passenger passenger = (Passenger) instanceModel().findById(number);
            if (passenger != null) {
                JOptionPane.showMessageDialog(null, passenger);
            } else {
                JOptionPane.showMessageDialog(null, "This passenger doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void menu() {
        String option;
        String message = """
                ....::::::   PASSENGERS MENU   ::::::....
                1. Show passengers.
                2. Create passenger.
                3. Update passenger.
                4. Delete passenger.
                5. Find passenger.
                6. Exit.
                                
                ENTER THE OPTION TO CONTINUE...
                """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option) {
                case "1":
                    showAllPassengers();
                    break;
                case "2":
                    createPassenger();
                    break;
                case "3":
                    updatePassenger();
                    break;
                case "4":
                    deletePassenger();
                    break;
                case "5":
                    findPassengerById();
                    break;
            }
        } while (!option.equals("6"));
    }

}
