package com.example.usuario.repository;

import com.example.usuario.entity.User;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends BaseRepository<User, Long> {
}
