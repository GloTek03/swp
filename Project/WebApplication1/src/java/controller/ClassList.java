/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import dal.ClassDAL;
import dal.MajorDAL;
import dal.SubjectDAL;
import dal.SystemSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Major;
import model.Subject;
import util.Paging;

/**
 *
 * @author acer
 */
public class ClassList extends HttpServlet {

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
            out.println("<title>Servlet ClassList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassList at " + request.getContextPath() + "</h1>");
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
        if(request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        else {
            switch(request.getParameter("act")) {
                case "list":
                {
                    listClassGet(request, response);
                    break;
                }
                case "add":
                {
                    adudClass(request, response);
                    break;
                }
                case "upd":
                {
                    adudClass(request, response);
                    break;
                }
                case "act":
                {
                    activateDeactivate(request, response);
                    break;
                }
            }
        }
    }
    
    void activateDeactivate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        String sj = request.getParameter("sj");
        
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);            
        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        
        ClassDAL cd = new ClassDAL();
        if(cd.getClassByIDASjASem(id, sj).getActivate() == 1) {
            cd.deactivateClass(id, sj);
        }
        else {
            cd.activateClass(id, sj);
        }
        response.sendRedirect("clist?act=list");
    }
    
    void adudClassPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        MajorDAL md = new MajorDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        SubjectDAL sd = new SubjectDAL();

        List<Major> listM = md.getAllMajor();

        List<String> listStat = new ArrayList<>();
        listStat.add("Active");
        listStat.add("Inactive");
        System.out.println("da");
        List<String> listSem = ssd.getAllSemester();

        request.setAttribute("listSj", sd.getAllSubject());
        request.setAttribute("listM", listM);
        request.setAttribute("listStat", listStat);
        request.setAttribute("listSem", listSem);
        request.setAttribute("adud", 1);
        if (request.getParameter("add") != null && request.getParameter("add").equals("1")) {
            String id_raw = request.getParameter("id");
            String name = request.getParameter("name");
            String mj = request.getParameter("mj");
            String sj = request.getParameter("sj");
            String sem = request.getParameter("sem");

            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            if (cd.getClassByIDASjASem(id, sj) != null) {
                request.setAttribute("ms", "Đã tồn tại 1 lớp với ID = " + id);
                request.getRequestDispatcher("view/adudClass.jsp").forward(request, response);
            } else if (cd.getClassByID(id) != null && ((!cd.getClassByID(id).getClassName().equals(name)) || (!cd.getClassByID(id).getMajorID().equals(mj)))) {
                request.setAttribute("ms", "Thông tin của lớp với ID = " + id + " vừa được thêm vào khác so với các bản ghi khác. Vui lòng kiểm tra lại");
                request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
            } else {
                cd.insClass(id, name, mj, sem, sj);
                model.Class c = cd.getClassByIDASjASem(id, sj);
                request.setAttribute("c", c);
                request.setAttribute("ms", "Thêm thành công");
                request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
            }
        } else if (request.getParameter("upd") != null && request.getParameter("upd").equals("1")) {
            String oldSem = request.getParameter("oldSem");
            String oldSj = request.getParameter("oldSj");
            String id_raw = request.getParameter("id");
            String className = request.getParameter("name");
            String mj = request.getParameter("mj");
            String sj = request.getParameter("sj");
            String sem = request.getParameter("sem");
            String active_raw = request.getParameter("active");
            int active = 0;
            if(active_raw.equals("Active")) {
                active = 1;
            }
            else if(active_raw.equals("Inactive")) {
                active = 0;
            }
            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                System.out.println(e);
            }
            model.Class c = cd.getClassByIDASjASem(id, sj);
            model.Class oldC = cd.getClassByIDASjASem(id, oldSj);
            if (c != null && c.getS().getSubjectID() != oldC.getS().getSubjectID()) {
                String ms = "Lớp học với ID = " + id + " đã/đang học môn với ID = " + sj + " vào kỳ " + cd.getClassByIDASjASem(id, sj).getSemester();
                c = cd.getClassByIDASjASem(id, sj);
                request.setAttribute("c", c);
                request.setAttribute("ms", ms);
                request.setAttribute("upd", 1);
                request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
            } else {
                cd.updClass(id, className, mj, oldSj, oldSem, active, sj, sem);
                c = cd.getClassByIDASjASem(id, sj);
                request.setAttribute("c", c);
                request.setAttribute("ms", "Sửa thành công");
                request.setAttribute("upd", 1);
                request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }
    
    void adudClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MajorDAL md = new MajorDAL();
        ClassDAL cd = new ClassDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        SubjectDAL sd = new SubjectDAL();

        List<Major> listM = md.getAllMajor();


        List<String> listStat = new ArrayList<>();
        listStat.add("Active");
        listStat.add("Inactive");

        List<String> listSem = ssd.getAllSemester();

        request.setAttribute("listSj", sd.getAllSubject());
        request.setAttribute("listM", listM);
        request.setAttribute("listStat", listStat);
        request.setAttribute("listSem", listSem);
        request.setAttribute("adud", 1);

        if (request.getParameter("act").equals("add")) {

            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else if (request.getParameter("act").equals("upd")) {
            String id_raw = request.getParameter("id");
            String sj = request.getParameter("sj");

            int classID = 0, subjectID = 0;

            try {
                classID = Integer.parseInt(id_raw);
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }

            model.Class c = cd.getClassByIDASjASem(classID, sj);
            request.setAttribute("c", c);
            request.setAttribute("upd", "1");
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }
    
    void listClassGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        
        List<Account> listAcc = ad.getAllSubjectManager();

        String subject = request.getParameter("subject");
        String sem_raw = request.getParameter("sem");
        request.setAttribute("ds", 1);
        String sem = "";
        if (subject == null || sem_raw == null || subject.equals("") || sem_raw.equals("")) {
            subject = "0";
            sem = "0";
        } else {
            sem = sem_raw;
        }

        request.setAttribute("subject", subject);
        request.setAttribute("sem", sem);
   
        List<Subject> listSJ = sd.getAllSubject();
        List<String> listSemester = ssd.getAllSemester();

        Paging p = new Paging();
        int page = 0;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
        }
        if (request.getParameter("search") == null || request.getParameter("search").equals("")) {
            int sem1 = Integer.parseInt(sem);
            List<model.Class> list = cd.getClassByFilter(subject, sem1);
            System.out.println(subject + " " + sem);
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");

            request.setAttribute("listSJ", listSJ);

            request.setAttribute("listSemester", listSemester);

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("totalEntity", cd.getClassByFilter(subject, sem1).size());
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<model.Class> list = new ArrayList<>();
            int sem1 = Integer.parseInt(sem);
            list = cd.getClassBySearchAFilter(key, subject, sem1);
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);

            request.setAttribute("search", key);
            request.setAttribute("listSJ", listSJ);

            request.setAttribute("listSemester", listSemester);

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
                request.setAttribute("totalEntity", cd.getClassBySearchAFilter(key, subject, sem1).size());
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
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
        if(request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        else {
            adudClassPost(request, response);
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
