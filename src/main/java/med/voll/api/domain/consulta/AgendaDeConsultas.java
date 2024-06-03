package med.voll.api.domain.consulta;


import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados){


        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()) ){
            throw new ValidacaoException("id do medico não existe");
        }


        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
       // var medico = EscolherMedico(dados);
       // var consulta = new Consulta(null, medico, paciente, dados.data());
        // consultaRepository.save(consulta);
    }

//    private Medico EscolherMedico(DadosAgendamentoConsulta dados) {
//
//    }
}
