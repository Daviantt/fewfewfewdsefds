package com.qlsach.model;
 
import java.util.*;
/**
 *
 * @author ASUS
 */
public class Sach {
    private String masach,tensach,matg,matl,manxb;
    private int dongia,soluongton,namxuatban;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sach s)) return false;
        return Objects.equals(masach, s.masach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masach);
    }
    
    public Sach(){
        masach = tensach = matg = matl = manxb = null;
        dongia = soluongton = namxuatban = 0;
    }
    
    public Sach(String masp, String tensp, String matg, String matl, int namxuatban, String manxb, int dongia, int soluongton){
        this.masach = masp;
        this.tensach = tensp;
        this.matg = matg;
        this.matl = matl;
        this.namxuatban = namxuatban;
        this.manxb = manxb;
        this.dongia = dongia;
        this.soluongton = soluongton;
    }
    
    public Sach(Sach sp){
        masach = sp.masach;
        tensach = sp.tensach;
        matg = sp.matg;
        matl = sp.matl;
        namxuatban = sp.namxuatban;
        manxb = sp.manxb;
        dongia = sp.dongia;
        soluongton = sp.soluongton;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masp) {
        this.masach = masp;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensp) {
        this.tensach = tensp;
    }

    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }
}
