<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Booking - Đăng Nhập</title>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 0; padding: 0; }
            .login-container { width: 350px; margin: 120px auto; padding: 30px; background: white; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
            h2 { text-align: center; color: #333; margin-bottom: 20px; }
            .error { color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; padding: 10px; border-radius: 4px; margin-bottom: 15px; font-size: 14px; text-align: center; }
            .success { color: #155724; background-color: #d4edda; border: 1px solid #c3e6cb; padding: 10px; border-radius: 4px; margin-bottom: 15px; font-size: 14px; text-align: center; }
            .form-group { margin-bottom: 20px; }
            .form-group label { display: block; margin-bottom: 8px; color: #666; font-weight: bold; }
            .form-group input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
            .btn-submit { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; }
            .btn-submit:hover { background-color: #0056b3; }
            .signup-link { text-align: center; margin-top: 15px; font-size: 14px; }
            .signup-link a { color: #007bff; text-decoration: none; }
            .signup-link a:hover { text-decoration: underline; }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Đăng Nhập</h2>
            
            <%-- 1. Thông báo LỖI đăng nhập --%>
            <% 
                String error = (String) request.getAttribute("ERROR_MSG");
                if (error != null) {
            %>
                <div class="error"><%= error %></div>
            <% 
                } 
            %>

            <%-- 2. Thông báo THÀNH CÔNG (Sau khi đăng ký hoặc đăng xuất) --%>
            <% 
                String success = (String) request.getAttribute("SUCCESS_MSG");
                if (success != null) {
            %>
                <div class="success"><%= success %></div>
            <% 
                } 
            %>
            
            <form action="auth?action=signin" method="POST">
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="txtEmail" placeholder="Nhập email của bạn..." required autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" name="txtPassword" placeholder="Nhập mật khẩu..." required/>
                </div>
                <button type="submit" class="btn-submit">Đăng Nhập</button>
            </form>
            
            <%-- Gọi qua GET phương thức /auth?action=signup để hiển thị trang đăng ký --%>
            <div class="signup-link">
                Chưa có tài khoản? <a href="auth?action=signup">Đăng ký ngay</a>
            </div>
        </div>
    </body>
</html>