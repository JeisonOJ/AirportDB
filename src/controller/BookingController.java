package controller;

import entity.Booking;
import model.BookingModel;

import javax.swing.*;

public class BookingController {

    public static BookingModel instanceModel(){
        return new BookingModel();
    }

    public static String listAllBookings() {
        StringBuilder message = new StringBuilder();
        message.append("......:::::::   All BOOKINGS   :::::::......\n");
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Booking booking = (Booking) object;
                message.append(booking).append("--------------------------\n");
            }
        }
        return message.toString();
    }

    public static void showAllBookings(){
        JOptionPane.showMessageDialog(null,listAllBookings());
    }

    public static void createBooking() {
        try {
            String date = JOptionPane.showInputDialog(null, "Enter the booking date");
            String seat = JOptionPane.showInputDialog(null, "Enter the booking departure");
            int flightId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the flight id"));
            int passengerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the passenger id"));

            Booking booking = new Booking();
            booking.setBookingDate(date);
            booking.setSeat(seat);
            booking.setIdFlight(flightId);
            booking.setIdPassenger(passengerId);

            booking = (Booking) instanceModel().insert(booking);

            if (booking.getId() != 0) {
                JOptionPane.showMessageDialog(null, booking);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }
    public static void updateBooking() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllBookings() + "\nEnter id to update"));
            Booking booking = (Booking) instanceModel().findById(number);
            String date = JOptionPane.showInputDialog(null, "Enter the booking date");
            String seat = JOptionPane.showInputDialog(null, "Enter the booking departure");
            int flightId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the flight id"));
            int passengerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the passenger id"));

            booking.setBookingDate(date);
            booking.setSeat(seat);
            booking.setIdFlight(flightId);
            booking.setIdPassenger(passengerId);

            if (instanceModel().update(booking)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }
    }

    public static void deleteBooking() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllBookings() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findBookingById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an booking"));
            Booking booking = (Booking) instanceModel().findById(number);
            if (booking != null) {
                JOptionPane.showMessageDialog(null, booking);
            } else {
                JOptionPane.showMessageDialog(null, "This booking doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findBookingByIdAndShowDetails() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an booking"));
            Booking booking = (Booking) instanceModel().findByIdAndShowDetails(number);
            if (booking != null) {
                JOptionPane.showMessageDialog(null, booking);
            } else {
                JOptionPane.showMessageDialog(null, "This booking doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void menu(){
        String option;
        String message = """
                            ....::::::   BOOKINGS MENU   ::::::....
                            1. Show Bookings.
                            2. Create booking.
                            3. Update booking.
                            4. Delete booking.
                            5. Find booking.
                            6. Find booking and details.
                            7. Exit.
                                            
                            ENTER THE OPTION TO CONTINUE...
                            """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option){
                case "1":
                    showAllBookings();
                    break;
                case "2":
                    createBooking();
                    break;
                case "3":
                    updateBooking();
                    break;
                case "4":
                    deleteBooking();
                    break;
                case "5":
                    findBookingById();
                    break;
                case "6":
                    findBookingByIdAndShowDetails();
                    break;
            }
        } while (!option.equals("7"));
    }

}
