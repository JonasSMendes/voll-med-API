package med.voll.api.domain.cancelamentoConsulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.consulta.Consulta;
@Entity(name = "CancelamentoConsulta")
@Getter
@Table(name = "cancelamento_consulta")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CancelamentoConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    Consulta consulta;

    @Enumerated(EnumType.STRING)
    MotivoCancelamento motivo;

    public CancelamentoConsulta(Consulta consulta, MotivoCancelamento motivo) {
        this.consulta = consulta;
        this.motivo = motivo;
    }
}
