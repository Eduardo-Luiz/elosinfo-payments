package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.exception.PaymentException;
import com.elosinfo.payments.repository.IPaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository repository;

    @Override
    public List<PaymentEntity> getAll() {
        List<PaymentEntity> result;
        try {
            result = this.repository.findAll();
        } catch (Exception e) {
            throw new PaymentException("Error searching payment with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Override
    public Optional<PaymentEntity> getById(Long id) {
        try {
            return this.repository.findById(id);
        } catch (Exception e) {
            throw new PaymentException("Error searching payment with ID " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void placePayment(PaymentDto paymentDto) {
        try {
            PaymentEntity entity = new ModelMapper().map(paymentDto, PaymentEntity.class);
            this.repository.save(entity);
        } catch (PaymentException c) {
            throw c;
        } catch (Exception e) {
            throw new PaymentException("Error creating payment with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updatePayment(Long id, PaymentDto paymentDto) {
        try {
            Optional<PaymentEntity> searchedEntity = this.repository.findById(id);

            if (searchedEntity.isEmpty()){
                throw new PaymentException("There is not payment with ID " + id, HttpStatus.NOT_FOUND);
            }

            PaymentEntity updatedEntity = new ModelMapper().map(paymentDto, PaymentEntity.class);
            updatedEntity.setId(id);

            this.repository.save(updatedEntity);
        } catch (PaymentException c) {
            throw c;
        } catch (Exception e) {
            throw new PaymentException("Error updating payment_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletePayment(Long id) {
        try {
            this.repository.deleteById(
                    this.repository
                            .findById(id)
                            .orElseThrow(() -> new PaymentException("There is not", HttpStatus.INTERNAL_SERVER_ERROR))
                            .getId()
            );

        } catch (PaymentException p) {
            throw p;
        } catch (Exception e) {
            throw new PaymentException("Error deleting payment_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
