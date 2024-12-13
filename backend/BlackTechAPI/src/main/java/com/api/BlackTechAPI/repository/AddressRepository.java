package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.AddressModel;
import com.api.BlackTechAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    List<AddressModel> findAllByUserModel(UserModel userModel);

    boolean existsByNumberAndPostalCodeAndUserModel(Integer number, String postalCode, UserModel userModel);

    @Modifying
    @Query("DELETE FROM AddressModel a WHERE a.userModel.userId = ?1")
    void deleteAllByUserId(UUID userId);

    int countByUserModel(UserModel userModel);
}
