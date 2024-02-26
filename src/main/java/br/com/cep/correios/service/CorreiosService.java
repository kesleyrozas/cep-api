package br.com.cep.correios.service;

import br.com.cep.correios.CorreiosApplication;
import br.com.cep.correios.exception.NoContentException;
import br.com.cep.correios.exception.NoReadyException;
import br.com.cep.correios.model.Address;
import br.com.cep.correios.model.AddressStatus;
import br.com.cep.correios.model.Status;
import br.com.cep.correios.repository.AddressRepository;
import br.com.cep.correios.repository.AddressStatusRepository;
import br.com.cep.correios.repository.SetupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CorreiosService {

    private static Logger logger = LoggerFactory.getLogger(CorreiosService.class);

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;
    @Autowired
    private SetupRepository setupRepository;

    public Status getStatus(){
        return this.addressStatusRepository.findById(String.valueOf(AddressStatus.DEFAULT_ID))
                .orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())//Se não tiver nada no banco ele instania um novo com esse status
                .getStatus();
    }

    public Address getAddressByZipCode(String zipcode) throws NoContentException, NoReadyException {
        if (!this.getStatus().equals(Status.READY)){
            throw new NoReadyException();
        }

        return addressRepository.findById(Integer.valueOf(zipcode))
                .orElseThrow(NoContentException::new);
    }

    private void saveStatus(Status status){
        this.addressStatusRepository.save(AddressStatus.builder()
                .id(AddressStatus.DEFAULT_ID)
                .status(status)
                .build());
    }

    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStaturp(){
        try {
            this.setup();
        }catch (Exception e){
            CorreiosApplication.close(999);
        }
    }

    public void setup() throws Exception{
        logger.info("-----");
        logger.info("-----");
        logger.info("-----SETUP RUNNING");
        logger.info("-----");
        logger.info("-----");

        if (this.getStatus().equals(Status.NEED_SETUP)){
            this.saveStatus(Status.SETUP_RUNNING);

            try {
                this.addressRepository.saveAll(
                        this.setupRepository.getFromOrigin());
            }catch (Exception e){
                this.saveStatus(Status.NEED_SETUP);
                throw e;
            }
            this.saveStatus(Status.READY);
        }

        logger.info("-----");
        logger.info("-----");
        logger.info("-----SERVICE READY");
        logger.info("-----");
        logger.info("-----");
        logger.info("-----");

    }

}
