package com.massal.twittertime.twittertime.repo;

import com.massal.twittertime.twittertime.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
