package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		
		if (uri.contains("trangchuadmin")) {
			page = "home.jsp";
		}
		if (uri.contains("tintuc")) {
			page = "tintuc.jsp";
		}
		if (uri.contains("loaitin")) {
			page = "loaitin.jsp";
		}
		if (uri.contains("nguoidung")) {
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
