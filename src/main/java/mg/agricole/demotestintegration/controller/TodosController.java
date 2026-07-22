package mg.agricole.demotestintegration.controller;

import mg.agricole.demotestintegration.modele.Todos;
import mg.agricole.demotestintegration.service.TodosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {
    private final TodosService service;

    public TodosController(TodosService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todos> getAllTodos(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Todos getTodosById(@PathVariable String id){
        return service.getById(id);
    }

    @PutMapping
    public Todos saveOrUpdateTodo(@RequestBody Todos todos){
        return service.saveOrUpdateTodos(todos);
    }
}
