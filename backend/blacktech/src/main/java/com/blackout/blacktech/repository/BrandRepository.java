package com.blackout.blacktech.repository;

import com.blackout.blacktech.model.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, UUID> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, UUID id);

    boolean existsByIdAndIsActiveTrue(UUID id);

    boolean existsByIdAndIsActiveFalse(UUID id);

    Optional<BrandModel> findByIdAndIsActiveTrue(UUID id);

    Optional<BrandModel> findByIdAndIsActiveFalse(UUID id);

    List<BrandModel> findAllByIsActiveTrue();

    List<BrandModel> findAllByIsActiveFalse();
}