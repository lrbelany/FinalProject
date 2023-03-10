package com.skilldistillery.bilingualbuddies.services;

import java.util.List;

import com.skilldistillery.bilingualbuddies.entities.Post;

public interface PostService {
	
	public List<Post> index();

	public Post findById(int postId);

	public List<Post> findByUserId(int userId);
	
//	public Post show(String postName);

	public Post create(Post post);

	public Post update(int postId, Post postUpdates);

	public boolean destroy(int postId);

}
