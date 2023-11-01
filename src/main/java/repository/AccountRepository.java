package repository;

import entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("AccountRepository")
public interface AccountRepository extends BaseRepository<Account, Long> {
    @Query( "UPDATE Account e " +
            "SET e.isBanned = 1 " +
            "WHERE e.id = :id")
    void banAccount(@Param("id")Long id);

    @Query( "UPDATE Account e " +
            "SET e.isBanned = 0 " +
            "WHERE e.id = :id")
    void unbanAccount(@Param("id")Long id);
}
