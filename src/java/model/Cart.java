/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Iterator;
import java.util.List;
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
public class Cart {
    private int totalBooks;
    private List<BookItem> listItems;

    public Cart(List<BookItem> listItems) {
        this.listItems = listItems;
    }
    public int getTotalBooks() {
        int t = 0;
        for (BookItem item : listItems) {
            t += (item.getQuantity());
        }
        return t;
    }
    public int getBookID() {
        Books b = new Books();
        int c = b.getId();
        return c;
    }
    public BookItem getItemById(int id) {
        for (BookItem listItem : listItems) {
            if(listItem.getBook().getId() == id) {
                return listItem;
            }
        }
        return null;
    }
    public boolean checkExist(int id) {
        for (BookItem listItem : listItems) {
            if(listItem.getBook().getId() == id) {
                return true;
            }
        }
        return false;
    }
    public void addBooks(BookItem bookItem) {
        if(checkExist(bookItem.getBook().getId())) {
            BookItem oldItem = getItemById(bookItem.getBook().getId());
            oldItem.setQuantity(oldItem.getQuantity() + bookItem.getQuantity());
        } else {
            listItems.add(bookItem);
        }
    }
    public void removeItem(int productId) {
    Iterator<BookItem> iterator = listItems.iterator();
    while (iterator.hasNext()) {
        BookItem item = iterator.next();
        if (item.getBook().getId() == productId) {
            totalBooks -= item.getQuantity();
            iterator.remove();
            break;
        }
    }
}
}
