package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByName(String name);
}
