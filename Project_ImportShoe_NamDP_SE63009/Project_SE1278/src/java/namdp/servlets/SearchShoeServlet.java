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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import namdp.tbl_shoeBySize.tbl_shoeBySizeDAO;
import namdp.tbl_shoeBySize.tbl_shoeBySizeDTO;

/**
 *
 * @author DELL
 */
public class SearchShoeServlet extends HttpServlet {

    private final String searchShoeResultPage = "searchResult.jsp";

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
        String url = searchShoeResultPage;

        try {

            String description = request.getParameter("txtDescription");
            String sizeValue = request.getParameter("txtSize");

//            ErrorObj error = new ErrorObj();
//            boolean err = false;
                    
            ArrayList<tbl_shoeBySizeDTO> result = null;
            tbl_shoeBySizeDAO dao = new tbl_shoeBySizeDAO();

            if (description.isEmpty() && !sizeValue.isEmpty()) {
                
                int size = Integer.parseInt(sizeValue);
                dao.searchBySize(size);
                result = (ArrayList<tbl_shoeBySizeDTO>) dao.getListShoes();

            } else if (!description.isEmpty() && sizeValue.isEmpty()) {

                dao.searchByDescription(description);
                result = (ArrayList<tbl_shoeBySizeDTO>) dao.getListShoes();

            } else if (!description.isEmpty() && !sizeValue.isEmpty()) {

                int size = Integer.parseInt(sizeValue);
                dao.searchByDescriptionAndSize(description, size);
                result = (ArrayList<tbl_shoeBySizeDTO>) dao.getListShoes();
            }

            if (result != null) {

                request.setAttribute("SEARCH_RESULT", result);

            }

        }catch(NullPointerException ex){
            log("SearchShoeServlet_NullPointerException Exception: " + ex.getMessage());
        }
        catch (SQLException ex) {
            log("SearchShoeServlet_SQL Exception: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchShoeServlet_Naming Exception: " + ex.getMessage());
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
