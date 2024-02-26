package br.com.cep.correios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    private String zipcode;
    private String street;
    private String district;
    private String city;
    private String state;
}
