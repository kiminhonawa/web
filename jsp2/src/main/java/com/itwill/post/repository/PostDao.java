package com.itwill.post.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.datasource.HikariDataSourceUtil;
import com.itwill.post.model.Post;
import com.zaxxer.hikari.HikariDataSource;

// Repository(Persistence) Layer (저장소/영속성 계층)
// DB CRUD(Create, Read, Update, Delete) 작업
public class PostDao {
	// Slf4j 로깅 기능 사용:
	private static final Logger log = LoggerFactory.getLogger(PostDao.class);

	private static PostDao instance = null;

	private HikariDataSource ds;

	private PostDao() {
		ds = HikariDataSourceUtil.getInstance().getDataSource();
	}

	public static PostDao getInstance() {
		if (instance == null) {
			instance = new PostDao();
		}
		return instance;
	}

	// POSTS 테이블에서 전체 레코드를 id 내림차순으로 정렬해서 (최근 작성 포스트 먼저) 검색.
	private static final String SQL_SELECTE_ALL = "select * from POSTS order by ID desc";

	public List<Post> select() {
		List<Post> list = new ArrayList<>();

		log.info(SQL_SELECTE_ALL);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection(); // 풀에서 Connection 객체를 빌려옴.
			stmt = conn.prepareStatement(SQL_SELECTE_ALL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				// 테이블 컬럼 내용을 Post 타입 객체로 변환하고 리스트에 추가.
				Post post = recordToPost(rs);
				list.add(post);
			}
			log.info("list size = {}", list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close(); // 물리적인 접속 해제가 아니라, 풀에 반환!
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private Post recordToPost(ResultSet rs) throws SQLException {
		long id = rs.getLong("ID");
		String title = rs.getString("TITLE");
		String content = rs.getString("CONTENT");
		String author = rs.getString("AUTHOR");
		LocalDateTime created = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
		LocalDateTime modified = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();

		Post post = new Post(id, title, content, author, created, modified);

		return post;
	}

	private static final String SQL_INSERT = "insert into POSTS (TITLE, CONTENT, AUTHOR) values (?, ?, ?)";

	public int insert(Post post) {
		log.info("insert({})", post);
		log.info(SQL_INSERT);
		int result = 0; // executeUpdate() 결과를 저장할 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setString(3, post.getAuthor());

			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();

			}

		}

		return result;

	}

	private static final String SQL_SELECTE_BY_ID = "select * from posts where id = ?";

	public Post selectById(long id) {

		Post post = new Post();

		log.info(SQL_SELECTE_BY_ID);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection(); // 풀에서 Connection 객체를 빌려옴.
			stmt = conn.prepareStatement(SQL_SELECTE_BY_ID);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				// 테이블 컬럼 내용을 Post 타입 객체로 변환하고 리스트에 추가.
				post = recordToPost(rs);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return post;
	}

	private static final String SQL_DELETE_BY_ID = "delete from posts where id = ?";

	public int deleteById(long id) {
		log.info(SQL_DELETE_BY_ID);

		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE_BY_ID);
			stmt.setLong(1, id);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}

	private static final String SQL_UPDATE = "update posts set TITLE =? , CONTENT = ?, MODIFIED_TIME = sysdate where ID = ?";

	public int update(Post post) {
		log.info(SQL_UPDATE);

		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setLong(3, post.getId());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	private static final String SQL_SEARCH_BY_TITLE = "select * from posts where title like ?";

	public List<Post> readByTitle(String keyword) {
		List<Post> list = new ArrayList<>();

		log.info(SQL_SEARCH_BY_TITLE);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SEARCH_BY_TITLE);

			String key = "%" + keyword + "%";

			stmt.setString(1, key);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Post post = recordToPost(rs);
				list.add(post);
			}
			log.info("list size = {}", list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private static final String SQL_SEARCH_BY_CONTENT = "select * from posts where content like ?";

	public List<Post> readByContent(String keyword) {
		List<Post> list = new ArrayList<>();

		log.info(SQL_SEARCH_BY_CONTENT);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SEARCH_BY_CONTENT);

			String key = "%" + keyword + "%";

			stmt.setString(1, key);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Post post = recordToPost(rs);
				list.add(post);
			}
			log.info("list size = {}", list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	private static final String SQL_SEARCH_BY_TITLE_AND_CONTENT = "SELECT * FROM posts WHERE title LIKE ? OR content LIKE ?";
	public List<Post> readByTitleAndContent(String keyword) {
		List<Post> list = new ArrayList<>();
		log.info(SQL_SEARCH_BY_TITLE_AND_CONTENT);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SEARCH_BY_TITLE_AND_CONTENT);

			String key = "%" + keyword + "%";

			stmt.setString(1, key);
			stmt.setString(2, key);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Post post = recordToPost(rs);
				list.add(post);
			}
			log.info("list size = {}", list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	private static final String SQL_SEARCH_BY_AUTHOR = "SELECT * FROM posts WHERE author LIKE ?";
	public List<Post> readByAuthor(String keyword) {
		List<Post> list = new ArrayList<>();
		log.info(SQL_SEARCH_BY_AUTHOR);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SEARCH_BY_AUTHOR);

			String key = "%" + keyword + "%";

			stmt.setString(1, key);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Post post = recordToPost(rs);
				list.add(post);
			}
			log.info("list size = {}", list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
