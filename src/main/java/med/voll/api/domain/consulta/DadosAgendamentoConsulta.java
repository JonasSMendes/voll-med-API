package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idPaciente,

        @NotNull
        Long idMedico,

        @NotNull
        @Future
        LocalDateTime data) {
}
