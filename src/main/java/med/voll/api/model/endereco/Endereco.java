package med.voll.api.model.endereco;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotNull
    private String logradouro;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String uf;
    @NotNull
    private String cep;

    private String numero;

    private String complemento;

    public Endereco(DadosEndereco endereco) {
        this.bairro = endereco.bairro();
        this.logradouro = endereco.logradouro();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }
}
