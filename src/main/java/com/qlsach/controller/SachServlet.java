package com.qlsach.controller;

import com.qlsach.model.Result;
import com.qlsach.model.Sach;
import com.qlsach.service.SachBUS;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sach")
public class SachServlet extends HttpServlet {
    private final SachBUS sachBUS = new SachBUS();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            showList(req, resp, sachBUS.getAll());
            return;
        }

        switch (action) {
            case "addForm":
                req.getRequestDispatcher("/WEB-INF/views/sach-form.jsp").forward(req, resp);
                break;
            case "editForm":
                String ma = req.getParameter("ma");
                Sach sach = sachBUS.timSachTheoMa(ma);
                req.setAttribute("sach", sach);
                req.getRequestDispatcher("/WEB-INF/views/sach-form.jsp").forward(req, resp);
                break;
            case "delete":
                Result kq = sachBUS.xoaSach(req.getParameter("ma"));
                req.getSession().setAttribute("flash", kq.getMessage());
                resp.sendRedirect(req.getContextPath() + "/sach");
                break;
            case "search":
                search(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/sach");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Result kq = Result.thatbai;

        try {
            Sach s = parseSach(req);
            if ("add".equals(action)) kq = sachBUS.themSach(s);
            else if ("edit".equals(action)) kq = sachBUS.suaSach(s);
        } catch (Exception e) {
            kq = Result.thieuthongtin;
        }

        req.getSession().setAttribute("flash", kq.getMessage());
        resp.sendRedirect(req.getContextPath() + "/sach");
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String keyword = req.getParameter("keyword");
        List<Sach> ds = new ArrayList<>();

        if (keyword == null || keyword.isBlank()) {
            ds = sachBUS.getAll();
        } else {
            switch (type) {
                case "ma":
                    Sach s = sachBUS.timSachTheoMa(keyword);
                    if (s != null) ds.add(s);
                    break;
                case "ten":
                    ds = sachBUS.timSachTheoTen(keyword);
                    break;
                default:
                    ds = sachBUS.getAll();
            }
        }
        showList(req, resp, ds);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp, List<Sach> ds) throws ServletException, IOException {
        req.setAttribute("dsSach", ds);
        req.setAttribute("tong", sachBUS.thongKeSoLuongSach(ds));
        req.getRequestDispatcher("/WEB-INF/views/sach-list.jsp").forward(req, resp);
    }

    private Sach parseSach(HttpServletRequest req) {
        Sach s = new Sach();
        s.setMasach(req.getParameter("masach"));
        s.setTensach(req.getParameter("tensach"));
        s.setMatg(req.getParameter("matg"));
        s.setMatl(req.getParameter("matl"));
        s.setNamxuatban(Integer.parseInt(req.getParameter("namxuatban")));
        s.setManxb(req.getParameter("manxb"));
        s.setDongia(Integer.parseInt(req.getParameter("dongia")));
        s.setSoluongton(Integer.parseInt(req.getParameter("soluongton")));
        return s;
    }
}