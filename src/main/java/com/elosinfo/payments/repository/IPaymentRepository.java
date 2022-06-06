package com.elosinfo.payments.repository;

import com.elosinfo.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long> {
    @Query("SELECT p FROM payment p WHERE p.categoryId = null and p.active = true")
    public List<PaymentEntity> getNotCategorized();
}
