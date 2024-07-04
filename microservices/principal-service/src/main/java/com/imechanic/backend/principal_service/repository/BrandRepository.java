package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
