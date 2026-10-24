<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.AccountDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Booking - Home</title>
        <style>
            body { 
                font-family: Arial, sans-serif; 
                background-color: #f4f7f6; 
                margin: 0; 
                padding: 40px; 
            }
            .welcome-container { 
                max-width: 800px; 
                margin: 0 auto; 
                padding: 30px; 
                background: white; 
                border-radius: 8px; 
                box-shadow: 0px 4px 10px rgba(0,0,0,0.05); 
                position: relative;
            }
            .title-success { 
                color: #28a745; 
                font-size: 24px; 
                font-weight: bold; 
                margin-bottom: 15px; 
            }
            .user-info { 
                font-size: 18px; 
                color: #333; 
                margin-bottom: 10px; 
            }
            .sub-text { 
                color: #666; 
                font-size: 14px; 
                margin-bottom: 30px; 
            }
            /* Style cho nút Logout */
            .btn-logout { 
                display: inline-block;
                background-color: #dc3545; 
                color: white; 
                text-decoration: none; 
                padding: 10px 20px; 
                border-radius: 4px; 
                font-weight: bold; 
                font-size: 14px; 
                transition: background 0.2s; 
            }
            .btn-logout:hover { 
                background-color: #bd2130; 
            }
        </style>
    </head>
    <body>
        <%
            // Lấy dữ liệu động từ Session nếu muốn hiển thị chính xác tên user đăng nhập
            AccountDTO user = (AccountDTO) session.getAttribute("LOGIN_USER");
            String displayName = (user != null) ? user.getFullName() : "test";
            String displayEmail = (user != null) ? user.getEmail() : "123@gmail.com";
        %>

        <div class="welcome-container">
            <div class="title-success">Đăng nhập thành công (CUSTOMER)!</div>
            
            <div class="user-info">
                <strong>Chào mừng khách hàng:</strong> <%= displayName %> (<%= displayEmail %>)
            </div>
            
            <div class="sub-text">
                Đây là trang chủ dự án Hotel Booking của bạn.
            </div>

            <!-- NÚT LOGOUT KHỚP THEO URL YÊU CẦU -->
          <a href="auth?action=logout" class="btn-logout">Đăng xuất</a>
        </div>
    </body>
</html>