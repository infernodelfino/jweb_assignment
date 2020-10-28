package fa.training.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.entities.Content;

public class ContentDaoTest {
	private static MemberDao memberDao = new MemberDaoImpl();
	private static ContentDao contentDao = new ContentDaoImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetContentById() {
		Content content = contentDao.getContentById(1);
		assertEquals(content.getId(), 1);
	}

	@Test
	public void testGetAllContent() {
		int memberId = 5;
		List<Content> contentList = new ArrayList<>();
		
		contentList = contentDao.getAllContentToDoPagination(memberId, 2);
		for (Content element:contentList) {
			System.out.println(element.getId() + "::" + element.getTitle() + "::" + element.getBrief());
		}
//		assertEquals(contentList.get(0).getBrief(), "111");
//		assertEquals(contentList.size(), 2);
	}

	@Test
	public void testAddContent() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateContent() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteContentById() {
		fail("Not yet implemented");
	}

}
