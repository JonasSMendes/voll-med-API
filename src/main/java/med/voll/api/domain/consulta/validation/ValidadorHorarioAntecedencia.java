package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validador(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencasMinuto = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencasMinuto < 30){
            throw new ValidacaoException("consulta deve ser agendada com antecedencia de 30 minutos");
        }
    }

}
