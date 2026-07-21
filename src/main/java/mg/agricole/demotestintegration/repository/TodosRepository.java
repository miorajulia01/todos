package mg.agricole.demotestintegration.repository;

import mg.agricole.demotestintegration.entity.TodosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface TodosRepository extends JpaRepository<TodosEntity, String>{}

