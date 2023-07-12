/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BookItem;
import model.Books;
import model.BorrowedBooks;
import model.Borrowing;
import model.Cart;
import model.OrderStatusEnum;
import model.User;

/**
 *
 * @author DUNG HOANG PC
 */
public class OrderDAO extends DBContext {

    public void insertOrder(User user, Cart cart) {
        try {
            String sql = "  insert into Borrowing ([User_ID],[Date_borrowed],[Due_date],[total], [status]) values (?,GETDATE(),DATEADD(MONTH, 3, GETDATE()),?,1) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, cart.getTotalBooks());
            statement.executeUpdate();

            String sql1 = "Select top 1 ID from Borrowing order by ID desc";
            statement = connection.prepareStatement(sql1);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (BookItem listItem : cart.getListItems()) {
                    String sql2 = "  insert into [BorrowedBooks] ([Borrowing_ID],[quantity],[Book_ID]) values (?,?,?)";
                    statement = connection.prepareStatement(sql2);
                    statement.setInt(1, oid);
                    statement.setInt(2, listItem.getQuantity());
                    statement.setInt(3, listItem.getBook().getId());
                    statement.executeUpdate();
                }
            }
            String sql3 = "Update [Books] set stock = stock - ? where Book_ID = ?";
            statement = connection.prepareStatement(sql3);
            for (BookItem listItem : cart.getListItems()) {
                statement.setInt(1, listItem.getQuantity());
                statement.setInt(2, listItem.getBook().getId());
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Borrowing> getAllOrderByUserId(int uid) {
        ArrayList<Borrowing> list = new ArrayList<>();
        try {
            String updateSql = "UPDATE Borrowing "
                    + "SET Borrowing.Book_ID = BorrowedBooks.Book_ID "
                    + "FROM Borrowing "
                    + "INNER JOIN BorrowedBooks "
                    + "ON Borrowing.ID = BorrowedBooks.Borrowing_ID";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.executeUpdate();
            String sq = "SELECT * FROM Borrowing WHERE User_ID = " + uid;
            PreparedStatement stm = connection.prepareStatement(sq);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Borrowing(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4),
                        rs.getInt(5), rs.getInt(6), OrderStatusEnum.getNameValueOf(rs.getInt(6)), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void returnBook(int quantity, int bookId, int borrowingId) {
        try {
            // Check if borrowingId is valid before updating database
            String sqlCheck = "SELECT * FROM Borrowing WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(sqlCheck);
            checkStatement.setInt(1, borrowingId);
            ResultSet rs = checkStatement.executeQuery();
            if (!rs.next()) {
                System.out.println("Borrowing ID not exist.");
                return;
            }
            // If borrowingId is valid, update the database
            String sql = "UPDATE [Books] SET stock = stock + ? WHERE Book_ID = ? AND Book_ID IN (SELECT Book_ID FROM Borrowing WHERE id = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, bookId);
            statement.setInt(3, borrowingId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Book not exist or have not been borrowed.");
            } else {
                String sql2 = "UPDATE Borrowing SET total = CASE WHEN total >= ? THEN total - ? ELSE total END, status = CASE WHEN total = ? THEN 2 ELSE status END WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, quantity);
                statement2.setInt(2, quantity);
                statement2.setInt(3, quantity);
                statement2.setInt(4, borrowingId);
                int rowsAffected = statement2.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Unable to find the Borrowed ID or the book have been returned.");
                } else {
                    System.out.println("Updated the number of books in store and total books in the corresponding Borrowing ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating the data: " + e.getMessage());
        }
    }

    public String checkReturnBook(int quantity, int bookId, int borrowingId) {
        try {
            // Check if borrowingId is valid before updating database
            String sqlCheck = "SELECT * FROM Borrowing WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(sqlCheck);
            checkStatement.setInt(1, borrowingId);
            ResultSet rs = checkStatement.executeQuery();
            if (!rs.next()) {
                return "Borrowing ID not exist!.";
            }
            // Check if bookId is valid and is being borrowed by borrowingId
            String sqlCheck2 = "SELECT * FROM Books WHERE Book_ID = ? AND Book_ID IN (SELECT Book_ID FROM Borrowing WHERE id = ?)";
            PreparedStatement checkStatement2 = connection.prepareStatement(sqlCheck2);
            checkStatement2.setInt(1, bookId);
            checkStatement2.setInt(2, borrowingId);
            ResultSet rs2 = checkStatement2.executeQuery();
            if (!rs2.next()) {
                return "Book not exist or have not been borrowed yet.";
            }
            // Check if quantity is valid (greater than 0 and less than or equal to total)
            int total = rs.getInt("total");
            if (quantity <= 0 || quantity > total) {
                return "Number of return book must larger than 0 anh not exceeding number of borrowed books.";
            }
            return "OK";
        } catch (SQLException e) {
            return "Error checking the data: " + e.getMessage();
        }
    }
}

