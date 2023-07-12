/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Books;
import model.Category;

/**
 *
 * @author DUNG HOANG PC
 */
public class BookDao extends DBContext{
    public static final int ROWS_PER_PAGE = 6;
    public ArrayList<Books> getBookByFilter(String search, String cateId) {
        ArrayList<Books> list = new ArrayList<>();
        try {
            String sql = "Select * from Books b, Category c\n" +
                    "where b.Category_id = c.ID and b.Title like ? ";
            if(!cateId.isEmpty()) {
                sql = sql + "  and b.Category_id = " + cateId;
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Books b = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), new Category(rs.getInt(12), rs.getString(13)));
                list.add(b);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<Category> getAllCate() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "Select * from Category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public Books getBookById(String id) {
        try {
            String sql = "Select * from Books b, Category c\n" +
                    "where b.Category_id = c.ID and b.Book_ID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Books b = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), new Category(rs.getInt(12), rs.getString(13)));
                return b;
            }
        } catch (Exception e) {
        }
        return null;
    }
    public ArrayList<Books> getAllBooks() {
        ArrayList<Books> list = new ArrayList<>();
        try {
            String sql = "Select * from Books b, Category c where b.Category_id = c.ID ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Books b = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), new Category(rs.getInt(12), rs.getString(13)));
                list.add(b);
            }
        } catch (Exception e) {
                
        }
        return list;
    }
    public void insertBook(String title, String author, String publisher, String date, String cateId, String img,
            String description, String country, String authorDesc, String stock) {
        try {
            String sql = " insert into Books ([Title],[Author],[Publisher],[Date_of_publication],[Category_id],[Img],[Description],"
                    + "[Country],[Author_desc],[stock]) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, author);
            stm.setString(3, publisher);
            stm.setString(4, date);
            stm.setString(5, cateId);
            stm.setString(6, img);
            stm.setString(7, description);
            stm.setString(8, country);
            stm.setString(9, authorDesc);
            stm.setString(10, stock);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void deleteBook(String bookId) {
        try {
            String sql = "Delete from Books where Book_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bookId);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int getNumberPage() {
        String sql = "select count(*) from Books";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total/5;
                if (total %5 != 0){
                    countPage++;
                } else {
                    return countPage;
                }
            }
        } catch (Exception e){
            
        }
        return 0;
    }
    public ArrayList<Books> getPagging(int index) {
        String sql = "Select * from Books\n" +
                    "order by Book_ID\n " +
                "OFFSET ? ROWS \n"+
                "FETCH FIRST 5 ROWS ONLY";
        List<Books> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index-1)*5);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                list.add(new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), new Category(rs.getInt(12), rs.getString(13))));
            }
            return (ArrayList<Books>) list;
        } catch (Exception e) {
        }
        return null;
    }
    
    
     public ArrayList<Books> getRecords(int offset, String search, String cateId) {
    ArrayList<Books> list = new ArrayList<>();
    try {
        String sql = "SELECT * FROM Books b, Category c WHERE b.Category_id = c.ID AND b.Title LIKE ?";
        if (!cateId.isEmpty()) {
            sql += " AND b.Category_id = " + cateId;
        }
        sql += " LIMIT ? OFFSET ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + search + "%");
        statement.setInt(2, ROWS_PER_PAGE);
        statement.setInt(3, offset);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), new Category(rs.getInt(12), rs.getString(13)));
            list.add(b);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}
