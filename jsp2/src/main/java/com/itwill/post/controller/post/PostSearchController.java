package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.service.PostService;

/**
 * Servlet implementation class PostSearchController
 */

@WebServlet (name="postSearchController", urlPatterns = {"/post/search"})
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(PostUpdateController.class);
	
	private final PostService postService = PostService.getInstance();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet()");
		
		// t - 제목
		// c - 내용
		// tc - 제목 내용
		// a - 작성자
		
		String flag = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		log.info("flag = {}", flag);
		log.info("keyword = {}", keyword);
		
		List<Post> posts = new ArrayList<>();
		
		if (flag.equals("t")) {
			posts = postService.readByTitle(keyword);
		} 
		else if (flag.equals("c")) {
			posts = postService.readByContent(keyword);
		}
		else if (flag.equals("tc")) {
			posts = postService.readByTitleAndContent(keyword);
		}	
		else if (flag.equals("a")) {
			posts = postService.readByAuthor(keyword);
		}
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/WEB-INF/post/post.jsp").forward(request, response);

	}
	
}
