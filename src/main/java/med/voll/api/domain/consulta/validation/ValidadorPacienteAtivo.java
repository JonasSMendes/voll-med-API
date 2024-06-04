package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.PacienteRepository;

public class ValidadorPacienteAtivo {

    private PacienteRepository repository;

    public void validador(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());

        if (!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não podera ser efetuada com paciente excluido");
        }

    }
}
