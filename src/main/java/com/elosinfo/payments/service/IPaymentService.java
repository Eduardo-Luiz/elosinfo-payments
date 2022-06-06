package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    List<PaymentEntity> getAll();

    Optional<PaymentEntity> getById(Long id);

    void placePayment(PaymentDto paymentDto);

    void updatePayment(Long id, PaymentDto customerDto);

    void deletePayment(Long id);

    void categorize(Long id, Long idCategory);

    List<PaymentEntity> getNotCategorized();
}
