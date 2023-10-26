/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author acer
 */
public class ValidInput {
    public int validInt(String s, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int n = 0;
        while(true) {
            try {
                n = Integer.parseInt(s);
                break;
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        return n;
    }
    
    public double validDouble(String s, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        double n = 0;
        while(true) {
            try {
                n = Double.parseDouble(s);
                break;
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        return n;
    }
}
