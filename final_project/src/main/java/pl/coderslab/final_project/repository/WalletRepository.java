package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
	Wallet findOneByOwnerId(Long id);
}
