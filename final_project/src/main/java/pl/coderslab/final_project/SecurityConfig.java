package pl.coderslab.final_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public SpringDataUserDetailsService customUserDetailsService() {
		return new SpringDataUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
		// .dataSource(dataSource).passwordEncoder(passwordEncoder());
		
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		
		/*
		 * withUser("admin") .password(passwordEncoder().encode("admin")).roles("USER");
		 */
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * http.exceptionHandling() .authenticationEntryPoint(new
		 * Http403ForbiddenEntryPoint());
		 */
		
		http.authorizeRequests()
		.antMatchers("/all").permitAll()
		.antMatchers("/moderator").hasAnyRole("MODERATOR")
		.antMatchers("/remove").hasAnyRole("MODERATOR", "ADMIN")
		.antMatchers("/users").hasAnyRole("USER")
		.antMatchers("/register").authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/user/home").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll().and()
		.exceptionHandling().accessDeniedPage("/403");
		// .and()
		// .addFilterAt(filter(), BasicAuthenticationFilter.class)
		// .csrf()
		// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
	}
	
	/*
	  @Override public void configure(AuthenticationManagerBuilder auth) throws
	  Exception { auth.inMemoryAuthentication()
	  .withUser("user").password("user").roles("USER").and()
	  .withUser("admin").password("admin").roles("ADMIN")
	  .and().withUser("moderator").password("moderator").roles("MODERATOR"); }
	 */
	// @Bean
	// public JdbcUserDetailsManager userDetailsManager() {
	// JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
	// manager.setDataSource(dataSource);
	// manager.setUsersByUsernameQuery(usersQuery);
	// manager.setAuthoritiesByUsernameQuery(rolesQuery);
	// manager.setRolePrefix("ROLE_");
	// return manager;
	// }

	/*
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * builder) throws Exception {
	 * 
	 * builder.userDetailsService(userDetailsManager()); }
	 */

	// @Value("${spring.queries.users-query}")
	// private String usersQuery;
	//
	// @Value("${spring.queries.roles-query}")
	// private String rolesQuery;


	// @Autowired
	// DataSource dataSource;


	/*
	 * private OAuth2ClientContext oauth2ClientContext; private
	 * AuthorizationCodeResourceDetails authorizationCodeResourceDetails; private
	 * ResourceServerProperties resourceServerProperties;
	 * 
	 * 
	 * @Autowired public void setOauth2ClientContext(OAuth2ClientContext
	 * oauth2ClientContext) { this.oauth2ClientContext = oauth2ClientContext; }
	 * 
	 * @Autowired public void
	 * setAuthorizationCodeResourceDetails(AuthorizationCodeResourceDetails
	 * authorizationCodeResourceDetails) { this.authorizationCodeResourceDetails =
	 * authorizationCodeResourceDetails; }
	 * 
	 * @Autowired public void setResourceServerProperties(ResourceServerProperties
	 * resourceServerProperties) { this.resourceServerProperties =
	 * resourceServerProperties; }
	 * 
	 * 
	 * 
	 * This method for creating filter for OAuth authentication. private
	 * OAuth2ClientAuthenticationProcessingFilter filter() { //Creating the filter
	 * for "/google/login" url OAuth2ClientAuthenticationProcessingFilter
	 * oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
	 * "/google/login");
	 * 
	 * //Creating the rest template for getting connected with OAuth service. //The
	 * configuration parameters will inject while creating the bean.
	 * OAuth2RestTemplate oAuth2RestTemplate = new
	 * OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext);
	 * oAuth2Filter.setRestTemplate(oAuth2RestTemplate);
	 * 
	 * // Setting the token service. It will help for getting the token and // user
	 * details from the OAuth Service. oAuth2Filter.setTokenServices(new
	 * UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
	 * resourceServerProperties.getClientId()));
	 * 
	 * return oAuth2Filter; }
	 */
}
