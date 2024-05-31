package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.dto.DadosListagemMedicoDTO;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder builder){
       var result = new Medico(dados);
       repository.save(result);

       var uri = builder.path("/medicos/{id}").buildAndExpand(result.getId()).toUri();

       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(result));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listaMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var result =  repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemMedicoDTO::new);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhesMedico(@PathVariable Long id) {
        var result = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(result));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMedicos(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInfos(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

}
