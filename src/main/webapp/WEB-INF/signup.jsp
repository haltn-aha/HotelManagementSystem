<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Booking - Đăng Ký Tài Khoản</title>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 0; padding: 0; }
            .signup-container { width: 400px; margin: 60px auto; padding: 30px; background: white; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
            h2 { text-align: center; color: #333; margin-bottom: 20px; }
            .form-group { margin-bottom: 15px; }
            .form-group label { display: block; margin-bottom: 5px; color: #666; font-weight: bold; font-size: 14px; }
            .form-group input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
            .btn-submit { width: 100%; padding: 12px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; margin-top: 10px; }
            .btn-submit:hover { background-color: #218838; }
            .login-link { text-align: center; margin-top: 15px; font-size: 14px; }
            .login-link a { color: #007bff; text-decoration: none; }
            .login-link a:hover { text-decoration: underline; }
        </style>
    </head>
    <body>
        <div class="signup-container">
            <h2>Đăng Ký Tài Khoản</h2>
            
            <form action="auth?action=signup" method="POST">
                <div class="form-group">
                    <label>Họ và Tên:</label>
                    <input type="text" name="txtFullName" placeholder="Nhập họ và tên..." required />
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="txtEmail" placeholder="Nhập địa chỉ email..." required />
                </div>
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" name="txtPassword" placeholder="Tạo mật khẩu..." required />
                </div>
                <div class="form-group">
                    <label>Số điện thoại:</label>
                    <input type="text" name="txtPhone" placeholder="Nhập số điện thoại..." />
                </div>
                <div class="form-group">
                    <label>Địa chỉ:</label>
                    <input type="text" name="txtAddress" placeholder="Nhập địa chỉ..." />
                </div>
                <button type="submit" class="btn-submit">Đăng Ký Khách Hàng</button>
            </form>
            
            <div class="login-link">
                Đã có tài khoản? <a href="auth">Quay lại Đăng nhập</a>
            </div>
        </div>
    </body>
</html>