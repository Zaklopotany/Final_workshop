package pl.coderslab.final_project.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class CurrentUser extends User {
	private final long userId;

	public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			long userId) {
		super(username, password, authorities);
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
}
