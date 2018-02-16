package pl.coderslab.final_project.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.user.Role;
import pl.coderslab.final_project.entity.user.User;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRole()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new CurrentUser(user.getLogin(), user.getPassword(), grantedAuthorities, user.getId());
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

}
