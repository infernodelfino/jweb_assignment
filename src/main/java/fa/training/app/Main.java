package fa.training.app;

import fa.training.dao.MemberDao;

import fa.training.dao.MemberDaoImpl;
import fa.training.entities.Member;

public class Main {
	public static void main(String[] args) {
		MemberDao memberDaoImpl = new MemberDaoImpl();
		Member member2 = new Member();
		member2.setId(2);
		member2.setUserName("user2");
		member2.setPassword("1232");
		member2.setEmail("user2@gmail.com");
		memberDaoImpl.addMember(member2);
	}
}
