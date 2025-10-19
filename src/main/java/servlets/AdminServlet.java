package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.CategoriesDAO;
import dao.CategoriesDAOImpl;
import dao.NewsDAO;
import dao.NewsDAOImpl;
import dao.UsersDAO;
import dao.UsersDAOImpl;
import entity.Categories;
import entity.News;
import entity.Users;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/trangchuadmin","/tintuc","/loaitin","/nguoidung","/newsletter"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri = request.getRequestURI();
		String page = "";
		
		NewsDAO newsDAO = new NewsDAOImpl();
		CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
		UsersDAO usersDAO = new UsersDAOImpl();
		
		if (uri.contains("trangchuadmin")) {
		    List<News> newsList = newsDAO.selectAll(); // lấy toàn bộ tin tức
		    request.setAttribute("newsList", newsList);
			page = "home.jsp";
		}
		if (uri.contains("tintuc")) {
			Users user = (Users) request.getSession().getAttribute("user");
		    List<News> list;
		    if (user != null && user.isRole()) {
		        list = newsDAO.selectAll();
		    } else {
		        list = newsDAO.selectByUser(user.getId());
		    }
		    request.setAttribute("list", list);
		    request.setAttribute("item", new News());
			page = "tintuc.jsp";
		}
		if (uri.contains("loaitin")) {
		    List<Categories> list = categoriesDAO.selectAll();
		    request.setAttribute("list", list);
		    request.setAttribute("item", new Categories());
			page = "loaitin.jsp";
		}
		if (uri.contains("nguoidung")) {
		    List<Users> list = usersDAO.selectAll();
		    request.setAttribute("list", list);
		    request.setAttribute("item", new Users());
			page = "nguoidung.jsp";
		}
		if (uri.contains("newsletter")) {
			page = "newsletter.jsp";
		}
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/admin/homeadmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
