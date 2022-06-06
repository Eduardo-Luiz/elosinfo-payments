package com.elosinfo.payments.controller;

import com.elosinfo.payments.dto.PaymentDto;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.model.Response;
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

    /**
     * Retornará todos os lançamentos de acordo com os filtros passados
     * @return Lista de lançamentos de acordo com os filtros
     */
    @GetMapping
    public ResponseEntity<List<PaymentEntity>> getAll(){
        //TODO Determinar filtros e aplicar na busca dos lançamentos
        return ResponseEntity.status(HttpStatus.OK).body(this.paymentService.getAll());
    }

    /**
     * Retorna um lançamento específico
     * @param id identificador do lançamento a ser consultado
     * @return retorna um objeto que representa o lançamento solicitado
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity> getById(@PathVariable Long id){
        //TODO Criar DTO como saída, não utilizando a model
        Optional<PaymentEntity> entity = this.paymentService.getById(id);

        return entity
                .map(paymentEntity -> ResponseEntity.status(HttpStatus.OK).body(paymentEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/dash-view")
    public ResponseEntity<?> getDashView(){
        //TODO Definir contrato do que será exibido como saída no dash e implementar a busca
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid PaymentDto paymentDto){
        this.paymentService.placePayment(paymentDto);

        Response.ResponseBuilder response = Response.builder();

        return ResponseEntity.status(HttpStatus.CREATED).body(response.build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid PaymentDto paymentDto, @PathVariable Long id){
        this.paymentService.updatePayment(id, paymentDto);

        Response.ResponseBuilder response = Response.builder();

        return ResponseEntity.status(HttpStatus.OK).body(response.build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id){
        this.paymentService.deletePayment(id);

        Response.ResponseBuilder response = Response.builder();

        return ResponseEntity.status(HttpStatus.OK).body(response.build());
    }

    /**
     *
     * @param id payment ID
     * @param idCategory category ID
     * @return true if it's ok
     */
    @PutMapping("/{id}/category/{idCategory}")
    public ResponseEntity<Boolean> categorize(@PathVariable Long id, @PathVariable Long idCategory){
        this.paymentService.categorize(id, idCategory);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/not-categorized")
    public ResponseEntity<List<PaymentEntity>> getNotCategorized(){
        return ResponseEntity.status(HttpStatus.OK).body(this.paymentService.getNotCategorized());
    }


}
