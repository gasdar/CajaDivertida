package com.gasdar.app.funbox.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.funbox.helpers.RequestHelper;
import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.services.FunBoxService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/app/boxes")
public class FunBoxController {

    @Autowired
    private FunBoxService service;

    @Value("${response.not-found}")
    private String messageNF;

    @Value("${response.not-products}")
    private String messageNotProducts;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ResponseEntity<?> notFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
        if(id.length() == 24) { // método helper
            ObjectId boxId = new ObjectId(id);
            Optional<FunBox> optionalBox = service.findById(boxId);
            if(optionalBox.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalBox.get());
            }
        }
        return notFound;
    }

    // Se validan datos básicos, para luego obtener cálculo del valor por pagar y luego dirigirse a pagar
    @PostMapping(value="/{userId}")
    public ResponseEntity<?> save(@Valid @RequestBody FunBox box, BindingResult bindingResult, @PathVariable ObjectId userId) {
        service.validateData(box, bindingResult, userId);
        if(bindingResult.hasErrors()) {
            return RequestHelper.getErrorsFromBody(bindingResult);
        }
        // Los campos están validados, se obtiene el precio de la caja y se corraborá que existan los productos suficientes para generar una caja
        // Si no se asignan los valores restantes de la caja, es porque no hay sufientes productos de esa categoría para generar la caja
        if(!service.assignOthersValues(box)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(RequestHelper.infoResponse(messageNotProducts, HttpStatus.NOT_ACCEPTABLE.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(box));
    }

    // Los campos están validados, se corraborarón que existan los productos sufientes para generar la caja y
    // se realizá el pago, entonces se llama la solicitud (=> estado="Finalizado"), se genere el código y los productos de la caja
    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FunBox box, @PathVariable String id) {
        return null;
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        ResponseEntity<?> notFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
        if(id.length() == 24) {
            ObjectId boxId = new ObjectId(id);
            Optional<FunBox> optionalBox = service.delete(boxId);
            if(optionalBox.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalBox.get());
            }
        }
        return notFound;
    }
    

}
