package controllers;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

    // =========================================================================
    // XỬ LÝ PHƯƠNG THỨC GET
    // =========================================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // 1. Nhánh xử lý Đăng xuất (GET /auth?action=logout)
        if ("logout".equals(action)) {
            session.invalidate(); // Hủy session hiện tại
            request.setAttribute("SUCCESS_MSG", "Bạn đã đăng xuất thành công!");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            return;
        } 
        
        // 2. Nhánh xử lý chuyển hướng xem form Đăng ký (GET /auth?action=signup)
        if ("signup".equals(action)) {
            request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        // 3. MẶC ĐỊNH: Xử lý khi truy cập trực tiếp đường dẫn /auth (Trang đăng nhập)
        // Lấy thông tin user hiện tại trong session ra để kiểm tra trạng thái
        AccountDTO user = (AccountDTO) session.getAttribute("LOGIN_USER");
        
        if (user != null) {
            // NẾU ĐÃ ĐĂNG NHẬP RỒI: Tự động chuyển thẳng vào trang chức năng tương ứng, không giữ lại ở login nữa
            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("-> Thong bao: User [" + user.getEmail() + "] da dang nhap truoc do. Tu dong chuyen huong trang!");
            System.out.println("==============================");
            
            if ("ADMIN".equals(user.getRole())) {
                request.getRequestDispatcher("WEB-INF/admin_dashboard.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
            }
        } else {
            // NẾU CHƯA ĐĂNG NHẬP (user == null): Chuyển đến login nội bộ theo đúng yêu cầu phân công
            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("-> Thong bao: Chua phat hien phien dang nhap hop le. Chuyen den login!");
            System.out.println("==============================");
            
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    // =========================================================================
    // XỬ LÝ PHƯƠNG THỨC POST
    // =========================================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");

        // Nhánh xử lý dữ liệu Đăng nhập: POST /auth?action=signin
        if ("signin".equals(action)) {
            String email = request.getParameter("txtEmail");
            String pass = request.getParameter("txtPassword");

            AccountDAO dao = new AccountDAO();
            AccountDTO account = dao.checkLogin(email, pass);

            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("-> Thuc hien dang nhap: [" + email + "]");

            if (account != null) {
                System.out.println("-> Ket qua DB: DANG NHAP THANH CONG! User: " + account.getFullName());
                System.out.println("==============================");
                
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", account);

                if ("ADMIN".equals(account.getRole())) {
                    request.getRequestDispatcher("WEB-INF/admin_dashboard.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                }
            } else {
                System.out.println("-> Ket qua DB: DANG NHAP THAT BAI (Sai mat khau hoac tai khoan)!");
                System.out.println("==============================");
                
                request.setAttribute("ERROR_MSG", "Email hoặc mật khẩu không đúng!");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
            
        // Nhánh xử lý dữ liệu tạo tài khoản mới: POST /auth?action=signup
        } else if ("signup".equals(action)) {
            String fullName = request.getParameter("txtFullName");
            String email = request.getParameter("txtEmail");
            String pass = request.getParameter("txtPassword");

            AccountDAO dao = new AccountDAO();
            boolean isSuccess = dao.registerAccount(fullName, email, pass);

            System.out.println("====== SYSTEM DEBUG LOG ======");
            System.out.println("-> Thuc hien tao tai khoan: [" + email + "]");
            
            if (isSuccess) {
                System.out.println("-> Ket qua DB: THEM TAI KHOAN THANH CONG!");
                System.out.println("==============================");
                
                request.setAttribute("SUCCESS_MSG", "Đăng ký thành công tài khoản " + email + "! Mời bạn đăng nhập.");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            } else {
                System.out.println("-> Ket qua DB: THEM TAI KHOAN THAT BAI (Trung Email hoac loi he thong)!");
                System.out.println("==============================");
                
                request.setAttribute("ERROR_MSG", "Đăng ký thất bại! Email này đã tồn tại trong hệ thống.");
                request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
            }
        }
    }
}