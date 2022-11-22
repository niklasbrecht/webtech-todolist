package de.htwberlin.webtech.todolist.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT users.id FROM users WHERE email = :#{#email}", nativeQuery = true)
    Long getIdByMail(@Param("email") String email);
}
