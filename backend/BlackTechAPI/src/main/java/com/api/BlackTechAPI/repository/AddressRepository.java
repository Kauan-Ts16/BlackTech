package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Integer, AddressModel> {
}
