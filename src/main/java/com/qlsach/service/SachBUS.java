package com.qlsach.service;

import com.qlsach.dao.SachDAO;
import com.qlsach.model.Result;
import com.qlsach.model.Sach;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */

public class SachBUS {
    private final SachDAO sachDAO = new SachDAO();
    private List<Sach> dss = new ArrayList<>();

    public SachBUS() {
        dss = sachDAO.loadSach();
    }

    public List<Sach> getAll() {
        return dss;
    }

    public void reload() {
        dss = sachDAO.loadSach();
    }

    public Sach timSachTheoMa(String ma) {
        for (Sach s : dss) {
            if (s.getMasach().equalsIgnoreCase(ma)) return s;
        }
        return null;
    }

    public List<Sach> timSachTheoTen(String ten) {
        List<Sach> rs = new ArrayList<>();
        for (Sach s : dss) {
            if (s.getTensach().toLowerCase().contains(ten.toLowerCase())) rs.add(s);
        }
        return rs;
    }

    public Result themSach(Sach sach) {
        if (sach.getMasach() == null || sach.getMasach().isBlank()
                || sach.getTensach() == null || sach.getTensach().isBlank()
                || sach.getMatg() == null || sach.getMatg().isBlank()
                || sach.getMatl() == null || sach.getMatl().isBlank()
                || sach.getManxb() == null || sach.getManxb().isBlank()
                || sach.getNamxuatban() <= 0 || sach.getDongia() <= 0 || sach.getSoluongton() < 0) {
            return Result.thieuthongtin;
        }

        if (timSachTheoMa(sach.getMasach()) != null) return Result.trungma;
        if (!sachDAO.insertSach(sach)) return Result.thatbai;

        dss.add(sach);
        return Result.thanhcong;
    }

    public Result suaSach(Sach sach) {
        if (timSachTheoMa(sach.getMasach()) == null) return Result.khongtontai;
        if (!sachDAO.updateSach(sach)) return Result.thatbai;

        int idx = dss.indexOf(sach);
        if (idx != -1) dss.set(idx, sach);
        return Result.thanhcong;
    }

    public Result xoaSach(String ma) {
        if (timSachTheoMa(ma) == null) return Result.khongtontai;
        if (!sachDAO.deleteSach(ma)) return Result.thatbai;

        dss.removeIf(s -> s.getMasach().equalsIgnoreCase(ma));
        return Result.thanhcong;
    }

    public int thongKeSoLuongSach(List<Sach> ds) {
        return ds == null ? 0 : ds.size();
    }
    
    public ArrayList<Sach> timSachTheoMaTacGia(String matg){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getMatg().toLowerCase().contains(matg.toLowerCase())){
                rs.add(s);
            }
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoMaTheLoai(String matl){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getMatl().toLowerCase().contains(matl.toLowerCase())){
                rs.add(s);
            }
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoNamXuatBan(int nam){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getNamxuatban() == nam){
                rs.add(s);
            }
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoNhaXuatBan(String nxb){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getManxb().toLowerCase().contains(nxb.toLowerCase())){
                rs.add(s);
            }
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoDonGiaTrongKhoang(int giamin, int giamax){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getDongia() >= giamin && s.getDongia() <= giamax){
                rs.add(s);
            }
        }
        return rs;
    }
    
//    //Sap Xep
//    
//    public ArrayList<Sach> sapXepSachTheoGiaGiamDan(){
//        ArrayList<Sach> tmp = new ArrayList<Sach>(dss);
//        tmp.sort((a,b) -> a.getDongia() - b.getDongia());
//        return tmp;
//    }
//    
//    public ArrayList<Sach> sapXepSachTheoGiaTangDan(){
//        ArrayList<Sach> tmp = new ArrayList<Sach>(dss);
//        tmp.sort((a,b) -> b.getDongia() - a.getDongia());
//        return tmp;
//    }
//    
//    //cap nhat
//    public boolean capNhatSoLuongTon(String ma,int soluongban){
//        Sach s = timSachTheoMa(ma);
//        if(s == null){
//            return false;
//        }
//        if(s.getSoluongton() < soluongban){
//            return false;
//        }
//        s.setSoluongton(s.getSoluongton() - soluongban);
//        return sachdao.updateSach(s);
//    }
    
    public ArrayList<Sach> sachSapHet(int minsl){
        ArrayList<Sach> tmp = new ArrayList<Sach>();
        for(Sach s : dss){
            if(s.getSoluongton() <= minsl){
                tmp.add(s);
            }
        }
        return tmp;
    }
    
    public String autoThemMa(){
        int max = 0;
        
        for(Sach s : dss){
            if(s.getMasach() != null && s.getMasach().matches("S\\d+")){
                int num = Integer.parseInt(s.getMasach().substring(1));
                if(num > max && num - max == 1){
                    max = num;
                }
            }
        }
        return String.format("S%03d",max + 1);
    }
    
    public ArrayList<Sach> sachHetHang(){
        return sachSapHet(0);
    }
    
    public int thongKeSoLuongSach(ArrayList<Sach> dstemp){
        int cnt = 0;
        for(Sach s : dstemp){
            cnt++;
        }
        return cnt;
    }
    
    public void sachreload(){
        dss = sachdao.loadSach();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
