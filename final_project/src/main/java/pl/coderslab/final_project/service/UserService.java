package pl.coderslab.final_project.service;

import pl.coderslab.final_project.entity.user.User;

public interface UserService {
	
	public User findByEmail(String email);
	public User findByLogin(String login);
	public void saveUser(User user);
}
