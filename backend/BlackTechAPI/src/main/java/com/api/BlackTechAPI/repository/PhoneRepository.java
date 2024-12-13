package com.api.BlackTechAPI.repository;

import com.api.BlackTechAPI.model.PhoneModel;
import com.api.BlackTechAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneModel, Integer> {

    List<PhoneModel> findAllByUserModel(UserModel userModel);

    boolean existsByNumber(String number);

    @Modifying
    @Query("DELETE FROM PhoneModel p WHERE p.userModel.userId = ?1")
    void deleteAllByUserId(UUID userId);

    int countByUserModel(UserModel userModel);
}
