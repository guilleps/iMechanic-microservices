package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrandId(Long id);
}
