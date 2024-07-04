package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u.email FROM UserEntity u WHERE u.email = :email")
    Optional<String> findEmailUserByEmail(@Param("email") String email);

    @Query("SELECT CASE "
            + "WHEN :attribute = 'email' THEN u.email "
            + "WHEN :attribute = 'password' THEN u.password "
            + "WHEN :attribute = 'name' THEN u.name "
            + "WHEN :attribute = 'phone' THEN u.phone "
            + "WHEN :attribute = 'address' THEN u.address "
            + "END "
            + "FROM UserEntity u "
            + "WHERE u.email = :email")
    Optional<String> findUserAttributeByEmailAndAttribute(@Param("email") String email, @Param("attribute") String attribute);
}
