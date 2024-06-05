package med.voll.api.domain.cancelamentoConsulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.Consulta;

public record DadosCancelamentoConsulta(
        Long idConsulta,
        MotivoCancelamento motivo) {
}
