package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoriesDAO;
import dao.CategoriesDAOImpl;
import entity.Categories;

@WebServlet({ 
	"/categories/index", 
	"/categories/edit/*", 
	"/categories/create", 
	"/categories/update",
	"/categories/delete", 
	"/categories/reset" 
})
public class CategoriesServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lấy thông tin trên form lưu vào biến form
		Categories form = new Categories();
		String message = "";
		try {
			BeanUtils.populate(form, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Tạo đối tượng truy cập bảng Categories
		CategoriesDAO dao = new CategoriesDAOImpl();
		
		String path = request.getServletPath();
		if (path.contains("edit")) {
			String id = request.getPathInfo().substring(1);
			form = dao.selectById(id);
		} else if (path.contains("create")) {
			try {
				dao.create(form);
				form = new Categories();
			} catch (RuntimeException e) {
				message = "Trùng khóa chính";
			}
		} else if (path.contains("update")) {
			dao.update(form);
		} else if (path.contains("delete")) {
			dao.deleteById(form.getId());
			form = new Categories();
		} else {
			form = new Categories();
		}
		
		request.setAttribute("item", form);
		List<Categories> list = dao.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("message", message);

		
		request.setAttribute("page", "loaitin.jsp");
		request.getRequestDispatcher("/views/admin/homeadmin.jsp").forward(request, response);
	}
}
