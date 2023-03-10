package com.skilldistillery.bilingualbuddies.services;

import java.util.List;

import com.skilldistillery.bilingualbuddies.entities.Team;
import com.skilldistillery.bilingualbuddies.entities.User;

public interface TeamService {

	public Team findById(int id);

	public List<Team> findAllTeams();

	public Team createTeam(Team team, String username);

	public Team update(int id, Team team, User user);

	public boolean destroy(int id);

	public Team finByUser(String username);

}
