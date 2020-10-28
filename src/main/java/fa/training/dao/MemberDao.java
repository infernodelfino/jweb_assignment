package fa.training.dao;

import java.util.List;

import fa.training.entities.Member;

public interface MemberDao {
	Member getMemberById(int id);

	List<Member> getAllMember();

	boolean addMember(Member member);

	boolean updateMember(Member member);

	boolean deleteMemberById(int id);
	
	Member getMemberByEmailAndPassword(String email, String password);
}
