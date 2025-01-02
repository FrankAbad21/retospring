package com.frab.retospring.repository;

import com.frab.retospring.model.RoleEntity;
import com.frab.retospring.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
