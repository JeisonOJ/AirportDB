package entity;

public class Booking {
    private int id;
    private String bookingDate;
    private int seat;
    private int idPassenger;
    private int idFlight;
    private Passenger passenger;
    private Flight flight;

    public Booking(){}

    public Booking(int id, String bookingDate, int seat, int idPassenger, int idFlight) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.seat = seat;
        this.idPassenger = idPassenger;
        this.idFlight = idFlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("Booking").append("\n Id: ").append(id)
                .append("\n Booking date: ").append(bookingDate)
                .append("\n Seat: ").append(seat)
                .append("\n Passenger id: ").append(idPassenger)
                .append("\n Flight id: ").append(idFlight)
                .append("\n");
        if(passenger != null){
            message.append("\n").append(passenger);
        }
        if(flight != null){
            message.append("\n").append(flight);
        }
        message.append("\n");
        return message.toString();
    }
}
