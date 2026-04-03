<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Quản lý sách</title></head>
<body>
<h2>QUẢN LÝ SÁCH</h2>

<c:if test="${not empty sessionScope.flash}">
    <p style="color:blue">${sessionScope.flash}</p>
    <c:remove var="flash" scope="session"/>
</c:if>

<form method="get" action="${pageContext.request.contextPath}/sach">
    <input type="hidden" name="action" value="search"/>
    <select name="type">
        <option value="ma">Mã sách</option>
        <option value="ten">Tên sách</option>
    </select>
    <input type="text" name="keyword" placeholder="Nhập từ khóa"/>
    <button type="submit">Tìm</button>
</form>

<p>Tổng số sách: ${tong}</p>
<p><a href="${pageContext.request.contextPath}/sach?action=addForm">+ Thêm sách</a></p>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Mã</th><th>Tên</th><th>Mã TG</th><th>Mã TL</th><th>Năm XB</th><th>NXB</th><th>Đơn giá</th><th>Tồn</th><th>Thao tác</th>
    </tr>
    <c:forEach var="s" items="${dsSach}">
        <tr>
            <td>${s.masach}</td>
            <td>${s.tensach}</td>
            <td>${s.matg}</td>
            <td>${s.matl}</td>
            <td>${s.namxuatban}</td>
            <td>${s.manxb}</td>
            <td>${s.dongia}</td>
            <td>${s.soluongton}</td>
            <td>
                <a href="${pageContext.request.contextPath}/sach?action=editForm&ma=${s.masach}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/sach?action=delete&ma=${s.masach}"
                   onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>