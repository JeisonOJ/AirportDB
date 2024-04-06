package controller;

import entity.Airplane;
import entity.Flight;
import entity.Passenger;
import model.FlightModel;
import utils.Utils;

import javax.swing.*;

public class FlightController {

    public static FlightModel instanceModel() {
        return new FlightModel();
    }

    public static String listAllFlights() {
        StringBuilder message = new StringBuilder();
        message.append("......:::::::   All FLIGHTS   :::::::......\n");
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Flight flight = (Flight) object;
                message.append(flight).append("--------------------------\n");
            }
        }
        return message.toString();
    }

    public static void showAllFlights() {
        JOptionPane.showMessageDialog(null, listAllFlights());
    }

    public static void createFlight() {
        try {
            String destination = JOptionPane.showInputDialog(null, "Enter the flight destination");
            String year = JOptionPane.showInputDialog(null, "Enter the departure year");
            String month = JOptionPane.showInputDialog(null, "Enter the departure month");
            String day = JOptionPane.showInputDialog(null, "Enter the departure day");
            String departureDate = year + "-" + month + "-" + day;
            String hour = JOptionPane.showInputDialog(null, "Enter the flight departure hour(24 hours)");
            String minutes = JOptionPane.showInputDialog(null, "Enter the flight departure minutes(60 minutes)");
            String departureTime = hour + ":" + minutes + ":00";
            Object[] airplanes = Utils.listToArray(AirplaneController.instanceModel().findAll());
            Airplane airplane = (Airplane) JOptionPane.showInputDialog(null,
                    "Select airplane",
                    "Airplanes",
                    JOptionPane.QUESTION_MESSAGE,null,
                    airplanes,
                    airplanes[0]);

            Flight flight = new Flight();
            flight.setDestination(destination);
            flight.setDepartureDate(departureDate);
            flight.setDepartureTime(departureTime);
            flight.setIdAirplane(airplane.getId());
            flight.setAirplane(airplane);

            flight = (Flight) instanceModel().insert(flight);

            if (flight.getId() != 0) {
                JOptionPane.showMessageDialog(null, flight);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void updateFlight() {
        try {
//            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllFlights() + "\nEnter id to update"));
//            Flight flight = (Flight) instanceModel().findById(number);
            Object[] flights = Utils.listToArray(instanceModel().findAll());
            Flight flight = (Flight) JOptionPane.showInputDialog(null,
                    "Select flight to update",
                    "Update",
                    JOptionPane.QUESTION_MESSAGE, null,
                    flights,
                    flights[0]);
            String destination = JOptionPane.showInputDialog(null, "Enter the flight destination", flight.getDestination());
            String departureDate = JOptionPane.showInputDialog(null, "Enter the flight departure date", flight.getDepartureDate());
            String departureTime = JOptionPane.showInputDialog(null, "Enter the flight departure time", flight.getDepartureTime());
            int idAirplane = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the airplane id"),flight.getIdAirplane());

            flight.setDestination(destination);
            flight.setDepartureDate(departureDate);
            flight.setDepartureTime(departureTime);
            flight.setIdAirplane(idAirplane);

            if (instanceModel().update(flight)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }
    }

    public static void deleteFlight() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllFlights() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findFlightById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an flight"));
            Flight flight = (Flight) instanceModel().findById(number);
            if (flight != null) {
                JOptionPane.showMessageDialog(null, flight);
            } else {
                JOptionPane.showMessageDialog(null, "This flight doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findFlightByDestination() {
            String destination = JOptionPane.showInputDialog(null, "Enter destination to find an flight");
            Flight flight = (Flight) instanceModel().findByDestination(destination);
            if (flight != null) {
                JOptionPane.showMessageDialog(null, flight);
            } else {
                JOptionPane.showMessageDialog(null, "This flight doesn't exist");
            }
    }

    public static void findFlightByIdAndShowDetails() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an flight"));
            Flight flight = (Flight) instanceModel().findByIdAndShowDetails(number);
            if (flight != null) {
                JOptionPane.showMessageDialog(null, flight);
            } else {
                JOptionPane.showMessageDialog(null, "This flight doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void menu() {
        String option;
        String message = """
                ....::::::   FLIGHTS MENU   ::::::....
                1. Show Flights.
                2. Create flight.
                3. Update flight.
                4. Delete flight.
                5. Find flight by ID.
                6. Find flight by destination.
                7. Find flight and details.
                8. Exit.
                                
                ENTER THE OPTION TO CONTINUE...
                """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option) {
                case "1":
                    showAllFlights();
                    break;
                case "2":
                    createFlight();
                    break;
                case "3":
                    updateFlight();
                    break;
                case "4":
                    deleteFlight();
                    break;
                case "5":
                    findFlightById();
                    break;
                case "6":
                    findFlightByDestination();
                    break;
                case "7":
                    findFlightByIdAndShowDetails();
                    break;
            }
        } while (!option.equals("8"));
    }

}
