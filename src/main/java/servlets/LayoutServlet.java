package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoriesDAO;
import dao.CategoriesDAOImpl;
import dao.NewsDAO;
import dao.NewsDAOImpl;
import entity.Categories;
import entity.News;

/**
 * Servlet implementation class LayoutServlet
 */
@WebServlet({ "/trangchu", "/categories", "/dangnhap", "/chitiet" })
public class LayoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LayoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri = request.getRequestURI();
		String page = "";

		NewsDAO newsDAO = new NewsDAOImpl();
		CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
		
		if (uri.contains("trangchu")) {
			List<News> newsList = newsDAO.selectHome();
			request.setAttribute("newsList", newsList);
			page = "home.jsp";
		}

		if (uri.contains("categories")) {
			String id = request.getParameter("id"); // ví dụ: ?id=PL
			if (id != null) {
				List<News> newsList = newsDAO.selectByCategory(id);
				request.setAttribute("newsList", newsList);
				
				Categories categories = categoriesDAO.selectById(id);
				request.setAttribute("categoryName", categories.getName());
				page = "categories.jsp";
			}
		}

		if (uri.contains("dangnhap")) {
			page = "login.jsp";
		}

		if (uri.contains("chitiet")) {
			String id = request.getParameter("id");
			if (id != null) {
				newsDAO.updateViewCount(id);
				News news = newsDAO.selectById(id);
				request.setAttribute("news", news);

				// 🔹 Thêm tin đã xem vào session
				List<News> daXemList = (List<News>) request.getSession().getAttribute("daXemList");
				if (daXemList == null) {
					daXemList = new ArrayList<>();
				}

				// Kiểm tra trùng lặp (nếu người dùng xem lại tin cũ)
				boolean exists = daXemList.stream().anyMatch(n -> n.getId().equals(news.getId()));
				if (!exists) {
					daXemList.add(0, news); // thêm vào đầu danh sách
				}

				// Giới hạn tối đa 5 tin đã xem
				if (daXemList.size() > 5) {
					daXemList = daXemList.subList(0, 5);
				}

				// Lưu lại vào session
				request.getSession().setAttribute("daXemList", daXemList);

				// 🔹 Lấy tin cùng loại
				List<News> relatedNews = newsDAO.selectRelated(news.getCategoryId(), id);
				request.setAttribute("relatedNews", relatedNews);
				page = "chitiet.jsp";
			}
		}
		// 1. Lấy 5 bản tin xem nhiều
		List<News> xemNhieuList = newsDAO.selectTopView();
		request.setAttribute("xemNhieuList", xemNhieuList);

		// 2. Lấy 5 bản tin mới nhất
		List<News> moiNhatList = newsDAO.selectTopNew();
		request.setAttribute("moiNhatList", moiNhatList);

		// 3. Lấy 5 bản tin đã xem từ session
		List<News> daXemList = (List<News>) request.getSession().getAttribute("daXemList");
		if (daXemList == null) {
			daXemList = new ArrayList<>();
		}

		List<Categories> categories = categoriesDAO.selectAll();
		request.setAttribute("categories", categories);

		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/layout/homepage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
