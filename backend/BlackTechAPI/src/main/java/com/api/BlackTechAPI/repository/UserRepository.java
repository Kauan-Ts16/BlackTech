package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UUID, UserModel> {
}