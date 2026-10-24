package dao;

import dto.AccountDTO;
import utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    // =========================================================================
    // 1. HÀM KIỂM TRA ĐĂNG NHẬP (SIGN-IN)
    // =========================================================================
    public AccountDTO checkLogin(String email, String password) {
        // Câu lệnh SQL lấy thông tin dựa trên email và mật khẩu trùng khớp
        String sql = "SELECT user_id, full_name, email, role FROM Users WHERE email = ? AND password = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Loại bỏ khoảng trắng thừa nếu kiểu dữ liệu trong DB là CHAR/NCHAR
            ps.setString(1, email.trim());
            ps.setString(2, password.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AccountDTO account = new AccountDTO();
                    
                    // Khớp chuẩn xác 100% theo tên cột trong SQL Server Management Studio
                    account.setUserId(rs.getInt("user_id"));
                    account.setFullName(rs.getNString("full_name")); 
                    account.setEmail(rs.getString("email"));
                    account.setRole(rs.getString("role"));
                    
                    return account; // Trả về đối tượng đã map đủ dữ liệu để đăng nhập thành công
                }
            }
        } catch (Exception e) {
            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("!!! LOI SQL KHI KIEM TRA DANG NHAP:");
            e.printStackTrace();
            System.out.println("==============================");
        }
        return null; // Trả về null nếu sai tài khoản/mật khẩu hoặc lỗi hệ thống
    }

    // =========================================================================
    // 2. HÀM ĐĂNG KÝ TÀI KHOẢN MỚI (SIGN-UP)
    // =========================================================================
    public boolean registerAccount(String fullName, String email, String password) {
        // Mặc định phân quyền tài khoản mới tạo luôn là CUSTOMER theo ảnh DB nhóm bạn
        String sql = "INSERT INTO Users (full_name, email, password, role) VALUES (?, ?, ?, 'CUSTOMER')";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setNString(1, fullName);
            ps.setString(2, email.trim());
            ps.setString(3, password.trim());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu chèn dữ liệu vào SQL thành công
            
        } catch (Exception e) {
            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("!!! LOI SQL KHI DANG KY TAI KHOAN MOI:");
            e.printStackTrace();
            System.out.println("==============================");
        }
        return false;
    }
}