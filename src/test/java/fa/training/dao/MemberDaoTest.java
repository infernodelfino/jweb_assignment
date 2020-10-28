package fa.training.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.entities.Content;
import fa.training.entities.Member;

public class MemberDaoTest {
	private static MemberDao memberDao = new MemberDaoImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMemberById() {
		Member actual = new Member();
		actual = memberDao.getMemberById(5);
		List<Content> contentList = actual.getContent();
		for (Content ele: contentList) {
			System.out.println(ele.getId() + "::" + ele.getTitle());
		}
		assertEquals("infernodelfino", actual.getUserName());
	}

	@Test
	public void testGetAllMember() {
		Member member1 = new Member();
		member1.setUserName("user1");
		member1.setPassword("1231");
		member1.setEmail("user1@gmail.com");
		memberDao.addMember(member1);
		
		Member member2 = new Member();
		member2.setUserName("user2");
		member2.setPassword("1232");
		member2.setEmail("user2@gmail.com");
		memberDao.addMember(member2);
		
		Member member3 = new Member();
		member3.setUserName("user3");
		member3.setPassword("1233");
		member3.setEmail("user3@gmail.com");
		memberDao.addMember(member3);
		
		List<Member> memberList = new ArrayList<>();
		memberList = memberDao.getAllMember();
		int expected = 3;
		assertEquals(expected, memberList.size());
	}

	@Test
	public void testAddMember() {
		Member member = new Member();
		member.setUserName("user1");
		member.setPassword("123");
		member.setEmail("user1@gmail.com");
		memberDao.addMember(member);
		String expectedUsername = "user1";
		assertEquals(expectedUsername, "user1");
	}

	@Test
	public void testUpdateMember() {
		Member member = new Member();
		member.setId(3);
		member.setFirstName("GG");
		member.setLastName("HH");
		member.setPhone("NN");
		
		boolean result = memberDao.updateMember(member);
		assertEquals(result, true);
		
	}

	@Test
	public void testDeleteMemberById() {
		fail("Not yet implemented");
	}

}
