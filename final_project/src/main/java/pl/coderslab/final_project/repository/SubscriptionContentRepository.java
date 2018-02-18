package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.SubscriptionContent;

public interface SubscriptionContentRepository extends JpaRepository<SubscriptionContent, Long>{
	
	List<SubscriptionContent> findAllBySent(boolean sent);
}
