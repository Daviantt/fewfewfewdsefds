package com.qlsach.dao;

import com.qlsach.database.DBConnection;
import com.qlsach.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    public ArrayList<Sach> loadSach() {
        ArrayList<Sach> dss = new ArrayList<>();
        String qry = "SELECT masach, tensach, matacgia, matheloai, namxuatban, manxb, dongia, soluongton FROM sach";
        try (Connection conn = DBConnection.getDBConnection();
             PreparedStatement st = conn.prepareStatement(qry);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                dss.add(new Sach(
                        rs.getString("masach"),
                        rs.getString("tensach"),
                        rs.getString("matacgia"),
                        rs.getString("matheloai"),
                        rs.getInt("namxuatban"),
                        rs.getString("manxb"),
                        rs.getInt("dongia"),
                        rs.getInt("soluongton")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dss;
    }

    public boolean insertSach(Sach sach) {
        String qry = "INSERT INTO sach VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getDBConnection();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, sach.getMasach());
            st.setString(2, sach.getTensach());
            st.setString(3, sach.getMatg());
            st.setString(4, sach.getMatl());
            st.setInt(5, sach.getNamxuatban());
            st.setString(6, sach.getManxb());
            st.setInt(7, sach.getDongia());
            st.setInt(8, sach.getSoluongton());
            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteSach(String masach) {
        String qry = "DELETE FROM sach WHERE masach = ?";
        try (Connection conn = DBConnection.getDBConnection();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, masach);
            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateSach(Sach sach) {
        String qry = "UPDATE sach SET tensach=?, matacgia=?, matheloai=?, namxuatban=?, manxb=?, dongia=?, soluongton=? WHERE masach=?";
        try (Connection conn = DBConnection.getDBConnection();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, sach.getTensach());
            st.setString(2, sach.getMatg());
            st.setString(3, sach.getMatl());
            st.setInt(4, sach.getNamxuatban());
            st.setString(5, sach.getManxb());
            st.setInt(6, sach.getDongia());
            st.setInt(7, sach.getSoluongton());
            st.setString(8, sach.getMasach());
            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}