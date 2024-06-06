package med.voll.api.domain.cancelamentoConsulta.validation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamentoConsulta.DadosCancelamentoConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ValidadorCancelamentoComAntecedencia implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;



    public void validador(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var consultaHora = consulta.getData();
        var dataDoDia = LocalDateTime.now();

        var horasEntre = ChronoUnit.HOURS.between(consultaHora,dataDoDia);

        if (horasEntre <= 24){
            throw new ValidacaoException("consulta só pode ser cancelada com 24h de antecedencia");
        }

    }
}
