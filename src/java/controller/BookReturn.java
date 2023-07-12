/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Books;
import model.Cart;
import model.User;

/**
 *
 * @author DUNG HOANG PC
 */
public class BookReturn extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookReturn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookReturn at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
        } else {
            User u = (User) session.getAttribute("user");
            request.getRequestDispatcher("bookreturn.jsp").forward(request, response);
        }
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quantity = request.getParameter("quantity");
        String bookId = request.getParameter("-book_id"); // updated parameter name
        String borrowingId = request.getParameter("borrowing_id"); // added parameter name
        String mess = "";
        if (quantity != null && bookId != null && borrowingId != null) {
            try {
                int q = Integer.parseInt(quantity);
                int bId = Integer.parseInt(bookId);
                int brId = Integer.parseInt(borrowingId);
                OrderDAO dao = new OrderDAO();
                String check = dao.checkReturnBook(q, bId, brId);
                if (check.equals("OK")) {
                    dao.returnBook(q, bId, brId);
                    mess = "Return successful";
                } else {
                    mess = check;
                }
            } catch (NumberFormatException e) {
                mess = "Invalid input";
            }
        } else {
            mess = "Missing parameter(s)";
        }
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("bookreturn.jsp").forward(request, response);
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
