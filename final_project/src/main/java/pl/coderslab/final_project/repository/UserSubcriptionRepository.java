package pl.coderslab.final_project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.UserSubscription;

public interface UserSubcriptionRepository extends JpaRepository<UserSubscription, Long>{

	List<UserSubscription> findByUsersIn(Set<User> users);
	
	@Query("Select s.users from UserSubscription s where s.id = ?1 ")
	Set<User> getAllUsersForSub(Long subId); 
}
