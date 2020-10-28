package fa.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.MemberDao;
import fa.training.dao.MemberDaoImpl;
import fa.training.entities.Member;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/html/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = null;
		
//		check checkbox có tick không? Có tick thì lưu lại ID pass vào Cookie
		
		Cookie emailCookie = new Cookie("email", email);
		Cookie passwordCookie = new Cookie("password", password);
		
		emailCookie.setMaxAge(60*24*24);
		passwordCookie.setMaxAge(60*24*24);
		
		response.addCookie(emailCookie);
		response.addCookie(passwordCookie);

		Member member = memberDao.getMemberByEmailAndPassword(email, password);
		if (member.getId() > 0) {
//		if authentication passes
			request.getSession().setAttribute("id", member.getId());
			request.getSession().setAttribute("email", member.getEmail());
			response.sendRedirect(request.getContextPath() + "/edit-profile");
		} else {
//			if authentication failed
			message = "Invalid login information";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}
	}

}
