package med.voll.api.repository;

import med.voll.api.domain.cancelamentoConsulta.CancelamentoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelamentoConsultaRepositoy extends JpaRepository<CancelamentoConsulta, Long> {
}
