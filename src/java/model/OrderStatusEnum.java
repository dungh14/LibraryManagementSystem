/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DUNG HOANG PC
 */
public enum OrderStatusEnum {
    PENDING(1,"Received"),
    RECEIVED(2,"Returned"),;
    
    public final int code;
    public final String name;

    private OrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public static String getNameValueOf(int value) {
        for (OrderStatusEnum val : values()) {
            if(val.code == value) {
                return val.name;
            }
        }
        return "Status not exist!";
    }
}
