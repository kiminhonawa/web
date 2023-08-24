package com.itwill.post.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.repository.PostDao;

// Service(Business) layer (서비스/비즈니스 계층)
// Repository 계층의 메서드들을 사용해서 서비를 만듦. 
public class PostService {
	// Slf4j 로깅 기능 사용:
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	
	private static PostService instance = null;
	
	// Service 계층에서는 Repository 계층의 메서드 사용:
	private final PostDao postDao = PostDao.getInstance();
	
	private PostService() {}
	
	public static PostService getInstance() {
		if (instance == null) {
			instance = new PostService();
		}
		
		return instance;
	}
	
	public List<Post> read() {
		log.info("read()");
		
		return postDao.select();
	}

	public int create(Post post) {
		log.info("create({})", post);
		return postDao.insert(post);
	}

	public Post readById(long id) {
		log.info("readyById({})", id);
		return postDao.selectById(id);
	}

	public int deleteById(long id) {
		log.info("deleteById({})", id);
		return postDao.deleteById(id);
	}

	public int update(Post post) {
		log.info("update({})", post);
		return postDao.update(post);
	}

	public List<Post> readByTitle(String keyword) {
		log.info("readByTitle({})", keyword);
		return postDao.readByTitle(keyword);
	}

	public List<Post> readByContent(String keyword) {
		log.info("readByContent({})", keyword);
		return postDao.readByContent(keyword);
	}

	public List<Post> readByTitleAndContent(String keyword) {
		log.info("readByTitleAndContent({})", keyword);
		return postDao.readByTitleAndContent(keyword);
	}

	public List<Post> readByAuthor(String keyword) {
		log.info("readByAuthor({})", keyword);
		return postDao.readByAuthor(keyword);
	}

	
	
}
