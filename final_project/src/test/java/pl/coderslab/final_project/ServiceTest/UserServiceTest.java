package pl.coderslab.final_project.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import pl.coderslab.final_project.entity.user.Role;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.repository.RoleRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.repository.WalletRepository;
import pl.coderslab.final_project.service.UserService;
import pl.coderslab.final_project.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

	@Autowired
	TestEntityManager entityManager;
	@Autowired
	WalletRepository walletRepo;
	
	@Mock
	BCryptPasswordEncoder passwordEncoder;
	@Mock
	UserRepository userRepo;
	@Mock
	RoleRepository roleRepo;
	
	private User user = new User();
	private Role role = new Role();
	
	private UserService userService;
	
	@Before
	public void setUp() {
		userService = new UserServiceImpl(userRepo, roleRepo, passwordEncoder, walletRepo);
		role.setName("ROLE_USER");
		role = entityManager.persistAndFlush(role);
		user = User.builder().email("email@wp.pl").login("login").role(new HashSet<>(Arrays.asList(role))).build();
	}
	
	
	//test user and new wallet creation with account state = 0
	@Test
	public void test_save_user_new_wallet_creation() {
		//given
		Wallet wallet = new Wallet();
		List<Wallet> walletList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		//when
		when(roleRepo.findOneByName("ROLE_USER")).thenReturn(role);
		when(passwordEncoder.encode(user.getPassword())).thenReturn("12345678911");
		when(userRepo.save(user)).thenReturn(entityManager.persistAndFlush(user));
		
		userService.saveUser(user);
		
		wallet = walletRepo.findOneByOwnerId(user.getId());
		walletList = walletRepo.findAll();
		userList.add(user);
		//then
		assertEquals(1, walletList.size());
		assertThat(user.getId()).isGreaterThan(0L);
		assertEquals(user.getId(), walletRepo.findOneByOwnerId(user.getId()).getOwner().getId());
		assertThat(walletRepo.findOneByOwnerId(user.getId()).getAccState()).isEqualTo(0.0);
		
	}
	
}
