/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountinclassDAO;
import dal.AccountnotclassDAO;
import dal.ClassDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Accountnotclass;

/**
 *
 * @author quany
 */
public class AddTrainee extends HttpServlet {

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
            out.println("<title>Servlet AddTrainee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTrainee at " + request.getContextPath() + "</h1>");
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
        AccountnotclassDAO accountnotclassDAO = new AccountnotclassDAO();
        ClassDAL cd = new ClassDAL();

        String class_raw = request.getParameter("class");
        String subject = request.getParameter("subject");

        int classSJ = 0;
        try {
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (cd.getClassByIDASjASem(classSJ, subject) == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            ArrayList<Accountnotclass> list = accountnotclassDAO.getAl(classSJ, subject);
            request.setAttribute("classSJ", classSJ);
            request.setAttribute("subject", subject);
            request.setAttribute("list", list);
            request.getRequestDispatcher("view/TraineeAdd.jsp").forward(request, response);
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
        AccountnotclassDAO accountnotclassDAO = new AccountnotclassDAO();
        ClassDAL cd = new ClassDAL();
        
        String class_raw = request.getParameter("classSJ");
        String subject = request.getParameter("subject");
        
        int classSJ = 0;
        try {
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        String[] username = request.getParameterValues("username");
  
        String isAdd[] = request.getParameterValues("isAdd");

        int semester = cd.getClassByIDASjASem(classSJ, subject).getSemester().getSetting_id();
        

        ArrayList<model.Class> listClasses = accountnotclassDAO.getClassBySubjectCode(subject);

        LocalDate startdate = LocalDate.of(2023, 1, 1);

        LocalDate enddate = LocalDate.of(2023, 12, 31);

        LocalTime starttime = LocalTime.of(0, 0, 0);

        LocalTime endtime = LocalTime.of(23, 59, 59);

        accountnotclassDAO.add(username[0], classSJ, semester, subject, startdate, enddate, starttime, endtime);

        response.sendRedirect("TraineeList?class=" + classSJ + "&subject=" + subject);
        //processRequest(request, response);
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
