package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.TaskEntity;
import de.htwberlin.webtech.todolist.persistence.TaskRepository;
import de.htwberlin.webtech.todolist.web.api.Task;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest implements WithAssertions {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Both Statuscodes should match if the task gets removed")
    void testSuccessfullDelete() {

        Long id = 442L;
        doReturn(true).when(taskRepository).existsById(id);
        ResponseEntity<Void> actual = taskService.deleteById(id);
        ResponseEntity<Object> expected = ResponseEntity.ok().build();

        verify(taskRepository).existsById(id);
        verify(taskRepository).deleteById(id);

        assertEquals(actual.getStatusCode(), expected.getStatusCode());
    }

    @Test
    @DisplayName("Both Statuscodes should match 'not found' if a non exisiting task gets removed")
    void testUnsuccessfullDelete() {

        Long id = 442L;
        doReturn(false).when(taskRepository).existsById(id);

        ResponseEntity<Void> actual = taskService.deleteById(id);
        ResponseEntity<Object> expected = ResponseEntity.notFound().build();

        verifyNoMoreInteractions(taskRepository);
        assertEquals(actual.getStatusCode(), expected.getStatusCode());
    }

    @Test
    @DisplayName("Should return all given tasks")
    void testFindAll() {

        var expected = List.of(
                new TaskEntity("Titel1", "Inhalt1", Date.valueOf(LocalDate.of(2023,3,1))),
                new TaskEntity("Titel2", "Inhalt2", Date.valueOf(LocalDate.of(2023,3,2)))
        );

        doReturn(expected).when(taskRepository).findAll();

        List<Task> actual = taskService.findAll();

        verify(taskRepository).findAll();

        // Compare results
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getTitel(), actual.get(i).getTitel());
            assertEquals(expected.get(i).getInhalt(), actual.get(i).getInhalt());
            assertEquals(expected.get(i).getDatum(), actual.get(i).getDatum());
        }
    }
}
