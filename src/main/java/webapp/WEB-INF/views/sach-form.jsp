<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Form sách</title></head>
<body>
<c:set var="isEdit" value="${not empty sach}" />

<h3>
    <c:choose>
        <c:when test="${isEdit}">SỬA SÁCH</c:when>
        <c:otherwise>THÊM SÁCH</c:otherwise>
    </c:choose>
</h3>

<form method="post" action="${pageContext.request.contextPath}/sach">
    <input type="hidden" name="action" value="${isEdit ? 'edit' : 'add'}"/>

    <p>Mã sách: <input name="masach" value="${sach.masach}" ${isEdit ? 'readonly' : ''}/></p>
    <p>Tên sách: <input name="tensach" value="${sach.tensach}"/></p>
    <p>Mã tác giả: <input name="matg" value="${sach.matg}"/></p>
    <p>Mã thể loại: <input name="matl" value="${sach.matl}"/></p>
    <p>Năm xuất bản: <input name="namxuatban" value="${sach.namxuatban}"/></p>
    <p>Nhà xuất bản: <input name="manxb" value="${sach.manxb}"/></p>
    <p>Đơn giá: <input name="dongia" value="${sach.dongia}"/></p>
    <p>Số lượng tồn: <input name="soluongton" value="${sach.soluongton}"/></p>

    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/sach">Hủy</a>
</form>
</body>
</html>