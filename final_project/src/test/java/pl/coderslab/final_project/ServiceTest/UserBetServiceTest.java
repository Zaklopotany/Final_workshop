package pl.coderslab.final_project.ServiceTest;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.UserBetRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.service.UserBetService;
import pl.coderslab.final_project.service.UserBetServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserBetServiceTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Mock
	private BetRepository betRepo;
	@Mock
	private UserBetRepository userBetRepo;
	@Mock
	UserRepository userRepo;
	
	private UserBetService userBetService;
	
	@Before
	public void setUp() {
		userBetService = new UserBetServiceImpl( userRepo, null, userBetRepo, betRepo, null, null);
	}
	
	
	//TODO finish test
	@Test
	public void add_bet_to_user_basket() {
		//given
		
		//when
		when(userBetService.validateBetInsideBasket(1L, 1L)).thenReturn(true);
		//then
	}
	
}
