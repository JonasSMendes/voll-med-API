package med.voll.api.domain.cancelamentoConsulta.service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamentoConsulta.CancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.DadosCancelamentoConsulta;
import med.voll.api.domain.cancelamentoConsulta.DadosDetalhamentoCancelamentoConsulta;
import med.voll.api.repository.CancelamentoConsultaRepositoy;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelamentoConsultaService {

    @Autowired
    private CancelamentoConsultaRepositoy cancelamentoConsultaRepositoy;

    @Autowired
    private ConsultaRepository consultaRepository;

    public DadosDetalhamentoCancelamentoConsulta cancelar(DadosCancelamentoConsulta dados){
        if (!cancelamentoConsultaRepositoy.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta não existe");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var cancelamento = new CancelamentoConsulta(null,consulta,dados.motivo());

        cancelamentoConsultaRepositoy.save(cancelamento);

        return new DadosDetalhamentoCancelamentoConsulta(cancelamento);
    }
}
