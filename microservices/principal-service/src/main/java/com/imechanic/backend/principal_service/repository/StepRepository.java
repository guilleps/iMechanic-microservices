package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

    @Query("SELECT CASE " +
            "WHEN :attribute = 'id' THEN cast(s.id as string ) " +
            "WHEN :attribute = 'name' THEN s.name " +
            "WHEN :attribute = 'orderStep' THEN s.orderStep " +
            "ELSE null END " +
            "FROM Step s " +
            "WHERE s.id = :id")
    Optional<String> findStepAttributeByStepIdAndAttribute(@Param("id") Long id, @Param("attribute") String attribute);

}
