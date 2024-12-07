package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.AddressModel;
import com.api.BlackTechAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    List<AddressModel> findAllByUserModel(UserModel userModel);
}
