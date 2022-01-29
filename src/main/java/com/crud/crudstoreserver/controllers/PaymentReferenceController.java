package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.PaymentReferenceNotFoundException;
import com.crud.crudstoreserver.models.PaymentReference;
import com.crud.crudstoreserver.services.PaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paymentReference")
public class PaymentReferenceController {
    @Autowired
    private PaymentReferenceService paymentReferenceService;

    @GetMapping
    public List<PaymentReference> getAllPaymentReferences(){
        return paymentReferenceService.findAllPaymentReferences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentReference> getPaymentReferenceById(@PathVariable Long id) throws PaymentReferenceNotFoundException {
        Optional<PaymentReference> paymentReferenceOptional = Optional.ofNullable(paymentReferenceService.findById(id));

        if (paymentReferenceOptional.isEmpty()) {
            throw new PaymentReferenceNotFoundException(id);
        }
        return new ResponseEntity<>(paymentReferenceOptional.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addPaymentReference(@RequestBody @Valid PaymentReference paymentReference) {
        paymentReferenceService.createPaymentReference(paymentReference);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentReference> updatePaymentReference(@RequestBody @Valid PaymentReference paymentReference) throws PaymentReferenceNotFoundException {
        paymentReferenceService.updatePaymentReference(paymentReference);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(paymentReference, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePaymentReference(@PathVariable("id") Long id) throws PaymentReferenceNotFoundException {
        paymentReferenceService.deletePaymentReferenceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restorePaymentReference(@PathVariable("id") Long id) throws PaymentReferenceNotFoundException {
        paymentReferenceService.restorePaymentReferenceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
