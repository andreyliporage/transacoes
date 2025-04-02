package tech.andlmat.transacaoAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.andlmat.transacaoAPI.DTO.EstatisticaDTO;
import tech.andlmat.transacaoAPI.service.EstatisticaService;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService service;

    public EstatisticaController(EstatisticaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTO> get(@RequestParam String intervalo) {
        EstatisticaDTO estatisticaDTO = EstatisticaDTO.fromEstatistica(service.get(intervalo));

        return ResponseEntity.ok(estatisticaDTO);
    }
}
