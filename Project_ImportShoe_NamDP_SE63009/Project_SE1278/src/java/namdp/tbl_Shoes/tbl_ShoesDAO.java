/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_Shoes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import namdp.utils.DBUtils;

/**
 *
 * @author DELL
 */
public class tbl_ShoesDAO implements Serializable {

    Connection conn;
    PreparedStatement stm;
    ResultSet rs;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean updateDisableShoe(String shoeID)
            throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tbl_shoes SET disable = 'true' where shoesID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoeID);
                result = stm.executeUpdate() > 0;
                
            }

        } finally {
            closeConnection();
        }
        return result;
    }
}
//public String getQuantity(String shoeID, String sizeID)
//            throws NamingException, SQLException {
//        String result = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = "SELECT quantityOfShoe, quantity\n"
//                        + "FROM tbl_shoes, tbl_sizes\n"
//                        + "Where shoesID = ? and id = ?";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, shoeID);
//                stm.setString(2, sizeID);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    result = rs.getString("quantityOfShoe") + "-" + rs.getString("quantity");
//                }
//            }
//        } finally {
//            closeConnection();
//        }
//        return result;
//    }
