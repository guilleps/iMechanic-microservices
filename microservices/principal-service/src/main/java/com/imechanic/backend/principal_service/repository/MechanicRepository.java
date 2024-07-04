package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    Optional<Mechanic> findByEmail(String email);

    List<Mechanic> findMechanicsByUserEntityEmail(String email);

    @Query("SELECT CASE " +
            "WHEN :attribute = 'id' THEN cast(m.id as string) " +
            "WHEN :attribute = 'name' THEN m.name " +
            "WHEN :attribute = 'email' THEN m.email " +
            "WHEN :attribute = 'password' THEN m.password " +
            "WHEN :attribute = 'phone' THEN m.phone " +
            "ELSE null END " +
            "FROM Mechanic m " +
            "WHERE m.id = :id")
    Optional<String> findMechanicAttributeByMechanicIdAndAttribute(@Param("id") Long id, @Param("attribute") String attribute);
}
