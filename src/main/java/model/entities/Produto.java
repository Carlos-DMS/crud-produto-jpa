package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Produto {
    @Id
    @Column(name = "codigo_de_barra")
    private String codigoDeBarra;

    private String nome;

    private String marca;

    @Column(name = "preco_unitario")
    private Double precoUnitario;

    private Integer quantidade;

    @Column(name = "data_de_cadastro")
    private LocalDate dataDeCadastro;

    @Column(name = "data_de_alteracao")
    private LocalDate dataDeAlteracao;

    public Produto() {
    }

    public Produto(String codigoDeBarra, String nome, String marca, Double precoUnitario, Integer quantidade) {
        this.codigoDeBarra = codigoDeBarra;
        this.nome = nome;
        this.marca = marca;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.dataDeCadastro = LocalDate.now();
        this.dataDeAlteracao = null;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataDeAlteracao(LocalDate dataDeAlteracao) {
        this.dataDeAlteracao = dataDeAlteracao;
    }

    public Double valorTotal(){
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Código de Barras: ").append(codigoDeBarra).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Marca: ").append(marca).append("\n");
        sb.append("Preço Unitário: ").append(String.format("%.2f", precoUnitario)).append("\n");
        sb.append("Quantidade: ").append(quantidade).append("\n");
        sb.append("Data de Cadastro: ").append(dataDeCadastro.format(fmt)).append("\n");
        sb.append("Valor Total: ").append(String.format("%.2f", valorTotal())).append("\n");

        if (dataDeAlteracao != null) {
            sb.append("Última alteração: ").append(dataDeAlteracao.format(fmt)).append("\n");
        }
        else {
            sb.append("Última alteração: N/A").append("\n");
        }

        return sb.toString();
    }

}
