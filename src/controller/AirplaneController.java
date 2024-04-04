package controller;

import entity.Airplane;
import jdk.jshell.execution.Util;
import model.AirplaneModel;
import utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirplaneController {

    public static AirplaneModel instanceModel() {
        return new AirplaneModel();
    }

    public static String listAllAirplanes() {
        StringBuilder message = new StringBuilder();
        message.append("......:::::::   All AIRPLANES   :::::::......\n");
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Airplane airplane = (Airplane) object;
                message.append(airplane).append("--------------------------\n");
            }
        }
        return message.toString();
    }

    public static void showAllAirplanes(){
        JOptionPane.showMessageDialog(null,listAllAirplanes());
    }

    public static void createAirplane() {
        try {
            String model = JOptionPane.showInputDialog(null, "Enter the airplane model");
            int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the airplane capacity"));

            Airplane airplane = new Airplane();
            airplane.setModel(model);
            airplane.setCapacity(capacity);

            airplane = (Airplane) instanceModel().insert(airplane);

            if (airplane.getId() != 0) {

                JOptionPane.showMessageDialog(null, airplane);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void updateAirplane() {
        try {
            Object[] options = Utils.listToArray(instanceModel().findAll());
            System.out.println(options[0]);
            Airplane airplane = (Airplane) JOptionPane.showInputDialog(null,
                    "select options",
                    "update",
                    JOptionPane.QUESTION_MESSAGE,null,
                    options,
                    options[0]);
//            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllAirplanes() + "\nEnter id to update"));
//            Airplane airplane = (Airplane) instanceModel().findById(number);
            String model = JOptionPane.showInputDialog(null, "Enter the airplane model", airplane.getModel());
            int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the airplane capacity", airplane.getCapacity()));
            airplane.setModel(model);
            airplane.setCapacity(capacity);
            if (instanceModel().update(airplane)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void deleteAirplane() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllAirplanes() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findAirplaneById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an airplane"));
            Airplane airplane = (Airplane) instanceModel().findById(number);
            if (airplane != null) {
                JOptionPane.showMessageDialog(null, airplane);
            } else {
                JOptionPane.showMessageDialog(null, "This airplane doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void menu(){
        String option;
        String message = """
                            ....::::::   AIRPLANES MENU   ::::::....
                            1. Show Airplanes.
                            2. Create airplane.
                            3. Update airplane.
                            4. Delete airplane.
                            5. Find airplane.
                            6. Exit.
                                            
                            ENTER THE OPTION TO CONTINUE...
                            """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option){
                case "1":
                    showAllAirplanes();
                    break;
                case "2":
                    createAirplane();
                    break;
                case "3":
                    updateAirplane();
                    break;
                case "4":
                    deleteAirplane();
                    break;
                case "5":
                    findAirplaneById();
                    break;
            }
        } while (!option.equals("6"));
    }

}
