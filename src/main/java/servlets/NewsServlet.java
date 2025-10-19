package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import dao.CategoriesDAO;
import dao.CategoriesDAOImpl;
import dao.NewsDAO;
import dao.NewsDAOImpl;
import entity.News;
import entity.Users;

@MultipartConfig()
@WebServlet({
	"/news/index",
	"/news/edit/*",
	"/news/create",
	"/news/update",
	"/news/delete",
	"/news/reset",
	"/news/upload"
})
public class NewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	News form = new News();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsDAO dao = new NewsDAOImpl();
		String path = request.getServletPath();
		
		// ✅ Lấy thông tin user đang đăng nhập
		Users user = (Users) request.getSession().getAttribute("user");

		// Cấu hình chuyển đổi ngày
		DateTimeConverter dtc = new DateConverter(new Date());
		dtc.setPattern("yyyy-MM-dd");
		ConvertUtils.register(dtc, Date.class);

		// Xử lý các thao tác CRUD
		if (path.contains("edit")) {
			String id = request.getPathInfo().substring(1);
			form = dao.selectById(id);
		} else if (path.contains("create")) {
			try {
				BeanUtils.populate(form, request.getParameterMap());
				dao.create(form);
				form = new News(); // reset form sau khi tạo
				request.setAttribute("message", "Thêm mới tin tức thành công!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "Lỗi: Không thể thêm tin mới (có thể trùng ID).");
			}
		} else if (path.contains("update")) {
			try {
				BeanUtils.populate(form, request.getParameterMap());
				dao.update(form);
				request.setAttribute("message", "Cập nhật tin tức thành công!");
			} catch (Exception e) {
				e.printStackTrace();
		        request.setAttribute("message", "Lỗi: Không thể cập nhật tin tức.");
			}
		} else if (path.contains("delete")) {
			try {
				dao.delete(form.getId());
				form = new News();
			} catch (Exception e) {}
		} else if (path.contains("upload")) {
			Part part = request.getPart("image");
			try {
				BeanUtils.populate(form, request.getParameterMap());
				form.setImage(part.getSubmittedFileName());
				uploadFile(request, part, "image");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			form = new News();
		}

		request.setAttribute("item", form);
		// ✅ Hiển thị danh sách tin tức theo quyền người đăng nhập
		List<News> list = new ArrayList<>();

		if (user != null) {
		    if (user.isRole()) {
		        // Admin → thấy tất cả tin
		        list = dao.selectAll();
		    } else {
		        // User → chỉ thấy tin của chính mình
		        list = dao.selectByUser(user.getId());
		    }
		}
		// Nếu edit hoặc thao tác khác liên quan form
		if (form.getId() != null && !form.getId().isEmpty()) {
		    form = dao.selectById(form.getId()); // luôn lấy bản mới nhất
		}
		CategoriesDAO cateDao = new CategoriesDAOImpl();
		request.setAttribute("categories", cateDao.selectAll());
		request.setAttribute("list", list);

		// Forward sang trang JSP
		request.setAttribute("page", "tintuc.jsp");
		request.getRequestDispatcher("/views/admin/homeadmin.jsp").forward(request, response);
	}

	// Hàm upload ảnh
	private void uploadFile(HttpServletRequest request, Part part, String folder) throws IOException {
		String path = request.getServletContext().getRealPath(folder);
		// kiem tra folder images co ton tai chua, neu chua thi tao
		File dir = new File(path);
		if (!dir.exists()) dir.mkdir();
		// sao chep hinh tu local len WEb Server
		try {
		part.write(path + "/" + part.getSubmittedFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
