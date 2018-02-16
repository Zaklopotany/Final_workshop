package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.entity.user.WalletHistory;

public interface WallethistoryRepository extends JpaRepository<WalletHistory, Long>{
	List<WalletHistory> findByWalletId(Long id);
}
