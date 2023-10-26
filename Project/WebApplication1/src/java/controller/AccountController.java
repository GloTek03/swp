/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.User;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class AccountController extends HttpServlet {

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
            out.println("<title>Servlet AccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String username = a.getUser();
        String action = request.getParameter("action");
        System.out.println(username + " " + action);
        UserDAO util = new UserDAO();
        User account;
        try {
            account = util.getAccountDetails(username);
            request.setAttribute("accountDetail", account);
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if ("edit".equals(action)) {
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
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
        try {
            UserDAO util = new UserDAO();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            String username = a.getUser();
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String mobile = request.getParameter("mobileNumber");
            String dobStr = request.getParameter("dob");

            if (fullname == null || email == null || gender == null || mobile == null || dobStr == null) {
                request.setAttribute("errorMessage", "Vui lòng không để trống bất kỳ trường nào.");
                User account = util.getAccountDetails(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("errorMessage", "Email không hợp lệ.");
                User account = util.getAccountDetails(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }
            if (util.checkEmailExist(email)) {
                request.setAttribute("errorMessage", "Email đã tồn tại.");
                User account = util.getAccountDetails(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }

            java.sql.Date dob = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = format.parse(dobStr);
                dob = new java.sql.Date(parsedDate.getTime());
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Ngày sinh không hợp lệ.");
                User account = util.getAccountDetails(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }

            boolean result = util.updateAccountInfo(fullname, dob, gender, email, mobile, username);

            if (result) {
                User account = util.getAccountDetails(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.setAttribute("successMessage", "Cập nhật thông tin tài khoản thành công.");
                    request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                } else {
                    response.sendRedirect("view/AccountDetail.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thông tin tài khoản. Vui lòng thử lại.");
                User account = util.getAccountDetails(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                } else {
                    response.sendRedirect("view/AccountDetail.jsp");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
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
