package mg.agricole.demotestintegration.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mg.agricole.demotestintegration.entity.TodosEntity;
import mg.agricole.demotestintegration.mapper.TodosMapper;
import mg.agricole.demotestintegration.modele.Todos;
import mg.agricole.demotestintegration.repository.TodosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodosService {
    private final TodosRepository repository;
    private final TodosMapper mapper;

    public TodosService(TodosRepository repository, TodosMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Todos> getAll(){
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    public Todos getById(String id){
        TodosEntity entity = repository.findById(id).orElse(null);
        return mapper.toModel(entity);
    }

    public Todos saveOrUpdateTodos(Todos model){
        TodosEntity entity = mapper.toEntity((model));
        TodosEntity savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }
}
