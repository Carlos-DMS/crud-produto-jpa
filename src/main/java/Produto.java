import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Produto {
    @Id
    private String codigoDeBarra;
    private String nome;
    private String marca;
    private Integer quantidade;
    private LocalDate dataDeCadastro;

    public Produto() {
    }

    public Produto(String codigoDeBarra, String nome, String marca, Integer quantidade) {
        this.codigoDeBarra = codigoDeBarra;
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
        this.dataDeCadastro = LocalDate.now();
    }
}
