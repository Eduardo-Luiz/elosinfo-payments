package com.elosinfo.payments.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "installment")
@Data //coloca todos os getters e setters
@NoArgsConstructor //criar construtor default
public class InstallmentEntity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment") //indica que será controlado pelo banco
    @GenericGenerator(name = "increment", strategy = "increment") //estratégia de geração de ID
    @Column(name = "id")
    private Long id;
}
