package pl.coderslab.final_project.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.user.Role;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.repository.RoleRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.repository.WalletRepository;
@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	private final WalletRepository walletRepo;
	
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder passwordEncoder, WalletRepository walletRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.walletRepo = walletRepo;
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
		Role userRole = roleRepo.findOneByName("ROLE_USER");
		Wallet wallet = new Wallet();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(user);
		//creating wallet
		wallet.setOwner(user);
		wallet.setAccState(0.0);
		walletRepo.save(wallet);
		
	}

}
