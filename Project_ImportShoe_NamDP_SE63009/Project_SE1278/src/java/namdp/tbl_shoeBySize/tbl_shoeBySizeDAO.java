/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.tbl_shoeBySize;

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
public class tbl_shoeBySizeDAO implements Serializable {

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
    private List<tbl_shoeBySizeDTO> listShoes;

    public List<tbl_shoeBySizeDTO> getListShoes() {
        return listShoes;
    }

    //Search By both Descrition and Size
    public void searchByDescriptionAndSize(String description, int sizes)
            throws SQLException, NamingException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT shoesID, description,quantityOfShoe, sizes, disable "
                        + "FROM tbl_shoes, tbl_sizes "
                        + "WHERE (description LIKE ? AND sizes LIKE ?) "
                        + "AND id IN(SELECT sizeID FROM tbl_shoeBySize)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + description + "%");
                stm.setInt(2, sizes);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int quantity = rs.getInt("quantityOfShoe");
                    String des = rs.getString("description");
                    int size = rs.getInt("sizes");
                    boolean disable = rs.getBoolean("disable");

                    tbl_shoeBySizeDTO dto = new tbl_shoeBySizeDTO();
                    dto.setShoeID(rs.getString("shoesID"));
                    dto.setDescription(des);
                    dto.setQuantityOfShoe(quantity);
                    dto.setSizes(size);
                    dto.setDisable(disable);

                    if (this.listShoes == null) {
                        this.listShoes = new ArrayList<>();
                    }
                    this.listShoes.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    //Search By only Description
    public void searchByDescription(String description)
            throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT description, quantityOfShoe, sizes, disable "
                        + "FROM tbl_shoes, tbl_sizes "
                        + "WHERE (description LIKE ?) "
                        + "AND id IN (SELECT sizeID FROM tbl_shoeBySize)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + description + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    int quantity = rs.getInt("quantityOfShoe");
                    String des = rs.getString("description");
                    int size = rs.getInt("sizes");
                    boolean disable = rs.getBoolean("disable");

                    tbl_shoeBySizeDTO dto = new tbl_shoeBySizeDTO();
                    dto.setDescription(des);
                    dto.setQuantityOfShoe(quantity);
                    dto.setSizes(size);
                    dto.setDisable(disable);

                    if (this.listShoes == null) {
                        this.listShoes = new ArrayList<>();
                    }
                    this.listShoes.add(dto);
                }
            }

        } finally {
            closeConnection();
        }
    }

    public void searchBySize(int size)
            throws NamingException, SQLException {
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select description, quantityOfShoe, sizes, disable\n"
                        + "From tbl_shoes, tbl_sizes\n"
                        + "where (sizes = ?)\n"
                        + "and id in (Select sizeID from tbl_shoeBySize)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, size);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int quan = rs.getInt("quantityOfShoe");
                    String des = rs.getString("description");
                    int sizess = rs.getInt("sizes");
                    boolean disable = rs.getBoolean("disable");
                    
                    tbl_shoeBySizeDTO dto = new tbl_shoeBySizeDTO();
                    dto.setDescription(des);
                    dto.setQuantityOfShoe(quan);
                    dto.setSizes(sizess);
                    dto.setDisable(disable);
                    
                    if (this.listShoes == null) {
                        this.listShoes = new ArrayList<>();
                    }
                    
                    this.listShoes.add(dto);
                }
            }

        } finally {
            closeConnection();
        }
    }

    public List<tbl_shoeBySizeDTO> listOutOfStock;

    public List<tbl_shoeBySizeDTO> getListOutOfStock() {
        return listOutOfStock;
    }

    public void searchOutOfStockShoe()
            throws SQLException, NamingException {

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT shoesID, id, description, sbz.price, sizes, quantityShoeBySize "
                        + "FROM tbl_shoes "
                        + "JOIN tbl_shoeBySize as sbz "
                        + "ON shoesID = shoeID "
                        + "JOIN tbl_sizes "
                        + "ON id = sizeID WHERE quantityShoeBySize = 0";
                stm = conn.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String shoeID = rs.getString("shoesID");
                    String des = rs.getString("description");
                    int quan = rs.getInt("quantityShoeBySize");
                    float price = rs.getFloat("price");
                    int size = rs.getInt("sizes");
                    String sizeID = rs.getString("id");
                    tbl_shoeBySizeDTO dto = new tbl_shoeBySizeDTO(size, price,
                            des, quan, shoeID, sizeID);
                    if (this.listOutOfStock == null) {
                        this.listOutOfStock = new ArrayList<>();
                    }
                    this.listOutOfStock.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

    }

    public boolean importShoe(String shoeID, String sizeID, int quantityShoeBySize,
            float price, int quanOfSize, int quanOfShoe)
            throws SQLException, NamingException {
        boolean result = false;
        String sql = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                sql = "UPDATE tbl_shoeBySize SET quantityShoeBySize = ?, price = ? "
                        + "WHERE shoeID = ? AND sizeID = ? "
                        + "UPDATE tbl_shoes SET quantityOfShoe = ? WHERE shoesID = ?\n"
                        + "\n"
                        + "UPDATE tbl_sizes SET quantity = ? WHERE id=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantityShoeBySize);
                stm.setFloat(2, price);
                stm.setString(3, shoeID);
                stm.setString(4, sizeID);
                stm.setInt(5, quanOfShoe);
                stm.setString(6, shoeID);
                stm.setInt(7, quanOfSize);
                stm.setString(8, sizeID);
                result = stm.executeUpdate() > 0;

            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertShoe(String shoeID, String sizeID, int quantityShoeBySize, float price)
            throws SQLException, NamingException {
        boolean result = false;
        String sql = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                sql = "INSERT INTO tbl_shoeBySize (shoeID, sizeID, quantityShoeBySize, price) "
                        + "VALUES (?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoeID);
                stm.setString(2, sizeID);
                stm.setInt(3, quantityShoeBySize);
                stm.setFloat(4, price);
                result = stm.executeUpdate() > 0;

            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public String getQuantity(String shoeID, String sizeID)
            throws NamingException, SQLException {
        String result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT quantityOfShoe, quantity\n"
                        + "FROM tbl_shoes, tbl_sizes\n"
                        + "Where shoesID = ? and id = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoeID);
                stm.setString(2, sizeID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("quantityOfShoe") + "-" + rs.getString("quantity");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
