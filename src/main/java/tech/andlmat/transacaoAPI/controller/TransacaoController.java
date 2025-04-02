package tech.andlmat.transacaoAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.andlmat.transacaoAPI.DTO.TransacaoDTO;
import tech.andlmat.transacaoAPI.domain.Transacao;
import tech.andlmat.transacaoAPI.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> post(@RequestBody TransacaoDTO transacaoDTO) {
        Transacao transacao = transacaoService.post(transacaoDTO.toTransacao());

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/transacao").buildAndExpand(transacao.getValor()).toUri()).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        transacaoService.delete();

        return ResponseEntity.ok().build();
    }
}
