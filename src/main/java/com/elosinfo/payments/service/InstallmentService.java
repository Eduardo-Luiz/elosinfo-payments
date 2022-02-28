package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService implements IInstallmentService {

    @Override
    public void validate(Long id, PaymentDto paymentDto) {
        //TODO validate
    }

    @Override
    public void place(PaymentEntity paymentEntity) {
        this.build();
        //TODO save
    }

    @Override
    public void deleteByPaymentId(Long idPayment) {
        //TODO delete
    }

    private void build(){
        //TODO implements
    }
}
