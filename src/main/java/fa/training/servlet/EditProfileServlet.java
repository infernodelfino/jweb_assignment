package fa.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.MemberDao;
import fa.training.dao.MemberDaoImpl;
import fa.training.entities.Member;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get user information
		Integer id = (Integer) request.getSession().getAttribute("id");
		
		// get member by Id
		Member member = memberDao.getMemberById(id); 
		
		// add user to request
		request.setAttribute("member", member);
		
		// forward request to view
		request.getRequestDispatcher("/html/edit-profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		get data from view's form
		Integer id = Integer.valueOf(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		String message = null;

		Member member = memberDao.getMemberById(id);
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setPhone(phone);
		member.setDescription(description);

		boolean result = memberDao.updateMember(member);

		// update session attribute member
		if (result) {
			message = "Successfully updated";
			request.setAttribute("message", message);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/html/edit-profile.jsp").forward(request, response);
		} else {
			message = "Update failed";
			request.setAttribute("message", message);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/html/edit-profile.jsp").forward(request, response);
		}
	}

}
