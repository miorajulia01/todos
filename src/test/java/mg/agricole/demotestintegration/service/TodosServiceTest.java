package mg.agricole.demotestintegration.service;

import mg.agricole.demotestintegration.model.Todos;
import mg.agricole.demotestintegration.repository.TodosRepository;
import mg.agricole.demotestintegration.entity.TodosEntity;
import mg.agricole.demotestintegration.mapper.TodosMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodosServiceTest {

    @Mock
    private TodosRepository repository;

    @Mock
    private TodosMapper mapper;

    @InjectMocks
    private TodosService service;

    @Test
    void can_get_todo_by_id() {
        // Given
        String todoId = "123e4567-e89b-12d3-a456-426614174000"; // Un vrai format UUID en String

        TodosEntity mockEntity = new TodosEntity();
        mockEntity.setId(todoId);
        mockEntity.setTitle("Apprendre Mockito");
        mockEntity.setDescription("Faire les tests unitaires");
        mockEntity.setCompleted(false);

        Todos mockTodo = new Todos();
        mockTodo.setId(todoId);
        mockTodo.setTitle("Apprendre Mockito");
        mockTodo.setDescription("Faire les tests unitaires");
        mockTodo.setIsCompleted(false);

        // When
        when(repository.findById(todoId)).thenReturn(Optional.of(mockEntity));
        when(mapper.toModel(mockEntity)).thenReturn(mockTodo);

        Todos result = service.getById(todoId);

        // Then
        assertNotNull(result);
        assertEquals(todoId, result.getId());
        assertEquals("Apprendre Mockito", result.getTitle());

        // Verify interactions
        verify(repository, times(1)).findById(todoId);
        verify(mapper, times(1)).toModel(mockEntity);
    }
}