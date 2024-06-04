package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository repository;

    public void validador(DadosAgendamentoConsulta dados){
        var medicoPossuiUmaConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiUmaConsultaNoMesmoHorario){
            throw new ValidacaoException("Medico já possui uma consulta no mesmo horario ");
        }

    }

}
