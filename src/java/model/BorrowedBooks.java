/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author DUNG HOANG PC
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBooks {
    int id;
    int borrowingId;
    int quantity;
    int bookId;
    ArrayList<BorrowedBooks> bo;

    public BorrowedBooks(ArrayList<BorrowedBooks> bo) {
        this.bo = bo;
    }
    
    
}
