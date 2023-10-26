package repository;

import entity.Account;
import org.springframework.stereotype.Repository;

@Repository("AccountRepository")
public interface AccountRepository extends BaseRepository<Account, Long> {

}
