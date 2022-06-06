package com.elosinfo.payments.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Data //coloca todos os getters e setters
@NoArgsConstructor //criar construtor default
public class CategoryEntity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment") //indica que será controlado pelo banco
    @GenericGenerator(name = "increment", strategy = "increment") //estratégia de geração de ID
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active_at")
    private Boolean active;
}
