package fa.training.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.ContentDao;
import fa.training.dao.ContentDaoImpl;
import fa.training.dao.MemberDao;
import fa.training.dao.MemberDaoImpl;
import fa.training.entities.Content;
import fa.training.entities.Member;

/**
 * Servlet implementation class ContentServlet
 */
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao = new MemberDaoImpl();
	private ContentDao contentDao = new ContentDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {

//		/content?action=view&page=1
		case "view":
			caseView(request, response);
			break;

//		/content?action=editOrCreate&id=1
		case "editOrCreate":
			caseEditOrCreateGet(request, response);
			break;

//		/content?action=delete&id=1
		case "delete":
			caseDelete(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {

//		/content?action=createOrUpdate		
		case "create":
			caseCreate(request, response);
			break;

//		/content?action=editOrCreate&id=1
		case "editOrCreate":
			caseEditOrCreatePost(request, response);
			break;

		}
	}

	/**
	 * Case action = view (getAllContent())
	 * 
	 * @param request
	 * @param response
	 */
	private void caseView(HttpServletRequest request, HttpServletResponse response) {
		Integer memberId = (Integer) request.getSession().getAttribute("id");
		String email = (String) request.getSession().getAttribute("email");
		Integer pageNumber = Integer.valueOf(request.getParameter("page"));

		List<Content> contentPaginationList = contentDao.getAllContentToDoPagination(memberId, pageNumber);

		int numberOfPage = contentDao.getAllContent(memberId).size();
		if (numberOfPage % 5 > 0) {
			numberOfPage = numberOfPage / 5 + 1;
		} else {
			numberOfPage = numberOfPage / 5;
		}

		request.setAttribute("contentList", contentPaginationList);
		request.setAttribute("email", email);
		request.setAttribute("numberOfPage", numberOfPage);
		try {
			request.getRequestDispatcher("/html/view-content.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Case action = create
	 * 
	 * @param request
	 * @param response
	 */
	private void caseCreate(HttpServletRequest request, HttpServletResponse response) {
		Integer memberId = (Integer) request.getSession().getAttribute("id");
		Member member = memberDao.getMemberById(memberId);

		String title = request.getParameter("title");
		String brief = request.getParameter("brief");
		String contentInput = request.getParameter("content");

		Content content = new Content();
		content.setTitle(title);
		content.setBrief(brief);
		content.setContent(contentInput);
		content.setMember(member);

		contentDao.addContent(content);

		try {
			response.sendRedirect(request.getContextPath() + "/content?action=view");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Case action = editOrCreate /content?action=editOrCreate&id=1 (GET)
	 * 
	 * @param request
	 * @param response
	 */
	private void caseEditOrCreateGet(HttpServletRequest request, HttpServletResponse response) {
		String contentId = request.getParameter("id");

//		create new
		if (null == contentId) {
			try {
				request.getRequestDispatcher("/html/form-content.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

//		fetch data out to update
		} else {
			Content currentContent = contentDao.getContentById(Integer.valueOf(contentId));
			request.setAttribute("content", currentContent);
			try {
				request.getRequestDispatcher("/html/form-content.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Case action = editOrCreate /content?action=editOrCreate&id=1 (POST)
	 * 
	 * @param request
	 * @param response
	 */
	private void caseEditOrCreatePost(HttpServletRequest request, HttpServletResponse response) {
		String contentId = request.getParameter("id");
		boolean result = true;
		String message = null;

//		create new
		if ("" == contentId) {
			Integer memberId = (Integer) request.getSession().getAttribute("id");
			Member member = memberDao.getMemberById(memberId);

			String titleInput = request.getParameter("title");
			String briefInput = request.getParameter("brief");
			String contentInput = request.getParameter("content");

			Content content = new Content();
			content.setTitle(titleInput);
			content.setBrief(briefInput);
			content.setContent(contentInput);
			content.setCreateDate(LocalDate.now());
			content.setUpdateTime(LocalDate.now());
			content.setMember(member);

			result = contentDao.addContent(content);
			if (result) {
				try {
					response.sendRedirect(request.getContextPath() + "/content?action=view&page=1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				message = "Failed";
				request.setAttribute("content", content);
				request.setAttribute("message", message);
				try {
					request.getRequestDispatcher("/html/form-content.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}

//		fetch data out to update
		} else {
			Content currentContent = contentDao.getContentById(Integer.valueOf(contentId));

			String titleInput = request.getParameter("title");
			String briefInput = request.getParameter("brief");
			String contentInput = request.getParameter("content");

			currentContent.setTitle(titleInput);
			currentContent.setBrief(briefInput);
			currentContent.setContent(contentInput);
			currentContent.setUpdateTime(LocalDate.now());
			currentContent.setCreateDate(currentContent.getCreateDate());

			result = contentDao.updateContent(currentContent);

			if (result) {
				try {
					response.sendRedirect(request.getContextPath() + "/content?action=view&page=1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				message = "Update failed";
				request.setAttribute("content", currentContent);
				request.setAttribute("message", message);
				try {
					request.getRequestDispatcher("/html/form-content.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Case delete (/content?action=delete&
	 * 
	 * @param request
	 * @param response
	 */
	private void caseDelete(HttpServletRequest request, HttpServletResponse response) {
		Integer contentId = Integer.valueOf(request.getParameter("id"));
		Integer memberId = (Integer) request.getSession().getAttribute("id");

//		tìm vị trí của content trong contentList
		int indexOfContentInContentList = contentDao.getAllContent(memberId).keySet().stream()
				.collect(Collectors.toList()).indexOf(contentId) + 1;

//		xóa
		contentDao.deleteContentById(contentId);
		
//		tìm số trang hiện tại của content sắp bị xóa
		int currentPageOfContent = indexOfContentInContentList / 5;
		
//		lấy số trang thực tế của content hiện tại
		if (indexOfContentInContentList % 5 > 0) {
			currentPageOfContent+=1;
		}

//		tìm số bản ghi hiện tại trong page của content đã bị xóa
		int numberOfContentsInCurrentPage = contentDao.getAllContentToDoPagination(memberId, currentPageOfContent)
				.size();

//		nếu không có bản ghi nào ở trang hiện tại thì lùi về 1 trang
		if (numberOfContentsInCurrentPage == 0) {
			try {
				currentPageOfContent -= 1;
				response.sendRedirect(
						request.getContextPath() + "/content?action=view&page=" + currentPageOfContent);
			} catch (IOException e) {
				e.printStackTrace();
			}

//		nếu có thì giữ nguyên trang
		} else {
			try {
				response.sendRedirect(
						request.getContextPath() + "/content?action=view&page=" + currentPageOfContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
