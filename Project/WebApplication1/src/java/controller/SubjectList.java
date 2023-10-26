/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import dal.SubjectDAL;
import dal.SubjectSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Subject;
import util.Paging;

/**
 *
 * @author acer
 */
public class SubjectList extends HttpServlet {

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
            out.println("<title>Servlet SubjectList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectList at " + request.getContextPath() + "</h1>");
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
                    listSubjectGet(request, response);
                    break;
                }
                case "view": {
                    getSubjectDetail(request, response, request.getParameter("id"));
                    break;
                }
                case "upd": {
                    updSubjectGet(request, response);
                    break;
                }
                case "act": {
                    activateDeactivate(request, response);
                    break;
                }
                case "add": {
                    addSubjectGet(request, response);
                    break;
                }
                default: {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                    break;
                }
            }
        }
    }
    
    void activateDeactivate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        else {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            SubjectDAL sd = new SubjectDAL();
  
            if(sd.getSubjectByID(id).getStatus()== 1) {
                sd.deactivateSubject(id);
            }
            else {
                sd.activateSubject(id);
            }
     
            response.sendRedirect("sjlist?act=list");
        }
    }

    void updSubjectPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAL ad = new AccountDAL();
        if (request.getParameter("id") == null || request.getParameter("name") == null || request.getParameter("des") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);

        } else {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String des = request.getParameter("des");
            String m = request.getParameter("manager");
            Account a = ad.getAccountByUsername(m);
            SubjectDAL sd = new SubjectDAL();
            if (sd.getSubjectByID(id) == null) {
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            } else {
                sd.updSubjectDetail(id, name, des, code, a.getID());
                Subject s = sd.getSubjectByID(id);
                request.setAttribute("ms", "Sửa môn học thành công");
                request.setAttribute("s", s);
                request.setAttribute("upd", 1);
                request.setAttribute("list", ad.getAllSubjectManager());
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
            }
        }
    }

    void listSubjectGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();

        List<Account> listAcc = ad.getAllSubjectManager();

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
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getAllSubject();
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", request.getParameter("mng"));
                list = sd.getAllSubjectByMng(request.getParameter("mng"));
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));
                for (int i = 0; i < sd.getSubjectByPage(start, end, list).size(); i++) {
                    System.out.println(sd.getSubjectByPage(start, end, list).get(i).getSubjectCode());
                }
                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject().size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getSubjectBySearch(key);
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", request.getParameter("mng"));
                list = sd.getSubjectBySearchAMng(key, request.getParameter("mng"));
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", key);
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));
      
                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject().size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        }
    }

    void updSubjectGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            String id = request.getParameter("id");

            if (sd.getSubjectByCode(id) == null) {
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            } else {
                Subject s = sd.getSubjectByCode(id);
                request.setAttribute("list", ad.getAllSubjectManager());
                request.setAttribute("s", s);
                request.setAttribute("upd", "1");
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
            }
        }
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
                if (!ssd.getAllSettingBySubject(id).isEmpty()) {
                    request.setAttribute("list", ssd.getListByPage(start, end, ssd.getAllSettingBySubject(id)));
                    request.setAttribute("size", ssd.getListByPage(start, end, ssd.getAllSettingBySubject(id)).size());
                } else {
                    request.setAttribute("size", 0);
                    request.setAttribute("msg", "Không có setting nào của môn học này");
                }
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

    void addSubjectGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAL ad = new AccountDAL();
        request.setAttribute("listA", ad.getAllSubjectManager());
        request.setAttribute("act", "add");
        request.setAttribute("add", "1");
        request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
    }

    void addSubjectPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") == null || request.getParameter("name") == null || request.getParameter("des") == null) {
            String ms = "Vui lòng điền đầy đủ thông tin";
            request.setAttribute("ms", ms);
            request.setAttribute("add", 1);
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        } else {
            String id = request.getParameter("id");
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String des = request.getParameter("des");
            String m = request.getParameter("manager");
            SubjectDAL sd = new SubjectDAL();
            AccountDAL ad = new AccountDAL();
            if (sd.getSubjectByCode(id) != null) {
                String ms = "ID đã tồn tại";
                request.setAttribute("ms", ms);
                request.setAttribute("listA", ad.getAllSubjectManager());
                request.setAttribute("add", "1");
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
            } else {
                sd.insertSubject(id, name, des, ad.getAccountByUsername(m).getID());
                Subject s = sd.getSubjectByCode(id);
                String ms = "Thêm thành công môn học";
                request.setAttribute("listA", ad.getAllSubjectManager());
                List<Subject> list = new ArrayList<>();
                list.add(s);
                request.setAttribute("list", list);
                request.setAttribute("ms", ms);
                request.setAttribute("add", "1");
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "upd": {
                    updSubjectPost(request, response);
                    break;
                }
                case "add": {
                    addSubjectPost(request, response);
                    break;
                }
                default: {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                    break;
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
