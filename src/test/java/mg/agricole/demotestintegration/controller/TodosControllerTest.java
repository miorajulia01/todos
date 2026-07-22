package mg.agricole.demotestintegration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mg.agricole.demotestintegration.exception.NotFoundException;
import mg.agricole.demotestintegration.modele.Todos;
import mg.agricole.demotestintegration.service.TodosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodosController.class)
public class TodosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodosService todosService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllTodos() throws Exception {
        Todos todo = new Todos();
        todo.setId("1");
        todo.setTitle("Test Todo");
        todo.setIsCompleted(false);

        when(todosService.getAll()).thenReturn(List.of(todo));

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Test Todo"));
    }

    @Test
    void shouldReturn404WhenTodoNotFound() throws Exception {
        when(todosService.getById("999")).thenThrow(new NotFoundException("Todo not found"));

        mockMvc.perform(get("/todos/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Todo not found"));
    }
}
