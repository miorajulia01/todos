package mg.agricole.demotestintegration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosEntity {
    @Id
    @Column(name= "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "is_completed")
    private boolean completed;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private OffsetDateTime updatedAt;
}
