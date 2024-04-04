package model;

import database.CRUD;
import database.ConfigDB;
import entity.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Flight flight = (Flight) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO flights (destination, departure_date, departure_time, id_airplane) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,flight.getDestination());
            ps.setString(2,flight.getDepartureDate());
            ps.setString(3,flight.getDepartureTime());
            ps.setInt(4,flight.getIdAirplane());

            int rows = ps.executeUpdate();
            if(rows>0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    flight.setId(rs.getInt(1));
                    System.out.println("Insert: flight inserted successfully");
                }
            }
        }catch (SQLException e){
            System.out.println("Insert: error in database"+e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return flight;
    }

    @Override
    public boolean update(Object object) {
        Flight flight = (Flight) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE flights SET destination = ?,departure_date = ?, " +
                "departure_time = ?, id_airplane = ? WHERE id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, flight.getDestination());
            ps.setString(2, flight.getDepartureDate());
            ps.setString(3, flight.getDepartureTime());
            ps.setInt(4, flight.getIdAirplane());
            ps.setInt(5, flight.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update: flight update successfully");
                isUpdated = true;
            }

        } catch (SQLException e) {
            System.out.println("Update: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "DELETE FROM flights WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Delete: flight deleted successfully");
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Delete: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        List<Object> flights = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM flights";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flights.add(new Flight(rs.getInt("id"),rs.getString("destination"), rs.getString("departure_date"),rs.getString("departure_time"),rs.getInt("id_airplane")));
            }

        } catch (SQLException e) {
            System.out.println("FindAll: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return flights;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        Flight flight = null;
        String sql = "SELECT * FROM flights WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flight = new Flight(rs.getInt("id"),rs.getString("destination"), rs.getString("departure_date"),rs.getString("departure_time"),rs.getInt("id_airplane"));
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return flight;
    }
}
