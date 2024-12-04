package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.PhoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Integer, PhoneModel> {
}
