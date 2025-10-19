package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import dao.UsersDAO;
import dao.UsersDAOImpl;
import entity.Users;

@MultipartConfig()
@WebServlet({
    "/users/index",
    "/users/edit/*",
    "/users/create",
    "/users/update",
    "/users/delete",
    "/users/reset"
})
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Users form = new Users();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsersDAO dao = new UsersDAOImpl();
        String path = request.getServletPath();

        // Lấy user đang đăng nhập
        Users currentUser = (Users) request.getSession().getAttribute("user");

        // Cấu hình chuyển đổi ngày
        DateTimeConverter dtc = new DateConverter(new Date());
        dtc.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dtc, Date.class);

        // Xử lý hành động CRUD
        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1);
            form = dao.selectById(id);
        } else if (path.contains("create")) {
            try {
                BeanUtils.populate(form, request.getParameterMap());
                dao.create(form);
                form = new Users(); // reset form
                request.setAttribute("message", "Thêm người dùng thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi: Không thể thêm người dùng (có thể trùng ID).");
            }
        } else if (path.contains("update")) {
            try {
                BeanUtils.populate(form, request.getParameterMap());
                dao.update(form);
                request.setAttribute("message", "Cập nhật người dùng thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi: Không thể cập nhật người dùng.");
            }
        } else if (path.contains("delete")) {
            try {
                dao.deleteById(form.getId());
                form = new Users();
                request.setAttribute("message", "Xóa người dùng thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi: Không thể xóa người dùng.");
            }
        } else if (path.contains("reset")) {
            form = new Users();
        }

        request.setAttribute("item", form);

        // Hiển thị danh sách người dùng
        List<Users> list = new ArrayList<>();
        list = dao.selectAll();
           
        // refresh form nếu đang edit
        if (form.getId() != null && !form.getId().isEmpty()) {
            form = dao.selectById(form.getId());
        }

        request.setAttribute("list", list);
        request.setAttribute("page", "nguoidung.jsp");
        request.getRequestDispatcher("/views/admin/homeadmin.jsp").forward(request, response);
    }
}
