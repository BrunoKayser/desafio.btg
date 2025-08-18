package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErroResponse {

    private LocalDateTime dataHora;
    private String mensagem;

}
