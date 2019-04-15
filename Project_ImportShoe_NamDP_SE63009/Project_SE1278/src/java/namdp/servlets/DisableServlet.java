/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import namdp.tbl_Shoes.tbl_ShoesDAO;

/**
 *
 * @author DELL
 */
public class DisableServlet extends HttpServlet {

    private final String updateDisableError = "updateDisableError.html";

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

        String description = request.getParameter("desValue");
        String size = request.getParameter("sizeValue");
        String urlRewriting = "searchShoe?txtDescription=" + description + "&txtSize=" + size;
        boolean result = false;
        try {
            String[] checkedDisable = request.getParameterValues("chkDisable");

            //boolean disableItem = checkedDisable.
            tbl_ShoesDAO dao = new tbl_ShoesDAO();

            if (checkedDisable.length > 0) {
                for (String shoeID : checkedDisable) {

                    result = dao.updateDisableShoe(shoeID);
                }

//                if (!description.isEmpty() && !size.isEmpty()) {
//                    urlRewriting = "searchShoe?txtDescription=" + description + "&txtSize=" + size;
//                } else if (!description.isEmpty() && size.isEmpty()) {
//                    urlRewriting = "searchShoe?txtDescription=" + description;
//                } else if (description.isEmpty() && !size.isEmpty()) {
//                    urlRewriting = "searchShoe?&txtSize=" + size;
//                } else {
//                    urlRewriting = updateDisableError;
//                    return;
//                }
                if (result) {
                    urlRewriting = "searchShoe?txtDescription=" + description + "&txtSize=" + size;
                } else {
                    urlRewriting = updateDisableError;
                    return;
                }
            }

        }catch(NullPointerException ex){
             log("DisableServlet_NullPointerException Ex: " + ex.getMessage());
        } 
        catch (NamingException ex) {
            log("DisableServlet_Naming Ex: " + ex.getMessage());
        } catch (SQLException ex) {
            log("DisableServlet_SQL Ex: " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
