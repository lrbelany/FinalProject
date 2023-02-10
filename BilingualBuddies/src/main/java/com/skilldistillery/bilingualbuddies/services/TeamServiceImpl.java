package com.skilldistillery.bilingualbuddies.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.bilingualbuddies.entities.Team;
import com.skilldistillery.bilingualbuddies.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository teamRepo;

	@Override
	public Team findById(int id) {
		Optional<Team> teamOpt = teamRepo.findById(id);
		Team team = null;
		if(teamOpt.isPresent()) {
			team = teamOpt.get();
		}
		return team;
	}

	@Override
	public List<Team> findAllTeams() {
		
		return teamRepo.findAll();
	}

	@Override
	public Team createTeam(Team team) {
		team.setEnabled(true);
		teamRepo.saveAndFlush(team);
		return team;
	}

	@Override
	public Team update(int id, Team team) {
		Team teamUpdate = findById(id);
		if(teamUpdate != null) {
			teamUpdate.setName(team.getName());
			teamUpdate.setContent(team.getContent());
			teamUpdate.setImageUrl(team.getImageUrl());
			
			teamUpdate = teamRepo.saveAndFlush(teamUpdate);
		}
		return teamUpdate;
	}

	@Override
	public boolean destroy(int id) {
		Team deleteMe = findById(id);
		boolean deleted = false;
		if(deleteMe != null) {
			deleteMe.setEnabled(false);
			deleted = true;
			teamRepo.saveAndFlush(deleteMe);
		}
		return deleted;
	}

}