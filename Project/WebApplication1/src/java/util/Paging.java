/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author acer
 */
public class Paging {

    public int getStart(int page, int size, int numPerPage) {
        int totalPage = (size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage + 1);
        int start = (page - 1) * numPerPage;
        return start;
    }

    public int getEnd(int page, int size, int numPerPage) {
        int totalPage = (size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage + 1);
        int end = (page == totalPage) ? (size - 1) : (page * numPerPage - 1);
        return end;
    }
    
    public int getTotalPage(int page, int size, int numPerPage) {
        int totalPage = (size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage + 1);
        return totalPage;
    }
}
