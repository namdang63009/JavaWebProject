/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import namdp.tbl_shoeBySize.tbl_shoeBySizeDAO;
import namdp.tbl_shoeBySize.tbl_shoeBySizeDTO;
import namdp.tbl_sizes.tbl_sizesDAO;
import namdp.tbl_sizes.tbl_sizesDTO;

/**
 *
 * @author DELL
 */
public class SearchOutOfStockServlet extends HttpServlet {

    private final String productIsOutOfStockPage = "productIsOutOfStock.jsp";
    //private final String searchFaile = "errorSearch.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = productIsOutOfStockPage;
        try {
            tbl_sizesDAO sizeDao = new tbl_sizesDAO();
            List<tbl_sizesDTO> dto = sizeDao.getAllSize();
            request.setAttribute("LIST_SIZE", dto);

            tbl_shoeBySizeDAO dao = new tbl_shoeBySizeDAO();

            dao.searchOutOfStockShoe();

            ArrayList<tbl_shoeBySizeDTO> result = (ArrayList<tbl_shoeBySizeDTO>) dao.getListOutOfStock();
            if (result != null) {
                request.setAttribute("OUTOFSTOCK", result);

            }

        
        } catch (NamingException ex) {
            log("SearchOutOfStockServlet_Naming Exception: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchOutOfStockServlet_SQL Exception: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
