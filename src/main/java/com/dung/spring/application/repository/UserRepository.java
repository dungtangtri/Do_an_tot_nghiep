package com.dung.spring.application.repository;

import java.util.List;
import java.util.Optional;

import com.dung.spring.application.models.User;
import com.dung.spring.application.payload.UserProfileInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static com.dung.spring.application.util.SQL.SQL_CONSTANT.GET_USER_PROFILE_BY_ID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  @Query(value = GET_USER_PROFILE_BY_ID, nativeQuery = true)
  List<UserProfileInterface> getUserProfileById(Long id);
}
