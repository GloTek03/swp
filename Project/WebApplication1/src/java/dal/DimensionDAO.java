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
import model.Dimension;
import model.Subject;
import model.SystemSetting;

/**
 *
 * @author ADMIN
 */
public class DimensionDAO extends DBContext implements Serializable {

    public Dimension getDimensionByID(int dimensionID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM dimension WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dimensionID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Dimension d = new Dimension();
                d.setCreatedAt(rs.getTimestamp("created_at"));
                d.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                d.setUpdatedAt(rs.getTimestamp("updated_at"));
                d.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                d.setDescription(rs.getString("description"));
                d.setDimensionID(rs.getInt("dimension_id"));
                d.setDimensionName(rs.getString("dimension_name"));
                d.setDisplayOrder(rs.getInt("display_order"));
                d.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                d.setStatus(rs.getInt("status"));
                d.setType(ssd.getSettingByID(rs.getInt("dimension_type")));
                return d;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Dimension> getDimenBySubjectID(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<Dimension> listDimen = new ArrayList<Dimension>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM dimension "
                    + "WHERE subject_id = ? "
                    + "ORDER BY display_order ASC";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                SubjectDAL SubjectDAO = new SubjectDAL();
                AccountDAL AccountDAO = new AccountDAL();
                SystemSettingDAL SSDAO = new SystemSettingDAL();
                Account creater;
                Account updater;
                creater = AccountDAO.getAccountByAccID(rs.getInt("created_by"));
                updater = AccountDAO.getAccountByAccID(rs.getInt("updated_by"));
                Subject subject = SubjectDAO.getSubjectByID(rs.getInt("subject_id"));
                SystemSetting type = SSDAO.getSettingByID(rs.getInt("dimension_type"));

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
                Dimension d = new Dimension(rs.getInt("dimension_id"),
                        subject,
                        rs.getString("dimension_name"),
                        type,
                        rs.getInt("display_order"),
                        rs.getString("description"),
                        creater,
                        rs.getTimestamp("created_at"),
                        updater,
                        rs.getTimestamp("updated_at"),
                        rs.getInt("status"));

                listDimen.add(d);
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
        return listDimen;
    }

    public boolean updateDimensionByID(Dimension dimen) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "UPDATE dimension SET created_at = ?,"
                    + " created_by = ?,"
                    + " updated_at = ?, "
                    + "updated_by = ?, "
                    + "description = ?,"
                    + " dimension_name = ?,"
                    + " display_order = ?, "
                    + "subject_id = ?, "
                    + "status = ?, "
                    + "dimension_type = ? "
                    + "WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dimen.getDimensionID());
            st.setTimestamp(1, dimen.getCreatedAt());
            int createrID = dimen.getCreatedBy().getID();
            st.setInt(2, createrID);
            st.setTimestamp(3, dimen.getUpdatedAt());
            int updaterID = dimen.getUpdatedBy().getID();
            st.setInt(4, updaterID);
            st.setString(5, dimen.getDescription());
            st.setString(6, dimen.getDimensionName());
            st.setInt(7, dimen.getDisplayOrder());
            int subjectID = sd.GetSubjectID(dimen.getSubject());
            st.setInt(8, subjectID);
            st.setInt(9, dimen.getStatus());
            int settingID = ssd.getIDBySetting(dimen.getType());
            st.setInt(10, settingID);
            st.setInt(11, dimen.getDimensionID());
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println("updateDimen: "+e);
        }
        return false;
    }

    public void activateDimension(int id) {
        try {
            String sql = "UPDATE dimension SET status = 1 WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deactivateDimension(int id) {
        try {
            String sql = "UPDATE dimension SET status = 0 WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean addDimension(Dimension dimension) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "INSERT INTO dimension "
                    + "(created_at, created_by, updated_at, updated_by, description, dimension_name, display_order, subject_id, status, dimension_type) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setTimestamp(1, dimension.getCreatedAt());
            int createrID = dimension.getCreatedBy().getID();
            st.setInt(2, createrID);
            st.setTimestamp(3, dimension.getUpdatedAt());
            int updaterID = dimension.getUpdatedBy().getID();
            st.setInt(4, updaterID);
            st.setString(5, dimension.getDescription());
            st.setString(6, dimension.getDimensionName());
            st.setInt(7, dimension.getDisplayOrder());
            int subjectID = sd.GetSubjectID(dimension.getSubject());
            st.setInt(8, subjectID);
            st.setInt(9, dimension.getStatus());
            int settingID = ssd.getIDBySetting(dimension.getType());
            st.setInt(10, settingID);

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println("addDimen: "+e);
        }
        return false;
    }

}
