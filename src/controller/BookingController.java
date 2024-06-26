package controller;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import model.BookingModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class BookingController {

    public static BookingModel instanceModel() {
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

    public static void showAllBookings() {
        JOptionPane.showMessageDialog(null, listAllBookings());
    }

    /**
     * Create and validate seats available in a flight
     */
    public static void createBooking() {
        try {
            Object[] flights = Utils.listToArray(FlightController.instanceModel().findAll());
            Object[] passengers = Utils.listToArray(PassengerController.instanceModel().findAll());
            Passenger passenger = (Passenger) JOptionPane.showInputDialog(null,
                    "Select passenger",
                    "Passengers",
                    JOptionPane.QUESTION_MESSAGE, null,
                    passengers,
                    passengers[0]);
            Flight flight = (Flight) JOptionPane.showInputDialog(null,
                    "Select flight",
                    "Flights",
                    JOptionPane.QUESTION_MESSAGE, null,
                    flights,
                    flights[0]);
            Object[] seats = Utils.listToArray(seatsAvailable(flight.getId()));
            int seat = 0;
            if (seats.length>0){
                seat = (int) JOptionPane.showInputDialog(null,
                        "Select seat available",
                        "Seats",
                        JOptionPane.QUESTION_MESSAGE, null,
                        seats,
                        seats[0]);
            }else {
                JOptionPane.showMessageDialog(null,"The flight is full capacity and doesn't have any seat available.");
                return;
            }
            String year = JOptionPane.showInputDialog(null, "Enter the booking year");
            String month = JOptionPane.showInputDialog(null, "Enter the booking month");
            String day = JOptionPane.showInputDialog(null, "Enter the booking day");

            String date = year + "-" + month + "-" + day;
            Booking booking = new Booking();
            booking.setBookingDate(date);
            booking.setSeat(seat);
            booking.setIdFlight(flight.getId());
            booking.setIdPassenger(passenger.getId());
            booking.setFlight(flight);
            booking.setPassenger(passenger);
            if (validateSeatsAndCapacity(booking)) {
                booking = (Booking) instanceModel().insert(booking);
                if (booking.getId() != 0) {
                    JOptionPane.showMessageDialog(null, booking);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Creating booking: Enter valid data" + e.getMessage());
        }

    }

    public static void updateBooking() {
        try {
//            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllBookings() + "\nEnter id to update"));
//            Booking booking = (Booking) instanceModel().findById(number);
            Object[] bookings = Utils.listToArray(instanceModel().findAll());
            Booking booking = (Booking) JOptionPane.showInputDialog(null,
                    "Select booking to update",
                    "Update",
                    JOptionPane.QUESTION_MESSAGE, null,
                    bookings,
                    bookings[0]);
            Object[] flights = Utils.listToArray(FlightController.instanceModel().findAll());
            Object[] passengers = Utils.listToArray(PassengerController.instanceModel().findAll());
            Passenger passenger = (Passenger) JOptionPane.showInputDialog(null,
                    "Select passenger",
                    "Passengers",
                    JOptionPane.QUESTION_MESSAGE, null,
                    passengers,
                    passengers[0]);
            Flight flight = (Flight) JOptionPane.showInputDialog(null,
                    "Select flight",
                    "Flights",
                    JOptionPane.QUESTION_MESSAGE, null,
                    flights,
                    flights[0]);
            Object[] seats = Utils.listToArray(seatsAvailable(flight.getId()));
            int seat = 0;
            if (seats.length>0){
                seat = (int) JOptionPane.showInputDialog(null,
                        "Select seat available",
                        "Seats",
                        JOptionPane.QUESTION_MESSAGE, null,
                        seats,
                        seats[0]);
            }else {
                JOptionPane.showMessageDialog(null,"The flight is full capacity and doesn't have any seat available.");
                return;
            }
            String date = JOptionPane.showInputDialog(null, "Enter the booking date", booking.getBookingDate());
            booking.setBookingDate(date);
            booking.setSeat(seat);
            booking.setIdFlight(flight.getId());
            booking.setIdPassenger(passenger.getId());
            booking.setPassenger(passenger);
            booking.setFlight(flight);
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

    public static boolean validateSeatsAndCapacity(Object object) {
        boolean isValid = true;
        Booking booking = (Booking) object;
        List<Object> bookingsForId = instanceModel().bookingsForFlights(booking.getIdFlight());
        if (!bookingsForId.isEmpty()) {

            for (Object objectDB : bookingsForId) {
                Booking bookingDB = (Booking) objectDB;
                if (bookingsForId.size() < bookingDB.getFlight().getAirplane().getCapacity()) {
                    if (booking.getSeat() == bookingDB.getSeat()) {
                        JOptionPane.showMessageDialog(null, "The seat isn't available");
                        isValid = false;
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The flight capacity is full");
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static List<Integer> seatsAvailable(int id) {
        List<Object> bookings = instanceModel().bookingsForFlights(id);
        List<Integer> allSeats;

        if (!bookings.isEmpty()) {
            Booking firstBooking = (Booking) bookings.getFirst();
            allSeats = firstBooking.getFlight().getAirplane().getSeats();
            for (Object object : bookings) {
                Booking booking = (Booking) object;
                if (allSeats.contains(booking.getSeat())){
                    allSeats.remove((Object)booking.getSeat());
                }
            }
        }else{
            Flight flight = (Flight) FlightController.instanceModel().findByIdAndShowDetails(id);
            allSeats = flight.getAirplane().getSeats();
        }

        return allSeats;
    }

    public static void menu() {
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
            switch (option) {
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
