package com.medibuddy.webapi.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.account.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findByEmail(String email);
	
}
