package com.elosinfo.payments.controller;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity> getById(@PathVariable Long id){
        Optional<PaymentEntity> entity = this.paymentService.getById(id);

        return entity
                .map(paymentEntity -> ResponseEntity.status(HttpStatus.OK).body(paymentEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody @Valid PaymentDto paymentDto){
        this.paymentService.placePayment(paymentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody @Valid PaymentDto paymentDto, @PathVariable Long id){
        this.paymentService.updatePayment(id, paymentDto);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        this.paymentService.deletePayment(id);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
