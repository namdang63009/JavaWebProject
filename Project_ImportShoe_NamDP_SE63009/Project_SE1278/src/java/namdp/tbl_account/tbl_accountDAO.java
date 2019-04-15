/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_account;

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
public class tbl_accountDAO implements Serializable{
    Connection conn;
    PreparedStatement stm;
    ResultSet rs;
    
    public void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(stm != null){
            stm.close();
        }
        if(conn != null){
            conn.close();
        }
    }
    
    public int checkLogin(String username, String password) 
            throws SQLException, NamingException{
        int result = -1;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT username, password,roles "
                        + "FROM tbl_account "
                        + "WHERE username = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    result = rs.getInt("roles");
                }
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
   
}
