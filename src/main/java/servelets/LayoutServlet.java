package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.NewsDAO;
import dao.NewsDAOImpl;
import entity.News;

/**
 * Servlet implementation class LayoutServlet
 */
@WebServlet({"/trangchu","/vanhoa","/phapluat","/thethao","/dangnhap","/chitiet"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri = request.getRequestURI();
		String page = "";
		
		if (uri.contains("trangchu")) {
			NewsDAO newsDAO = new NewsDAOImpl();
			List<News> newsList = newsDAO.selectHome();
			request.setAttribute("newsList", newsList);
			page = "home.jsp";
		}
		
		if (uri.contains("vanhoa")) {
			NewsDAO newsDAO = new NewsDAOImpl();
			List<News> newsList = newsDAO.selectVanHoa();
			request.setAttribute("newsList", newsList);
			page = "vanhoa.jsp";
		}
		
		if (uri.contains("phapluat")) {
			NewsDAO newsDAO = new NewsDAOImpl();
			List<News> newsList = newsDAO.selectPhapLuat();
			request.setAttribute("newsList", newsList);
			page = "phapluat.jsp";
		}
		
		if (uri.contains("thethao")) {
			NewsDAO newsDAO = new NewsDAOImpl();
			List<News> newsList = newsDAO.selectTheThao();
			request.setAttribute("newsList", newsList);
			page = "thethao.jsp";
		}
		
		if (uri.contains("dangnhap")) {
			page = "login.jsp";
		}
		
		if (uri.contains("chitiet")) {
			String id = request.getParameter("id");
			if (id != null) {
				NewsDAO newsDAO = new NewsDAOImpl();
				newsDAO.updateViewCount(id);
				News news = newsDAO.selectById(id);
				request.setAttribute("news", news);
				// Lấy tin cùng loại
				List<News> relatedNews = newsDAO.selectRelated(news.getCategoryId(), id);
				request.setAttribute("relatedNews", relatedNews);
				page = "chitiet.jsp";
			}
		}
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/layout/homepage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
