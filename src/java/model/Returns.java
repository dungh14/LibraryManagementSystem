/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author DUNG HOANG PC
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Returns {
    int borrowId;
    int userId;
    int bookId;
    Date returnDate;
    Date dueDate;
    int fine;
}
