package fa.training.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fa.training.entities.Content;
import fa.training.entities.Member;
import fa.training.utils.ConnectionUtils;

public class ContentDaoImpl implements ContentDao {
	private SessionFactory sessionFactory = null;
	private Session session = null;
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Content getContentById(int id) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		Content content = new Content();
		try {
			session.beginTransaction();
			content = (Content) session.get(Content.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return content;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContentToDoPagination(int memberId, int page) {
		Member member = memberDao.getMemberById(memberId);
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		List<Content> contentList = new ArrayList<>();
		try {
			session.beginTransaction();
			
			String sql = "FROM Content AS c WHERE c.member = :member";
			Query query = session.createQuery(sql);
			
			query.setParameter("member", member);
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(page * 5);
			
			contentList = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return contentList;
	}

	@Override
	public boolean addContent(Content content) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		boolean result = false;
		try {
			session.beginTransaction();
			session.save(content);
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
	public boolean updateContent(Content content) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		boolean result = false;
		try {
			session.beginTransaction();
			session.evict(content);
			session.update(content);
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
	public boolean deleteContentById(int id) {
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		boolean result = false;
		try {
			session.beginTransaction();
			session.delete(session.get(Content.class, id));
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Content> getAllContent(int memberId) {
		Member member = memberDao.getMemberById(memberId);
		sessionFactory = ConnectionUtils.getSessionFactory();
		session = sessionFactory.openSession();
		
		Map<Integer, Content> mapList = new LinkedHashMap<Integer, Content>();
		List<Content> contentList = new ArrayList<>();
		try {
			session.beginTransaction();
			
			String sql = "FROM Content AS c WHERE c.member = :member";
			Query query = session.createQuery(sql);
			
			query.setParameter("member", member);
			
			contentList = query.list();
			
			for (int i = 1; i <= contentList.size(); i++) {
				mapList.put(contentList.get(i-1).getId(), contentList.get(i-1));
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return mapList;
	}

}
