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
import javax.servlet.http.HttpSession;
import namdp.tbl_account.tbl_accountDAO;

/**
 *
 * @author DELL
 */
public class LoginServlet extends HttpServlet {

    private final String searchPage = "searchShoe.jsp";
    private final String invalidPage = "invalid.html";
    private final String customerPage = "customer.html";
    private final String adminPage = "admin.html";

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
        String url = invalidPage;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            tbl_accountDAO dao = new tbl_accountDAO();
            int role = dao.checkLogin(username, password);
            HttpSession session = request.getSession();
            if (role == 1) {
                url = searchPage;

                session.setAttribute("USERNAME", username);
            }
            if (role == 2) {
                url = customerPage;
                //HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
            }
            if (role == 3) {
                url = adminPage;
                // HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
            }


        } catch (NamingException ex) {
            log("LoginServlet_Naming Exception: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_Sql Exception: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
