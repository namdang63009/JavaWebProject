/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.errors;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ErrorObj implements Serializable{
    private String sizeFormatError;

    public String getSizeFormatError() {
        return sizeFormatError;
    }

    public void setSizeFormatError(String sizeFormatError) {
        this.sizeFormatError = sizeFormatError;
    }
    
}
