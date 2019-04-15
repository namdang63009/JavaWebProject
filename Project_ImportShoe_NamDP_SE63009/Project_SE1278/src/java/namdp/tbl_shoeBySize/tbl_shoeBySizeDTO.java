/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_shoeBySize;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class tbl_shoeBySizeDTO implements Serializable{
    private String shoeID;
    private String sizesID;
    private int sizes;
    private float price;
    private String description;
    private int quantityOfShoe;
    private boolean disable;

    public tbl_shoeBySizeDTO() {
    }

    public tbl_shoeBySizeDTO(int sizes, String description, int quantityOfShoe) {
        this.sizes = sizes;
        this.description = description;
        this.quantityOfShoe = quantityOfShoe;
    }

    public tbl_shoeBySizeDTO(int sizes, float price, String description, int quantityOfShoe, String shoeID, String sizeID) {
        this.sizes = sizes;
        this.price = price;
        this.description = description;
        this.quantityOfShoe = quantityOfShoe;
        this.shoeID = shoeID;
        this.sizesID = sizeID;
    }
    

    /**
     * @return the shoeID
     */
    public String getShoeID() {
        return shoeID;
    }

    /**
     * @param shoeID the shoeID to set
     */
    public void setShoeID(String shoeID) {
        this.shoeID = shoeID;
    }

    /**
     * @return the sizesID
     */
    public String getSizesID() {
        return sizesID;
    }

    /**
     * @param sizesID the sizesID to set
     */
    public void setSizesID(String sizesID) {
        this.sizesID = sizesID;
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantityOfShoe(int quantityOfShoe) {
        this.quantityOfShoe = quantityOfShoe;
    }

    public int getQuantityOfShoe() {
        return quantityOfShoe;
    }

    /**
     * @return the disable
     */
    public boolean isDisable() {
        return disable;
    }

    /**
     * @param disable the disable to set
     */
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

   

    
    
}
