package com.medibuddy.webapi.repository.account;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.account.UserFeedback;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, UUID> {

	@Query("SELECT u FROM UserFeedback u WHERE u.user.username = ?1")
	Page<UserFeedback> findByUsername(String username, Pageable pageable);

}
