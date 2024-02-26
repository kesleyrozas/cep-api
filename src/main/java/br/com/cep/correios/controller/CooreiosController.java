package br.com.cep.correios.controller;

import br.com.cep.correios.exception.NoContentException;
import br.com.cep.correios.exception.NoReadyException;
import br.com.cep.correios.model.Address;
import br.com.cep.correios.service.CorreiosService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CooreiosController {

    @Autowired
    private CorreiosService correiosService;

    @GetMapping("/status")
    public String getStatus(){
        return "Service Status: " + correiosService.getStatus();
    }

    @GetMapping("/zipcode/{zipcode}")
    public Address getAddressByZipCode(@PathVariable("zipcode") String zipcode) throws NoContentException, NoReadyException {
        return Address.builder().zipcode(zipcode).build();
    }

}
