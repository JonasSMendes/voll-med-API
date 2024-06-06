package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosAtulizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.dto.DadosListagemPacienteDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity adicionarPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder builder){
        var result = new Paciente(dados);
        repository.save(result);

        var uri = builder.path("paciente/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(result));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listaDePaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var result = repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemPacienteDTO::new);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamentoPacientes(@PathVariable Long id){
        var resul = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(resul));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editandoPaciente(@RequestBody @Valid DadosAtulizacaoPaciente dados){
        var result = repository.getReferenceById(dados.id());

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(result));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePaciente(@PathVariable Long id){
        var result = repository.getReferenceById(id);
        result.excluir();

        return ResponseEntity.noContent().build();
    }


}
