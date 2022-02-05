package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.PaymentNotFoundException;
import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.findAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) throws PaymentNotFoundException {
        paymentService.findById(id);

        try{
            paymentService.findById(id);
        } catch (PaymentNotFoundException paymentNotFoundException) {
            return new ResponseEntity<>(paymentNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody @Valid Payment payment) {
        paymentService.createPayment(payment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Payment> updatePayment(@RequestBody @Valid Payment payment) throws PaymentNotFoundException {
        paymentService.updatePayment(payment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(payment, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable("id") Long id) throws PaymentNotFoundException {
        paymentService.deletePaymentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restorePayment(@PathVariable("id") Long id) throws PaymentNotFoundException {
        paymentService.restorePaymentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
