package com.skilldistillery.bilingualbuddies.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.bilingualbuddies.entities.Comment;
import com.skilldistillery.bilingualbuddies.entities.User;
import com.skilldistillery.bilingualbuddies.services.CommentService;
import com.skilldistillery.bilingualbuddies.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;

	@GetMapping(path = "posts/{id}/comments")
	public List<Comment> listComments(@PathVariable int id) {
		List<Comment> comments = commentService.findAllComments(id);

		return comments;
	}

	@GetMapping("comments/{id}")
	public Comment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return commentService.findById(id);

	}

	@PostMapping("posts/{id}/comments")
	public Comment create(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody Comment comment,
			@PathVariable Integer id) {
		try {
			User user = userService.show(principal.getName());
			comment.setUser(user);
			commentService.createComment(id, comment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return null;
	}

	@DeleteMapping("posts/{id}/comments/{cid}")
	public void delete(HttpServletRequest req, HttpServletResponse res, @PathVariable Comment cid) {
		try {
			commentService.deleteCommentById(cid);
			res.setStatus(204);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
