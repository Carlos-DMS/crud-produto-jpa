package dao;

import model.entities.Produto;
import model.exceptions.ProdutoException;
import model.services.ProdutoFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO{

    private EntityManager em;

    public ProdutoDAOImpl(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public void inserir(Produto produto) {

        if (produto != null){
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        }
        else{
            throw new ProdutoException("Já existe um produto cadastrado com esse código de barras.");
        }
    }

    @Override
    public void atualizar(String codigoDeBarra) {
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, codigoDeBarra);

        if (produto != null) {
            ProdutoFactory.atualizarProduto(produto);

            em.merge(produto);
            em.getTransaction().commit();
        }
        else{
            throw new ProdutoException("O produto não pode ser atualizado, pois não existe no banco de dados.");
        }
    }

    @Override
    public void deletarPorId(String codigoDeBarra) {
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, codigoDeBarra);

        if (produto != null) {
            em.remove(produto);
            em.getTransaction().commit();
        }
        else{
            throw new ProdutoException("O produto não pode ser deletado, pois não existe no banco de dados.");
        }
    }

    @Override
    public Produto encontrarPorId(String codigoDeBarra) {
        Produto produto = em.find(Produto.class, codigoDeBarra);
        if (produto != null) {
            return produto;
        }
        else{
            throw new ProdutoException("O produto não existe no banco de dados.");
        }
    }

    @Override
    public boolean verificarExistenciaPorId(String codigoDeBarra) {
        Produto produto = em.find(Produto.class, codigoDeBarra);

        if (produto != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Produto> encontrarPorMarca(String marca) {
        TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE p.marca = :marca", Produto.class);
        query.setParameter("marca", marca);

        List<Produto> produtos = query.getResultList();

        if (produtos.isEmpty()) {
            throw new ProdutoException("Não há correspondências dessa marca no banco de dados.");
        }

        return produtos;
    }


    @Override
    public List<Produto> encontrarTodos() {
        TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p", Produto.class);

        List<Produto> produtos = query.getResultList();

        if (produtos.isEmpty()) {
            throw new ProdutoException("Não há nenhum produto no banco de dados.");
        }

        return produtos;
    }

    @Override
    public void finalizarOperacoes() {
        em.close();
    }
}
