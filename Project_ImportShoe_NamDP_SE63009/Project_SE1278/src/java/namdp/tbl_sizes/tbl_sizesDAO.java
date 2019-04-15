/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_sizes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import namdp.utils.DBUtils;

/**
 *
 * @author DELL
 */
public class tbl_sizesDAO implements Serializable {

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

  

    public List<tbl_sizesDTO> getAllSize() throws NamingException, SQLException {
        List<tbl_sizesDTO> result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select sizes, id From tbl_sizes";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while(rs.next()){
                    tbl_sizesDTO dto = new tbl_sizesDTO();
                    dto.setSizes(rs.getInt("sizes"));
                    dto.setId(rs.getString("id"));
                    
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
