/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_sizes;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class tbl_sizesDTO implements Serializable{
    private String id;
    private int sizes;
    private String country;
    private int quantity;
    private float price;

    public tbl_sizesDTO() {
    }

    public tbl_sizesDTO(String id, int sizes, String country, int quantity, float price) {
        this.id = id;
        this.sizes = sizes;
        this.country = country;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sizes
     */
    public int getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(int sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
