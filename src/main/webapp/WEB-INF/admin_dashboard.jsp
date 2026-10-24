<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.AccountDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f7f6; display: flex; }
            .sidebar { width: 250px; background-color: #343a40; color: white; height: 100vh; padding: 20px; box-sizing: border-box; }
            .sidebar h2 { font-size: 20px; text-align: center; padding-bottom: 15px; border-bottom: 1px solid #4b545c; }
            .sidebar ul { list-style: none; padding: 0; margin: 20px 0; }
            .sidebar ul li { padding: 12px 10px; border-bottom: 1px solid #4b545c; }
            .sidebar ul li a { color: #c2c7d0; text-decoration: none; display: block; }
            .sidebar ul li a:hover { color: white; }
            .main-content { flex: 1; padding: 40px; }
            .admin-header { display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #dee2e6; padding-bottom: 15px; }
            .btn-logout { background-color: #dc3545; color: white; text-decoration: none; padding: 8px 15px; border-radius: 4px; font-weight: bold; font-size: 13px; }
            .btn-logout:hover { background-color: #bd2130; }
        </style>
    </head>
    <body>
        <%
            AccountDTO user = (AccountDTO) session.getAttribute("LOGIN_USER");
            String adminName = (user != null) ? user.getFullName() : "Admin";
        <%

        <!-- Thanh Menu bên trái của Admin -->
        <div class="sidebar">
            <h2>HỆ THỐNG QUẢN LÝ</h2>
            <ul>
                <li><a href="#">Quản lý phòng</a></li>
                <li><a href="#">Quản lý hóa đơn</a></li>
                <li><a href="#">Quản lý khách hàng</a></li>
            </ul>
        </div>

        <!-- Nội dung chính Dashboard -->
        <div class="main-content">
            <div class="admin-header">
                <h2>Bảng điều khiển Admin - Xin chào <%= adminName %></h2>
                
                <!-- NÚT ĐĂNG XUẤT CHO ADMIN -->
                <a href="auth?action=logout" class="btn-logout">Đăng xuất</a>
            </div>
            
            <div style="background: white; padding: 20px; margin-top: 20px; border-radius: 6px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
                <p>Nội dung thống kê doanh thu, trạng thái phòng khách sạn hiển thị ở đây...</p>
            </div>
        </div>
    </body>
</html>