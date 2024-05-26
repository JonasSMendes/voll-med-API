package med.voll.api.model.medico.dto;

import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record DadosListagemMedicoDTO(String nome,
                                     Long id,
                                     String email,
                                     String crm,
                                     Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getNome(),medico.getId(), medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
