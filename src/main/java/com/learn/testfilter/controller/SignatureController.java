package com.learn.testfilter.controller;

import com.learn.testfilter.payload.request.SignatureDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.learn.testfilter.utils.constants.ApiConstants.*;

@RequestMapping(value = API_V1)
@RestController
public class SignatureController {

    @GetMapping(HELLO)
    ResponseEntity<?> sayHello() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping(SIGNATURE)
    ResponseEntity<?> createSignature(@RequestBody(required = false) SignatureDTO signatureDTO) {
        return ResponseEntity.ok(signatureDTO.getSignature());
    }
}
