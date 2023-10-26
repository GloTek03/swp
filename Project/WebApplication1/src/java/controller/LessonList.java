/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectSettingDAL;
import dal.SystemSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Lesson;
import util.Paging;
import util.ValidInput;

/**
 *
 * @author acer
 */
public class LessonList extends HttpServlet {

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
            out.println("<title>Servlet LessonList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessonList at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "list": {
                    listLessonGet(request, response);
                    break;
                }
                case "add": {
                    addLesson(request, response);
                    break;
                }
                case "upd": {
                    updLesson(request, response);
                    break;
                }
                case "act": {
                    activateDeactivateLesson(request, response);
                    break;
                }
            }
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

    private void listLessonGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = sort(request, response);
        String sql = "";
        String moreC = searchByChapter(request, response);
        String moreS = searchByStatus(request, response);
        String moreT = searchByType(request, response);
        String moreN = searchByNameOrID(request, response);
        sql += "WHERE ";
        sql += moreC;
        sql += "AND";
        sql += moreS;
        sql += "AND";
        sql += moreT;
        sql += "AND";
        sql += moreN;
        Paging p = new Paging();
        int numPerPage = 5;
        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw == null || page_raw.equals("")) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(page_raw);
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private String sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String o = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("sort") == null || request.getParameter("sort").equals("")
                || request.getParameter("order") == null || request.getParameter("order").equals("")) {
            o = "ORDER BY display_order, lesson_id";
        } else {
            if (!request.getParameter("sort").equals("ID") && !request.getParameter("sort").equals("Name")
                    && !request.getParameter("sort").equals("Chapter") && !request.getParameter("sort").equals("Type")
                    && !request.getParameter("sort").equals("Order") && !request.getParameter("sort").equals("status")) {
                o = "ORDER BY display_order, lesson_id";
            } else {
                int order = 0;
                try {
                    order = Integer.parseInt(request.getParameter("order"));
                    if (order != 0 && order != 1) {
                        throw new Exception("L");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
                if (order == 0) {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 0);
                    o = ssd.getSortedLessonDesc(request.getParameter("sort"));
                } else {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 1);
                    o = ssd.getSortedLessonAsc(request.getParameter("sort"));
                }
            }
        }
        return o;
    }

    public List<Lesson> pagination(List<Lesson> list, int page, int numPerPage) {
        Paging p = new Paging();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        int start = p.getStart(page, list.size(), numPerPage);
        int end = p.getEnd(page, list.size(), numPerPage);
        list = ssd.getLessonByPage(start, end, list);
        return list;
    }

    private String searchByChapter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("chapter");
        if (request.getParameter("chapter") == null
                || request.getParameter("chapter").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("c", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " chapter_id = " + id + " ";
            }
        }
        return more;
    }

    private String searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        if (request.getParameter("status") == null
                || request.getParameter("status").equals("")) {
            request.setAttribute("s", -1);
            more = " 1 = 1 ";
        } else {
            int status = 0;
            try {
                status = Integer.parseInt(request.getParameter("status"));
                if (status != 1 && status != 0 && status != -1) {
                    throw new Exception("L");
                }
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("s", status);
            if (status == -1) {
                more = " 1 = 1 ";
            } else {
                more = " status = " + status + " ";
            }
        }
        return more;
    }

    private String searchByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("type");
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("t", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " lesson_type = " + id + " ";
            }
        }
        return more;
    }

    private String searchByNameOrID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = "", more = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("search") == null
                || request.getParameter("search").equals("")) {
            request.setAttribute("search", key);
            more = " 1 = 1 ";
        } else {
            key = request.getParameter("search");
            request.setAttribute("search", key);
            more = " lesson_name LIKE '%" + key + "%' ";
        }
        return more;
    }

    private void addLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("add", 1);
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private void updLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("upd", 1);
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private void activateDeactivateLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidInput v = new ValidInput();
        String id_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int id = v.validInt(id_raw, request, response);
        int page = v.validInt(page_raw, request, response);
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (ssd.getLessonByID(id) != null) {
            if (ssd.getLessonByID(id).getStatus() == 1) {
                ssd.actLesson(0, id);
            } else {
                ssd.actLesson(1, id);
            }
            int numPerPage = 5;
            listLessonPage(page, request, response);
        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }

    }

    private void listLessonPage(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = sort(request, response);
        String sql = "";
        String moreC = searchByChapter(request, response);
        String moreS = searchByStatus(request, response);
        String moreT = searchByType(request, response);
        String moreN = searchByNameOrID(request, response);
        sql += "WHERE ";
        sql += moreC;
        sql += "AND";
        sql += moreS;
        sql += "AND";
        sql += moreT;
        sql += "AND";
        sql += moreN;
        Paging p = new Paging();
        int numPerPage = 5;

        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

}
