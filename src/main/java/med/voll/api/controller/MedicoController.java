package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosListagemMedicoDTO;
import med.voll.api.model.medico.DadosCadastroMedico;
import med.voll.api.model.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedicoDTO> listaMedicos(){
        return repository.findAll()
                .stream()
                .map(DadosListagemMedicoDTO::new)
                .toList();
    }

}
