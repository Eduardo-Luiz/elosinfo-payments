package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService implements IInstallmentService {

    @Override
    public void validate(Long id, PaymentDto paymentDto) {
        //TODO implementar validação da criação de parcelas de um lançamento
    }

    @Override
    public void place(PaymentEntity paymentEntity) {
        this.build();
        //TODO implementar a criação dos lançamentos de um pagamento
    }

    @Override
    public void deleteByPaymentId(Long idPayment) {
        //TODO deletar parcelas de um pagamento
    }

    private void build(){
        //TODO implements InstallmentService.build()
    }
}
