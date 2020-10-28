package fa.training.dao;

import java.util.List;
import java.util.Map;

import fa.training.entities.Content;

public interface ContentDao {
	Content getContentById(int id);

	List<Content> getAllContentToDoPagination(int memberId, int page);

	boolean addContent(Content content);

	boolean updateContent(Content content);

	boolean deleteContentById(int id);
	
	Map<Integer, Content> getAllContent(int memberId);

}
