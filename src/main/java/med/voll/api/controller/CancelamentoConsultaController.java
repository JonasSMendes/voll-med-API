package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.cancelamentoConsulta.DadosCancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.service.CancelamentoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancelamento")
public class CancelamentoConsultaController {

    @Autowired
    private CancelamentoConsultaService cancelamento;


    @PostMapping
    public ResponseEntity cancelamentoConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados){
      var dto = cancelamento.cancelar(dados);
      return ResponseEntity.ok(dados);
    }
}
