package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{

}
