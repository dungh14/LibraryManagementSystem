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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    int id;
    String title;
    String author;
    String publisher;
    Date dateOfPulication;
    int categoryId;
    String img;
    String description;
    String country;
    String authorDesc;
    int stock;
    Category category;
}
