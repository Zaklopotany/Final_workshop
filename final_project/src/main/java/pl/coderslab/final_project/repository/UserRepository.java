package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOneByEmail(String email);
	User findOneByLogin(String login);
}
