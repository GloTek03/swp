/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.Chapter;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class ChapterDAO extends DBContext implements Serializable {

    public ArrayList<Chapter> getChapterBySubjectID(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<Chapter> listChapter = new ArrayList<Chapter>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM chapter "
                    + "WHERE subject_id = ?"
                    + "ORDER BY display_order ASC ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                SubjectDAL SubjectDAO = new SubjectDAL();
                AccountDAL AccountDAO = new AccountDAL();
                Account creater;
                Account updater;
                creater = AccountDAO.getAccountByAccID(rs.getInt("created_by"));
                updater = AccountDAO.getAccountByAccID(rs.getInt("updated_by"));
                Subject subject = SubjectDAO.getSubjectByID(rs.getInt("subject_id"));

                Chapter c = new Chapter(rs.getInt("chapter_id"),
                        subject,
                        rs.getString("chapter_name"),
                        rs.getInt("display_order"),
                        rs.getString("description"),
                        creater,
                        rs.getTimestamp("created_at"),
                        updater,
                        rs.getTimestamp("updated_at"),
                        rs.getInt("status"));
                listChapter.add(c);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listChapter;
    }

    public Chapter getChapterByID(int id) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM chapter WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                c.setChapterName(rs.getString("chapter_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateChapterByID(Chapter chapter) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "UPDATE chapter SET chapter_name = ?, created_at = ?, created_by = ?, updated_at = ?, updated_by = ?, description = ?, display_order = ?, subject_id = ?, status = ? WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, chapter.getChapterName());
            st.setTimestamp(2, chapter.getCreatedAt());
            int createrID = chapter.getCreatedBy().getID();
            st.setInt(3, createrID);
            st.setTimestamp(4, chapter.getUpdatedAt());
            int updaterID = chapter.getUpdatedBy().getID();
            st.setInt(5, updaterID);
            st.setString(6, chapter.getDescription());
            st.setInt(7, chapter.getDisplayOrder());
            int subjectID = sd.GetSubjectID(chapter.getSubject());
            st.setInt(8, subjectID);
            st.setInt(9, chapter.getStatus());
            st.setInt(10, chapter.getChapterID());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addChapter(Chapter chapter) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "INSERT INTO chapter (chapter_name, created_at, created_by, updated_at, updated_by, description, display_order, subject_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, chapter.getChapterName());
            st.setTimestamp(2, chapter.getCreatedAt());
            int createrID = chapter.getCreatedBy().getID();
            st.setInt(3, createrID);
            st.setTimestamp(4, chapter.getUpdatedAt());
            int updaterID = chapter.getUpdatedBy().getID();
            st.setInt(5, updaterID);
            st.setString(6, chapter.getDescription());
            st.setInt(7, chapter.getDisplayOrder());
            int subjectID = sd.GetSubjectID(chapter.getSubject());
            st.setInt(8, subjectID);
            st.setInt(9, chapter.getStatus());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void activateChapter(int id) {
        try {
            String sql = "UPDATE chapter SET status = 1 WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deactivateChapter(int id) {
        try {
            String sql = "UPDATE chapter SET status = 0 WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
