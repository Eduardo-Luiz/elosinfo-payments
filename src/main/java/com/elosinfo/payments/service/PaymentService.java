package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.exception.PaymentException;
import com.elosinfo.payments.repository.IPaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    private final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private final String PAYMENT_NOT_FOUND = "PAYMENT_NOT_FOUND";

    @Autowired
    private IPaymentRepository repository;
    @Autowired
    private IInstallmentService installmentService;
    private ModelMapper mapper;

    @Autowired
    public PaymentService(IPaymentRepository paymentRepository){
        this.repository = paymentRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<PaymentEntity> getAll() {
        List<PaymentEntity> result;
        try {
            result = this.repository.findAll();
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR, "Error searching payment with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @CachePut(value = "payment", key = "#id")
    @Override
    public Optional<PaymentEntity> getById(Long id) {
        try {
            return this.repository.findById(id);
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR, "Error searching payment with ID " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void placePayment(PaymentDto paymentDto) {
        try {
            this.validatePayment(0L, paymentDto);
            this.installmentService.validate(0L, paymentDto);

            PaymentEntity entity = this.mapper.map(paymentDto, PaymentEntity.class);
            entity.setCreatedAt(LocalDateTime.now());

            this.repository.save(entity);
            this.installmentService.place(entity);

        } catch (PaymentException c) {
            throw c;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PaymentException(UNKNOWN_ERROR,
                    "Error creating payment with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updatePayment(Long id, PaymentDto paymentDto) {
        try {
            Optional<PaymentEntity> searchedEntity = this.repository.findById(id);

            if (searchedEntity.isEmpty()){
                throw new PaymentException(PAYMENT_NOT_FOUND,
                        "There is not payment with ID " + id, HttpStatus.NOT_FOUND);
            }

            this.validatePayment(id, paymentDto);
            this.installmentService.validate(id, paymentDto);

            PaymentEntity updatedEntity = this.mapper.map(paymentDto, PaymentEntity.class);
            updatedEntity.setUpdatedAt(LocalDateTime.now());
            updatedEntity.setId(id);

            this.repository.save(updatedEntity);
            this.installmentService.place(updatedEntity);

        } catch (PaymentException c) {
            throw c;
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR,
                    "Error updating payment_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method that will validate the creation and modification of payments
     * @param paymentDto represents the payment with it's data
     */
    private void validatePayment(Long id, PaymentDto paymentDto){
        //TODO implement validation to save the payment
    }

    @Override
    public void deletePayment(Long id) {
        try {
            this.installmentService.deleteByPaymentId(id);

            this.repository.deleteById(
                    this.repository
                            .findById(id)
                            .orElseThrow(() -> new PaymentException(PAYMENT_NOT_FOUND,
                                    "There is no payment with id " + id,
                                    HttpStatus.INTERNAL_SERVER_ERROR))
                            .getId()
            );

        } catch (PaymentException p) {
            throw p;
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR,
                    "Error deleting payment_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void categorize(Long id, Long idCategory) {
        try {
            Optional<PaymentEntity> searchedEntity = this.repository.findById(id);

            if (searchedEntity.isEmpty()){
                throw new PaymentException(PAYMENT_NOT_FOUND,
                        "There is not payment with ID " + id, HttpStatus.NOT_FOUND);
            }

            PaymentEntity updatedEntity = searchedEntity.get();
            updatedEntity.setCategoryId(idCategory);
            updatedEntity.setUpdatedAt(LocalDateTime.now());
            updatedEntity.setId(id);

            this.repository.save(updatedEntity);

        } catch (PaymentException c) {
            throw c;
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR,
                    "Error categorizing payment_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
