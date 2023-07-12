/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Books;
import model.Category;

/**
 *
 * @author DUNG HOANG PC
 */
@WebServlet("")
public class BookList extends HttpServlet {

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
            out.println("<title>Servlet BookList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookList at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int indexPage = 1;
        String indexStr = request.getParameter("index");
        if (indexStr != null && !indexStr.isEmpty()) {
            indexPage = Integer.parseInt(indexStr);
        }
        String search = request.getParameter("search");
        String cateId = request.getParameter("cateId");
        if (search == null) {
            search = "";
        }
        if (cateId == null) {
            cateId = "";
        }
        BookDao dao = new BookDao();
        ArrayList<Books> blist = dao.getBookByFilter(search, cateId);
        int totalRecords = blist.size(); // lấy tổng số bản ghi
        int recordsPerPage = BookDao.ROWS_PER_PAGE; // lấy số bản ghi trên mỗi trang từ BookDao
        int numPages = (int) Math.ceil((double) totalRecords / recordsPerPage); // tính tổng số trang

        int startIndex = (indexPage - 1) * recordsPerPage;
        int endIndex = Math.min(startIndex + recordsPerPage, blist.size());
        blist = new ArrayList<>(blist.subList(startIndex, endIndex));
        ArrayList<Category> clist = dao.getAllCate();

        request.setAttribute("blist", blist);
        request.setAttribute("clist", clist);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("search", search);
        request.setAttribute("cateId", cateId);
        request.getRequestDispatcher("booklist.jsp").forward(request, response);
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
