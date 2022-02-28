package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    public List<PaymentEntity> getAll();

    public Optional<PaymentEntity> getById(Long id);

    public void placePayment(PaymentDto paymentDto);

    public void updatePayment(Long id, PaymentDto customerDto);

    public void deletePayment(Long id);

    void categorize(Long id, Long idCategory);
}
