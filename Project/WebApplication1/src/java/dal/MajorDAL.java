/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Major;

/**
 *
 * @author acer
 */
public class MajorDAL extends DBContext{
    public List<Major> getAllMajor() {
        List<Major> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM major";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Major m = new Major(rs.getString("major_id"), rs.getString("major_name"));
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
