/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SubjectProgress;

/**
 *
 * @author acer
 */
public class SubjectProgressDAL extends DBContext{
    public List<SubjectProgress> getAllProgressBySubjectASemester(String subjectID, String semester) {
        List<SubjectProgress> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM subject_prog WHERE subject_code = ? AND semester = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, subjectID);
            st.setString(2, semester);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                SubjectProgress sp = new SubjectProgress(rs.getString("subject_code"), rs.getString("semester"), rs.getInt("prog_id"), rs.getString("prog_name"), rs.getString("description"), rs.getDate("start_date"), rs.getTime("start_time"), rs.getDate("end_date"), rs.getTime("end_time"));
                list.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
