package com.skilldistillery.bilingualbuddies.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.bilingualbuddies.entities.Language;
import com.skilldistillery.bilingualbuddies.entities.User;
import com.skilldistillery.bilingualbuddies.repositories.LangeuageRepository;
import com.skilldistillery.bilingualbuddies.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private LangeuageRepository langRepo;

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

//	findById and show do the same thing just with different parameters
	@Override
	public User findById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public User show(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	@Override
	public User create(User user) {
		User sendMe = new User();
		user.setRole("user");
		sendMe = userRepo.saveAndFlush(user);
		return sendMe;
	}

	@Override
	public User update(String username, User user) {
		User output = show(username);
		if (output != null) {
			output.setFirstName(user.getFirstName());
			output.setLastName(user.getLastName());
			output.setEmail(user.getEmail());
			output.setPassword(user.getPassword());
			output.setUsername(user.getUsername());
			output.setEnabled(user.isEnabled());
			output.setImageUrl(user.getImageUrl());
			output.setBio(user.getBio());
			
			if (output.getLanguages().size() < user.getLanguages().size()) {
				Language lang = user.getLanguages().get(user.getLanguages().size() - 1);
				Optional<Language> opt = langRepo.findById(lang.getId());
				if (opt.isPresent()) {
					lang = opt.get();
				}
				output.addLanguage(lang);				
			}
			
			if (output.getLanguages().size() > user.getLanguages().size()) {
				List<Language> languages = new ArrayList<>(output.getLanguages());
				for (Language lang : languages) {
					if (!user.getLanguages().contains(lang)) {
						output.removeLanguage(lang);
					}
				}
			}
			
			output = userRepo.saveAndFlush(output);
		}
		return output;
	}

	@Override
	public boolean destroy(String username) {
		User deleteMe = show(username);
		boolean deleted = false;
		if (deleteMe != null) {
			deleteMe.setEnabled(false);
			deleted = true;
			userRepo.saveAndFlush(deleteMe);
		}
		return deleted;
	}

}
