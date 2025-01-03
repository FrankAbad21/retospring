package com.frab.retospring.repository;

import com.frab.retospring.model.RoleEntity;
import com.frab.retospring.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
