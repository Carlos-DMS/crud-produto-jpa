package dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDAO {
    void inserir(Produto produto);
    void atualizar(String codigoDeBarra);
    void deletarPorId(String codigoDeBarra);
    Produto encontrarPorId(String codigoDeBarra);
    boolean verificarExistenciaPorId(String codigoDeBarra);
    List<Produto> encontrarPorMarca(String marca);
    List<Produto> encontrarTodos();
    void finalizarOperacoes();
}
