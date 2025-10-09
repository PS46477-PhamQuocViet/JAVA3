package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LayoutServlet
 */
@WebServlet({"/trangchu","/vanhoa","/phapluat","/thethao","/dangnhap"})
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
			page = "home.jsp";
		}
		if (uri.contains("vanhoa")) {
			page = "vanhoa.jsp";
		}
		if (uri.contains("phapluat")) {
			page = "phapluat.jsp";
		}
		if (uri.contains("thethao")) {
			page = "thethao.jsp";
		}
		if (uri.contains("dangnhap")) {
			page = "login.jsp";
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
