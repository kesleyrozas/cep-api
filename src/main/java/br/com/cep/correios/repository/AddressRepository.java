package br.com.cep.correios.repository;

import br.com.cep.correios.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
