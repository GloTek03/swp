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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ResetPasswordServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("view/ResetPassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("dad");
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        UserDAO util = new UserDAO();
        //System.out.println("dad");
        if ("reset".equals(action)) {
            try {
                boolean check = util.checkUsernameExist(username);
                System.err.println(check);
                if (!check) {
                    request.setAttribute("error", "Username không tồn tại trong hệ thống");
                    request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
                    return;
                }
                //System.err.println("check success");
                char[] otp = util.generateOTP(6);
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setMaxInactiveInterval(10 * 60);
                User account = util.getAccountDetails(username);
                util.EmailOTPSender(account.getEmail(), otp);
                request.setAttribute("username", username);
                request.getRequestDispatcher("view/ConfirmOTP.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("confirm".equals(action)) {
            char[] sessionOtp = (char[]) request.getSession().getAttribute("otp");
            char[] userOtp = request.getParameter("otp").toCharArray();
            if (Arrays.equals(sessionOtp, userOtp)) {
                request.setAttribute("username", username);
                request.getRequestDispatcher("view/SetNewPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("username", username);
                request.setAttribute("error", "OTP không chính xác");
                request.getRequestDispatcher("view/ConfirmOTP.jsp").forward(request, response);
            }
        } else if ("setNewPass".equals(action)) {
            String newPass = request.getParameter("newPass");
            try {
                boolean SetPass = util.updateAccountPassword(newPass, username);
                if (SetPass) {
                    request.setAttribute("message", "Mật khẩu của bạn đã được thay đổi thành công!");
                    request.getRequestDispatcher("view/SetNewPassword.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Có lỗi xảy ra trong quá trình đổi mật khẩu, vui lòng thử lại sau.");
                    request.getRequestDispatcher("view/SetNewPassword.jsp").forward(request, response);
                }
                request.getRequestDispatcher("view/SetNewPassword.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
