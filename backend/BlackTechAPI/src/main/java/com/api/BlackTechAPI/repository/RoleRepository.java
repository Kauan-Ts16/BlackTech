package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.enums.RoleName;
import com.api.BlackTechAPI.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    RoleModel findByRoleName(RoleName roleName);
}
