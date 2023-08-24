package com.itwill.spring2.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml"}
		)
public class RepositoryTest {
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void testDelete() {
		int result = postRepository.deleteById(44);
		Assertions.assertEquals(1, result);
	}
	
	
	//@Test
	public void testUpdate() {
		Post post = Post.builder().id(14).title("업데이트 test").content("두가자이잉").build();
		
		int result = postRepository.updateTitleAndContent(post);
		Assertions.assertEquals(1, result);
	}
	
	
	
	//@Test
	public void testSelectOrderByIdDesc() {
		List<Post> list = postRepository.selectOrderByIdDesc();
		for (Post p : list) {
			log.info(p.toString());
		}
	}
	
	//@Test
	public void testSelectById() {
		Post result = postRepository.selectById(14);
		Assertions.assertNotNull(result);
		log.info(result.toString());
		
		result = postRepository.selectById(-1);
		Assertions.assertNull(result);
	}
	
	//@Test
	public void testPostRepository() {
		Assertions.assertNotNull(postRepository);
		log.info("postRepository = {}", postRepository);
		
		Post post = Post.builder()
				.title("MyBatis 테스트")
				.content("MyBatis 이용한 insert ")
				.author("admin")
				.build();
		log.info(post.toString());
		
		int result = postRepository.insert(post);
		Assertions.assertEquals(1, result);
		log.info("result = {}", result);
	}

}
