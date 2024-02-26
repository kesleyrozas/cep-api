package br.com.cep.correios.repository;

import br.com.cep.correios.model.AddressStatus;
import org.springframework.data.repository.CrudRepository;

public interface AddressStatusRepository extends CrudRepository<AddressStatus, String> {
}
