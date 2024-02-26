package br.com.cep.correios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.SERVICE_UNAVAILABLE, reason = "Serviço está em instalação. Por favor aguarde de 3 a 5 minutos") // 503 não está disponível
public class NoReadyException extends Exception{
}
