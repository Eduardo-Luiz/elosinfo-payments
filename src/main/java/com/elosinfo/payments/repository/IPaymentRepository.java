package com.elosinfo.payments.repository;

import com.elosinfo.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
