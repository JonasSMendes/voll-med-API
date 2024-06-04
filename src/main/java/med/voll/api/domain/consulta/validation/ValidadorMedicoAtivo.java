package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.MedicoRepository;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validador(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("o medico não esta disponivel no momento");
        }
    }

}
