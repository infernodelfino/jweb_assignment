package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fa.training.entities.Member;
import fa.training.utils.ConnectionUtils;

public class MemberDaoImpl implements MemberDao {
	private SessionFactory sessionFactory = null;
	private Session session = null;

	@Override
	public Member getMemberById(int id) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		Member member = new Member();
		try {
			session.beginTransaction();
			member = (Member) session.get(Member.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return member;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMember() {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		List<Member> memberList = new ArrayList<>();
		try {
			session.beginTransaction();
			memberList = session.createQuery("FROM Member").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return memberList;
	}

	@Override
	public boolean addMember(Member member) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		boolean result = false;
		try {
			session.beginTransaction();
			session.save(member);
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return result;
	}

	@Override
	public boolean updateMember(Member member) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		
		Member existingMember = null;
		boolean result = false;
		
		try {
			session.beginTransaction();
			existingMember = (Member) session.get(Member.class, member.getId());
			if (existingMember != null) {
				session.evict(session.get(Member.class, member.getId()));
				session.update(member);
				session.getTransaction().commit();
				result = true;
			} else {
				return result;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return result;
	}

	@Override
	public boolean deleteMemberById(int id) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		boolean result = false;
		try {
			session.beginTransaction();
			session.delete(session.get(Member.class, id));
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return result;
	}

	@Override
	public Member getMemberByEmailAndPassword(String email, String password) {
		Member member = new Member();

		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		String sql = "FROM Member AS m WHERE m.email = :email AND m.password = :password";
		Query query = session.createQuery(sql);
		query.setString("email", email);
		query.setString("password", password);

		@SuppressWarnings("unchecked")
		List<Member> result = query.list();

		if (result.size() >= 1) {
			return result.get(0);
		}

		return member;
	}

}
