package fa.training.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.MemberDao;
import fa.training.dao.MemberDaoImpl;
import fa.training.entities.Member;
import fa.training.service.MemberService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member member = new Member();

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("re-password");
		String message = null;

		member.setUserName(username);
		member.setEmail(email);
		member.setPassword(password);

		if (memberDao.addMember(member)) {
			message = "Successfully created an account";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/html/register.jsp").forward(request, response);
		} else {
			message = "Failed to create an account";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/html/register.jsp").forward(request, response);
		}

	}

}
