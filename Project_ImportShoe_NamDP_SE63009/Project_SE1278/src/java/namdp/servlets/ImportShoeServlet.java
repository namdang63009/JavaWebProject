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
import namdp.tbl_shoeBySize.tbl_shoeBySizeDAO;

/**
 *
 * @author DELL
 */
public class ImportShoeServlet extends HttpServlet {

    private final String importErrorPage = "importError.html";

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
        String urlReWriting = importErrorPage;
        try {
            String selectedSize = request.getParameter("cboSize");
            String currentSizeId = request.getParameter("currentSizeID");
            String shoeID = request.getParameter("txtShoeID");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            
            //Get quantityOfShoe and quantitySizeOfShoe
            tbl_shoeBySizeDAO dao = new tbl_shoeBySizeDAO();

            String sumOfQuantity = dao.getQuantity(shoeID, selectedSize);
            int quantityOfShoe = Integer.parseInt(sumOfQuantity.substring(sumOfQuantity.lastIndexOf("-") + 1, sumOfQuantity.length()));
            int quantityOfSize = Integer.parseInt(sumOfQuantity.substring(0, sumOfQuantity.lastIndexOf("-")));

            boolean result = false;
            if (currentSizeId.equals(selectedSize)) {
                 //Update Shoe if selected size = outofstockShoe's size 
                 //Replace by new shoe with new quantity, new price, new size
                 //After update, quantiyOfShoe in tbl_shoes and quantiyOfSize in tbl_sizes will be update too.  

                result = dao.importShoe(shoeID, selectedSize, quantity, price, quantityOfSize + quantity, quantityOfShoe + quantity);
                if (result) {
                    urlReWriting = "searchOutOfStock?";
                           
                }
            } else {
                //Insert Shoe selected size different outofstockShoe's size 
                result = dao.insertShoe(shoeID, selectedSize, quantity, price);
                if(result){
                     urlReWriting = "searchOutOfStock?";
                }
            }
        } catch (NumberFormatException ex) {
            log("ImportShoeServlet_NumberFormatException Exception: " + ex.getMessage()); 
        }catch (NamingException ex) {

            log("ImportShoeServlet_Naming Ex: " + ex.getMessage());
        } catch (SQLException ex) {

            log("ImportShoeServlet_SQL Ex: " + ex.getMessage());
        } finally {
            response.sendRedirect(urlReWriting);
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
