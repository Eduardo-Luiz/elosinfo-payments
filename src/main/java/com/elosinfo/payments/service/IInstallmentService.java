package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.InstallmentEntity;
import com.elosinfo.payments.entity.PaymentEntity;

import java.util.List;

public interface IInstallmentService {
    public void validate(Long id, PaymentDto paymentDto);
    public void place(PaymentEntity paymentEntity);
    public void deleteByPaymentId(Long idPayment);
}
