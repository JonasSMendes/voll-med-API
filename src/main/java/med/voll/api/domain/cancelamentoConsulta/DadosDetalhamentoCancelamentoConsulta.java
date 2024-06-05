package med.voll.api.domain.cancelamentoConsulta;

public record DadosDetalhamentoCancelamentoConsulta(
        Long id,
        Long idConsulta,
        MotivoCancelamento motivo
) {
    public DadosDetalhamentoCancelamentoConsulta(CancelamentoConsulta cancelamento){
        this(cancelamento.getId(), cancelamento.getConsulta().getId(),cancelamento.getMotivo());
    }
}
