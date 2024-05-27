package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String telefone;
    @Column(unique = true)
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;


    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();
    }

    public void excluir() {
        this.ativo = false;
    }
}
