package med.voll.api.domain.cancelamentoConsulta.service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamentoConsulta.CancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.DadosCancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.DadosDetalhamentoCancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.validation.ValidadorCancelamentoConsulta;
import med.voll.api.repository.CancelamentoConsultaRepositoy;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelamentoConsultaService {

    @Autowired
    private CancelamentoConsultaRepositoy cancelamentoConsultaRepositoy;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadorCancelamento;

    public DadosDetalhamentoCancelamentoConsulta cancelar(DadosCancelamentoConsulta dados){
        var consulta = consultaRepository.findById(dados.idConsulta())
                .orElseThrow(() -> new ValidacaoException("Id da consulta não existe"));;

        validadorCancelamento.forEach(v -> v.validador(dados));

        var cancelamento = new CancelamentoConsulta(consulta,dados.motivo());
        cancelamentoConsultaRepositoy.save(cancelamento);

        return new DadosDetalhamentoCancelamentoConsulta(cancelamento);
    }
}
