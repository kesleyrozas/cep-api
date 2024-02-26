package br.com.cep.correios.model;

public enum Status {
    NEED_SETUP, //Precisa baixar o csv do correios
    SETUP_RUNNING, //Está baixando  e salvando no banco
    READY; // Serviço está disponível para consulta
}
