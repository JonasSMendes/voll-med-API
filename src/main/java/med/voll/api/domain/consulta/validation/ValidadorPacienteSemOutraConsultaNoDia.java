package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private ConsultaRepository repository;

    private void validador(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

    }

}
