/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ClassDAL;
import dal.SubjectSettingDAL;
import dal.VideoDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Account;
import model.Chapter;
import model.LessonIChapter;
import util.ValidInput;

/**
 *
 * @author acer
 */
public class LearningPage extends HttpServlet {

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
            out.println("<title>Servlet LearningPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LearningPage at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) {
            response.sendRedirect("view/error.jsp");
        } else {
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            VideoDAL vd = new VideoDAL();
            ValidInput v = new ValidInput();
            SubjectSettingDAL ssd = new SubjectSettingDAL();
            ClassDAL cd = new ClassDAL();

            int subject = v.validInt(request.getParameter("subject"), request, response);
            int cl = v.validInt(request.getParameter("class"), request, response);

            List<Chapter> listC = ssd.getAllChapterFromClassAndSubject(subject);
            List<LessonIChapter> listLIC = new ArrayList<>();
            for (int i = 0; i < listC.size(); i++) {      
                if(listC.get(i).getNumAchieve() == ssd.getAllLessonByChapter(listC.get(i).getChapterID()).size()) {
                    listC.get(i).setAchieve(true);
                }
                else {
                    listC.get(i).setAchieve(false);
                }
                listLIC.add(new LessonIChapter(listC.get(i), ssd.getAllLessonByChapter(listC.get(i).getChapterID())));
            }
            request.setAttribute("list", listLIC);
            request.setAttribute("subject", subject);
            request.setAttribute("cl", cl);
            Date now = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());

            List<model.Class> listCL = cd.getAllClassThatTraineeIn(a.getID(), now);
      
            request.setAttribute("listCL", listCL);
            request.getRequestDispatcher("view/LearningPage.jsp").forward(request, response);
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
