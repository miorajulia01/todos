package mg.agricole.demotestintegration.mapper;

import mg.agricole.demotestintegration.entity.TodosEntity;
import mg.agricole.demotestintegration.modele.Todos;
import org.springframework.stereotype.Component;

@Component
public class TodosMapper {
    public Todos toModel (TodosEntity entity){
        if (entity == null) return null;
        Todos model = new Todos();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setIsCompleted(entity.isCompleted());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }

    public TodosEntity toEntity (Todos model){
        if (model == null) return null;
        TodosEntity entity = new TodosEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setCompleted(model.getIsCompleted());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }
}
