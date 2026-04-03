package com.qlsach.model;

/**
 *
 * @author Quyen
 */
public enum Result {
    thanhcong("thành công!"),
    khongtontai("không tồn tại!"),
    loidb("Lỗi khi thao tác database!"),
    thieuthongtin("Vui lòng nhập đầy đủ thông tin!"),
    trungma("Trùng mã!"),
    thatbai("Thất bại!");
    
    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
