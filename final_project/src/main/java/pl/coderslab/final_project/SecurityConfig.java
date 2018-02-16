package pl.coderslab.final_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.coderslab.final_project.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("pl.coderslab")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	SpringDataUserDetailsService myUserDetails;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(myUserDetails);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/moderator").hasAnyRole("MODERATOR")
		.antMatchers("/remove").hasAnyRole("MODERATOR", "ADMIN")
		.antMatchers("/users").hasAnyRole("USER")
		.antMatchers("/register").authenticated()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/user/home").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedPage("/403");

	}


}
