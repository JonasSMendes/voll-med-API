package med.voll.api.domain.Agendamento;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.sql.Timestamp;

public class AgendamentoDeConsulta {

    private Paciente paciente;
    private Medico medico;
    private Timestamp horaData;
}
