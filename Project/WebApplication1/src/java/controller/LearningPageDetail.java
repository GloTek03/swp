/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ClassDAL;
import dal.QuizDAL;
import dal.SubjectSettingDAL;
import dal.VideoDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import model.Account;
import model.Chapter;
import model.LessonIChapter;
import util.ValidInput;

/**
 *
 * @author acer
 */
public class LearningPageDetail extends HttpServlet {

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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) {
            response.sendRedirect("view/error.jsp");
        } else {
            HttpSession session = request.getSession();
            QuizDAL qd = new QuizDAL();
            Account a = (Account) session.getAttribute("account");
            System.out.println(a.getID());
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
            request.setAttribute("trainer", cd.getTrainerByClassID(cl));

            if (request.getParameter("lesson") == null || request.getParameter("lesson").equals("")) {
                request.setAttribute("lesson", 1);
                request.setAttribute("video", ssd.getLessonByID(1).getVideoLink());
                if (getVideoID(ssd.getLessonByID(1).getVideoLink()) != null) {
                    request.setAttribute("videoID", getVideoID(ssd.getLessonByID(1).getVideoLink()));
                    if (vd.getVideoStateByAccALss(a.getID(), 1) != null) {
                        request.setAttribute("state", vd.getVideoStateByAccALss(a.getID(), 1).getState());
                    } else {
                        request.setAttribute("state", 0);
                    }
                } else if (qd.getQuizByLessonID(1) != null) {
                    System.out.println("32");
                            
                    request.setAttribute("quiz", qd.getQuizByLessonID(1));
                    if (!qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(1).getQuizID()).isEmpty()) {
                        request.setAttribute("listQG", qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(1).getQuizID()));
                        double highest = 0;
                        for (int i = 0; i < qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(1).getQuizID()).size(); i++) {
                            if (highest < qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(1).getQuizID()).get(i).getGrade()) {
                                highest = qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(1).getQuizID()).get(i).getGrade();
                            }
                        }
                        request.setAttribute("highest", highest);
                    }
                }
                request.setAttribute("des", ssd.getLessonByID(1).getDescription());
                request.setAttribute("chapter", ssd.getChapterByID(1).getChapterID());
            } else {
                int lesson = 0;
                try {
                    lesson = Integer.parseInt(request.getParameter("lesson"));
                    if (ssd.getLessonByID(lesson) == null) {
                        throw new Exception("L");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    response.sendRedirect("view/error.jsp");
                }
                request.setAttribute("lesson", lesson);
                request.setAttribute("chapter", ssd.getLessonByID(lesson).getChapter().getChapterID());
                if (ssd.getLessonByID(lesson).getVideoLink() != null) {
                    request.setAttribute("video", ssd.getLessonByID(lesson).getVideoLink());
                    request.setAttribute("videoID", getVideoID(ssd.getLessonByID(lesson).getVideoLink()));
                    if (vd.getVideoStateByAccALss(a.getID(), lesson) != null) {
                        request.setAttribute("state", vd.getVideoStateByAccALss(a.getID(), lesson).getState());
                    } else {
                        request.setAttribute("state", 0);
                        request.setAttribute("des", ssd.getLessonByID(1).getDescription());
                    }
                } else if (qd.getQuizByLessonID(lesson) != null) {
                    request.setAttribute("quiz", qd.getQuizByLessonID(lesson));
                    if (!qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(lesson).getQuizID()).isEmpty()) {
                        request.setAttribute("listQG", qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(lesson).getQuizID()));
                        double highest = 0;
                        for (int i = 0; i < qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(lesson).getQuizID()).size(); i++) {
                            if (highest < qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(lesson).getQuizID()).get(i).getGrade()) {
                                highest = qd.getQuizGradeByAccAQuiz(a.getID(), qd.getQuizByLessonID(lesson).getQuizID()).get(i).getGrade();
                            }
                        }
                        request.setAttribute("highest", highest);
                    }
                }

            }
            request.getRequestDispatcher("view/LearningPageDetail.jsp").forward(request, response);
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
            for (int i = 0; i < text.length(); i++) {
                if (i + 5 < text.length()) {
                    if (text.substring(i, i + 5).equals("time=")) {
                        for (int j = 0; j < text.substring(i + 5).length(); j++) {
                            if (text.substring(i + 5).charAt(j) == '&') {
                                time_raw = text.substring(i + 5, i + 5 + j);
                                break;
                            }
                        }
                    }
                }
                if (i + 7 < text.length()) {
                    if (text.substring(i, i + 7).equals("lesson=")) {
                        for (int j = 0; j < text.substring(i + 7).length(); j++) {
                            if (text.substring(i + 5).charAt(j) == '&') {
                                lesson_raw = text.substring(i + 7);
                                break;
                            }
                        }
                    }
                }
                if (i + 7 < text.length()) {
                    if (text.substring(i, i + 9).equals("duration=")) {
                        for (int j = 0; j < text.substring(i + 9).length(); j++) {
                            duration_raw = text.substring(i + 9);
                            break;
                        }
                    }
                }
            }

            lesson = v.validInt(lesson_raw, request, response);
            time = v.validDouble(time_raw, request, response);
            duration = Math.floor(v.validDouble(duration_raw, request, response));
            System.out.println(lesson + " " + time);
            VideoDAL vd = new VideoDAL();
            if (vd.getVideoStateByAccALss(accID, lesson) == null) {
                if (duration - time <= 15) {
                    vd.add(accID, lesson, time, 0);
                } else {
                    vd.add(accID, lesson, time, 1);
                }
            } else {
                if (duration - time <= 15) {
                    vd.updateStateByAccALss(accID, lesson, time, 1);
                } else {
                    vd.updateStateByAccALss(accID, lesson, time, 0);
                }
            }
        }
    }

    protected String getVideoID(String videoLink) {
        String videoID = "";
        if (videoLink.contains("embed")) {
            for (int i = 0; i < videoLink.length(); i++) {
                if (i + 6 < videoLink.length()) {
                    if (videoLink.substring(i, i + 6).equals("embed/")) {

                        for (int j = 0; j < videoLink.substring(i + 6).length(); j++) {
                            if (videoLink.substring(i + 6).charAt(j) == '?') {
                                videoID = videoLink.substring(i + 6, i + 6 + j);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (videoLink.contains("watch")) {
        }
        return videoID;
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
