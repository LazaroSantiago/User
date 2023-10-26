package repository;

import entity.User;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends BaseRepository<User, Long> {
}
