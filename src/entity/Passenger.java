package entity;

public class Passenger {
    private int id;
    private String name;
    private String lastName;
    private String identity;

    public Passenger(){}

    public Passenger(int id, String name, String lastName, String identity) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Passenger" + "\n Id: " + id +
                "\n Name: " + name + " " + lastName +
                "\n Identity: " + identity +
                "\n";
    }
}
