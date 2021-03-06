package newbee.jsh.security.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import newbee.jsh.security.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Account findByEmail(final String email);

}
