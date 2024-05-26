package med.voll.api.model.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {


    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
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

    public void atualizarInfo(Endereco dados) {
        if (dados.logradouro != null){
            this.logradouro = dados.logradouro;
        }
        if (dados.bairro != null){
            this.bairro = dados.bairro;
        }
        if (dados.uf != null){
            this.uf = dados.uf;
        }
        if (dados.cidade != null){
            this.cidade = dados.cidade;
        }
        if (dados.cep != null){
            this.cep = dados.cep;
        }
        if (dados.numero != null){
            this.numero = dados.numero;
        }
        if (dados.complemento != null){
            this.complemento = dados.complemento;
        }

    }
}
