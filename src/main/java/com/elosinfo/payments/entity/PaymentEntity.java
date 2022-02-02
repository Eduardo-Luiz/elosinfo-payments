package com.elosinfo.payments.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data //coloca todos os getters e setters
@NoArgsConstructor //criar construtor default
public class PaymentsEntity {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment") //indica que será controlado pelo banco
    @GenericGenerator(name = "increment", strategy = "increment") //estratégia de geração de ID
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "description")
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "total_paid")
    private BigDecimal totalPaid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "installments_number")
    private Integer installmentsNumber;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
