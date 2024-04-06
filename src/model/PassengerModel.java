package model;

import database.CRUD;
import database.ConfigDB;
import entity.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Passenger passenger = (Passenger) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO passengers (name,last_name,identity) VALUES (?,?,?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, passenger.getName());
            ps.setString(2, passenger.getLastName());
            ps.setString(3, passenger.getIdentity());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    passenger.setId(rs.getInt(1));
                    System.out.println("Insert: passenger inserted successfully");
                }
            }
        } catch (SQLException e) {
            System.out.println("Insert: error in database" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return passenger;
    }

    @Override
    public boolean update(Object object) {
        Passenger passenger = (Passenger) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE passengers SET name = ?,last_name = ?,identity = ? WHERE id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, passenger.getName());
            ps.setString(2, passenger.getLastName());
            ps.setString(3, passenger.getIdentity());
            ps.setInt(4, passenger.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update: passenger update successfully");
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
        String sql = "DELETE FROM passengers WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Delete: passenger deleted successfully");
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
        List<Object> passengers = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM passengers";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passengers.add(new Passenger(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("identity")));
            }

        } catch (SQLException e) {
            System.out.println("FindAll: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return passengers;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        Passenger passenger = null;
        String sql = "SELECT * FROM passengers WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passenger = new Passenger(rs.getInt("id"),rs.getString("name"), rs.getString("last_name"),rs.getString("identity"));
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return passenger;
    }
    public Object findByName(String name) {
        Connection connection = ConfigDB.openConnection();
        Passenger passenger = null;
        String sql = "SELECT * FROM passengers WHERE name like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passenger = new Passenger(rs.getInt("id"),rs.getString("name"), rs.getString("last_name"),rs.getString("identity"));
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return passenger;
    }
}
