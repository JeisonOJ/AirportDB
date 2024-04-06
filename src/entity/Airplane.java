package entity;

import java.util.ArrayList;
import java.util.List;

public class Airplane {

    private int id;
    private String model;
    private int capacity;
    private final List<Integer> seats = new ArrayList<>();

    public Airplane(){}

    public Airplane(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        setSeats(capacity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        setSeats(capacity);
        this.capacity = capacity;
    }

    public void setSeats(int capacity){
        for (int seat = 1; seat <= capacity; seat++) {
            seats.add(seat);
        }
    }

    public List<Integer> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Airplane" + "\n Id: " + id +
                "\n Model: " + model +
                "\n Capacity: " + capacity +
                "\n Seats: " + getSeats().size() +
                "\n";
    }
}
