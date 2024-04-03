package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Airplane airplane = (Airplane) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO airplanes (model,capacity) VALUES (?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,airplane.getModel());
            ps.setInt(2,airplane.getCapacity());
            int rows = ps.executeUpdate();
            if(rows>0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    airplane.setId(rs.getInt(1));
                    System.out.println("Insert: airplane inserted successfully");
                }
            }
        }catch (SQLException e){
            System.out.println("Insert: error in database"+e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return airplane;
    }

    @Override
    public boolean update(Object object) {
        Airplane airplane = (Airplane) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE airplanes SET model = ?,capacity = ? WHERE id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, airplane.getModel());
            ps.setInt(2, airplane.getCapacity());
            ps.setInt(3, airplane.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update: airplane update successfully");
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
        String sql = "DELETE FROM airplanes WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Delete: airplane deleted successfully");
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
        List<Object> airplanes = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM airplanes";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                airplanes.add(new Airplane(rs.getInt("id"),rs.getString("model"), rs.getInt("capacity")));
            }

        } catch (SQLException e) {
            System.out.println("FindAll: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return airplanes;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        Airplane airplane = null;
        String sql = "SELECT * FROM airplanes WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                airplane = new Airplane(rs.getInt("id"),rs.getString("model"), rs.getInt("capacity"));
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return airplane;
    }


}
