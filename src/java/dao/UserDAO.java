/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.User;

/**
 *
 * @author DUNG HOANG PC
 */
public class UserDAO extends DBContext {

    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "Select * from [Users]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8));
                list.add(u);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void register(String name, String gender, String email, String password, String phone, String address, String dob) {
        String key = "";
        key = new SendMail().createCaptcha();
        try {
            String sql = "insert into [Users] ([Name], Gender, Email, [Password], Phone, [Address], DOB, [register_key], [status]) values (?,?,?,?,?,?,?,?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, dob);
            preparedStatement.setString(8, key);
            preparedStatement.executeUpdate();
            
            new SendMail().sentEmail(email, "Verify Account", "http://localhost:9999/LibraryManagementSystem/VerifyAccount?email=" + email + "&key=" + key);
        } catch (Exception e) {
        }
    }

    public void editProfile(String name, String gender, String email, String phone, String address, String dob, String id) {
        try {
            String sql = "Update [Users] set [Name] = ?, Gender = ?, Email = ?, Phone = ?, [Address] = ?, DOB = ? where User_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setString(6, dob);
            statement.setString(7, id);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public String getAlphaNumericString(int n) {
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
    
    public void activeAccount(int id) {
        try {
            String sql = "  Update [Users] set [register_key] = '', status = 1 where User_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int checkKeyExist(String key, String email) {
        try {
            String sql1 = "Select * from [Users] where Email = ? and [register_key] = ?";
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setString(1, email);
            statement.setString(2, key);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
    public User getUserById(int id) {
        try {
            String sql = "Select * from [Users] where User_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
