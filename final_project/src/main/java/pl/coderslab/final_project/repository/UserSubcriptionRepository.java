package pl.coderslab.final_project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.UserSubscription;

public interface UserSubcriptionRepository extends JpaRepository<UserSubscription, Long>{

	List<UserSubscription> findByUsersIn(Set<User> users);
}
