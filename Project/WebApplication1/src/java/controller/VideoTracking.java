/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.VideoDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.List;
import model.Account;
import util.ValidInput;

/**
 *
 * @author acer
 */
public class VideoTracking extends HttpServlet {

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
            out.println("<title>Servlet Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Test at " + request.getContextPath() + "</h1>");
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
    private List<String> list;

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
        BufferedReader reader = request.getReader();
        String text = "";
        while ((text = reader.readLine()) != null) {
            HttpSession session = request.getSession();
            ValidInput v = new ValidInput();
            Account a = (Account) session.getAttribute("account");
            int accID = a.getID();
            String time_raw = "", lesson_raw = "", duration_raw = "";
            int lesson = 0;
            double time = 0, duration = 0;
            System.out.println(text);
            for (int i = 0; i < text.length(); i++) {
                if (i + 5 < text.length()) {
                    if (text.substring(i, i + 5).equals("time=")) {
                        for (int j = i + 5; j < text.length(); j++) {
                            if (text.charAt(j) == '&' && time_raw.equals("")) {
                                time_raw = text.substring(i + 5, j);
                            }
                        }
                    }
                }
                if (i + 7 < text.length()) {
                    if (text.substring(i, i + 7).equals("lesson=")) {
                        for (int j = i + 7; j < text.length(); j++) {
                            if (text.charAt(j) == '&' && lesson_raw.equals("")) {
                                lesson_raw = text.substring(i + 7, j);
                            }
                        }
                    }
                }
                if (i + 9 < text.length()) {
                    if (text.substring(i, i + 9).equals("duration=")) {
                        for (int j = i + 9; j < text.length(); j++) {
                            duration_raw = text.substring(i + 9);
                            break;
                        }
                    }
                }
            }
            lesson = v.validInt(lesson_raw, request, response);
            time = v.validDouble(time_raw, request, response);
            duration = Math.floor(v.validDouble(duration_raw, request, response));
            System.out.println(lesson + " " + time + " " + duration);
            VideoDAL vd = new VideoDAL();
            if (vd.getVideoStateByAccALss(accID, lesson) == null) {
                if (duration - time <= 20) {
                    vd.add(accID, lesson, time, 1);
                } else {
                    vd.add(accID, lesson, time, 0);
                }
            } else {
                if (vd.getVideoStateByAccALss(accID, lesson).getStatus() != 1) {
                    if (duration - time <= 20) {
                        vd.updateStateByAccALss(accID, lesson, time, 1);
                    } else {
                        vd.updateStateByAccALss(accID, lesson, time, 0);
                    }
                }
                else {
                    if (duration - time <= 20) {
                        vd.updateStateByAccALss(accID, lesson, time, 1);
                    } else {
                        vd.updateStateByAccALss(accID, lesson, time, 1);
                    }
                }
            }
        }
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
