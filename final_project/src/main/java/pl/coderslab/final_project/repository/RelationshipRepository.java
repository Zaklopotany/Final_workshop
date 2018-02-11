package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.Relationship;
import pl.coderslab.final_project.entity.user.RelationshipKey;

public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipKey>{

}
