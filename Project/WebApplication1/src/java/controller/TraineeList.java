/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountinclassDAO;
import dal.ClassDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Accountinclass;
import util.Paging;

/**
 *
 * @author quany
 */
public class TraineeList extends HttpServlet {

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
            out.println("<title>Servlet TraineeList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraineeList at " + request.getContextPath() + "</h1>");
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
        Paging p = new Paging();
        ClassDAL cd = new ClassDAL();
        AccountinclassDAO accountinclassDAO = new AccountinclassDAO();
        ArrayList<Accountinclass> list = null;

        String class_raw = request.getParameter("class");
        String subject = request.getParameter("subject");

        int classSJ = 0;

        try {
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {
            System.out.println(e);
        }

        int page = 1;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        model.Class c = cd.getClassByIDASjASem(classSJ, subject);
        if (c != null) {
            list = accountinclassDAO.getAll(classSJ, subject);
            if (!list.isEmpty()) {
                int numPerPage = 5;
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = p.getTotalPage(page, list.size(), numPerPage);
                
                request.setAttribute("page", page);
                request.setAttribute("list", accountinclassDAO.getListByPage(start, end, list));
                request.setAttribute("totalPage", totalPage);

            } else {
                request.setAttribute("ms", "Chưa có học viên nào của lớp này");
            }
            request.setAttribute("c", c);
            request.getRequestDispatcher("view/TraineeList.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
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
