/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectDAL;
import dal.SubjectSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Paging;

/**
 *
 * @author acer
 */
public class SubjectSettingManagement extends HttpServlet {

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
            out.println("<title>Servlet SubjectSettingManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectSettingManagement at " + request.getContextPath() + "</h1>");
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
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("act") != null && !request.getParameter("act").equals("")) {
            if (request.getParameter("act").equals("act")) {
                String id_raw = request.getParameter("id");
                int id = 0;
                try {
                    id = Integer.parseInt(id_raw);
                } catch (Exception e) {
                    System.out.println(e);
                }
                activate(id);
                if (ssd.getAllSettingBySetting(id) != null) {
                    getSubjectDetail(request, response, ssd.getAllSettingBySetting(id).getSubjectID());
                }
                else {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
            } else if (request.getParameter("act").equals("deact")) {
                String id_raw = request.getParameter("id");
                int id = 0;
                try {
                    id = Integer.parseInt(id_raw);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println(id);
                deactivate(id);
                if (ssd.getAllSettingBySetting(id) != null) {
                    getSubjectDetail(request, response, ssd.getAllSettingBySetting(id).getSubjectID());
                }
                else {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }

    void activate(int id) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        ssd.activateSST(id);
    }

    void deactivate(int id) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        ssd.deactivateSST(id);
    }

    void getSubjectDetail(HttpServletRequest request, HttpServletResponse response, String id)
            throws ServletException, IOException {
        Paging p = new Paging();
        SubjectDAL sd = new SubjectDAL();

        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (sd.getSubjectByCode(id) == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            int numPerPage = 5;
            String page_raw = request.getParameter("page");
            int page = 1;
            if (page_raw != null) {
                try {
                    page = Integer.parseInt(page_raw);
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
            }

            request.setAttribute("s", sd.getSubjectByCode(id));
            if (ssd.getAllSettingBySubject(id) != null) {
                int start = p.getStart(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                int end = p.getEnd(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                int totalPage = p.getTotalPage(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                request.setAttribute("list", ssd.getListByPage(start, end, ssd.getAllSettingBySubject(id)));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("totalEntity", ssd.getAllSettingBySubject(id).size());
            } else {
                request.setAttribute("msg", "Không có setting nào của môn học này");
            }
            request.setAttribute("id", id);
            request.getRequestDispatcher("view/subjectDetail.jsp").forward(request, response);
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
