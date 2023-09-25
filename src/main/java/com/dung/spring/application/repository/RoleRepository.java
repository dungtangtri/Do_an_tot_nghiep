package com.dung.spring.application.repository;

import java.util.Optional;

import com.dung.spring.application.models.ERole;
import com.dung.spring.application.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
