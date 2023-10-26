/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.VideoTracking;

/**
 *
 * @author acer
 */
public class VideoDAL extends DBContext{
    public VideoTracking getVideoStateByAccALss(int accID, int lessonID) {
        AccountDAL ad = new AccountDAL();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        try {
            String sql = "SELECT * FROM video_tracking WHERE account_id = ?"
                    + " AND lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, lessonID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                VideoTracking vt = new VideoTracking();
                vt.setA(ad.getAccountByAccID(accID));
                vt.setLesson(ssd.getLessonByID(lessonID));
                vt.setState(rs.getDouble("state"));
                vt.setStatus(rs.getInt("status"));
                return vt;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updateStateByAccALss(int accID, int lessonID, double time, int status) {
        try {
            String sql = "UPDATE video_tracking SET state = ?, status = ? WHERE account_id = ?"
                    + " AND lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setDouble(1, time);
            st.setInt(2, status);
            st.setInt(3, accID);
            st.setInt(4, lessonID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void add(int accID, int lessonID, double state, int status) {
        try {
            String sql = "INSERT INTO video_tracking VALUES (?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, lessonID);
            st.setDouble(3, state);
            st.setInt(4, status);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
