package de.htwberlin.webtech.todolist.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByTitel(String titel);

    @Query(value = "SELECT t FROM tasks t WHERE t.benutzer.id = ?1")
    List<TaskEntity> findAllByBenutzer(Long benutzer_id);
}
