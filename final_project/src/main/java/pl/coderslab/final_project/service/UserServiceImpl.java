package pl.coderslab.final_project.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.user.Role;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.repository.RoleRepository;
import pl.coderslab.final_project.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepo.findOneByEmail(email);
	}

	@Override
	public User findByLogin(String login) {
		return userRepo.findOneByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole = roleRepo.findOneByName("ROLE_USER");
		user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(user);		
	}

}
