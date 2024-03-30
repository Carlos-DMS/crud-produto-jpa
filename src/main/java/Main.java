import constants.OpcoesMenu;
import dao.ProdutoDAO;
import dao.ProdutoDAOImpl;
import model.entities.Produto;
import model.exceptions.ProdutoException;
import model.services.ProdutoFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Para utilizar este programa, siga as instruções a seguir:
 * <p>
 * 1. Certifique-se de ter um servidor MySQL em execução localmente na porta 3306.
 * <p>
 * 2. Crie um banco de dados chamado "mercado".
 * <p>
 * Isso permitirá que o programa se conecte ao banco de dados corretamente.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mercado");
        Scanner sc = new Scanner(System.in);
        ProdutoFactory.setScanner(sc);
        ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);

        List<Produto> produtos;
        String codbarra, marca;
        boolean continuar = true;

        do {
            try {
                System.out.println(exibirMenu());

                System.out.print("Escolha uma opção: ");
                int opcao = sc.nextInt();
                System.out.println();

                switch (opcao) {
                    case OpcoesMenu.INSERIR_PRODUTO:
                        Produto novoProduto = ProdutoFactory.cadastroProduto(produtoDAO);
                        produtoDAO.inserir(novoProduto);
                        System.out.println("Produto inserido com sucesso!");
                        System.out.println();
                        break;

                    case OpcoesMenu.ATUALIZAR_PRODUTO:
                        codbarra = ProdutoFactory.inserirCodigoDeBarras();
                        produtoDAO.atualizar(codbarra);
                        System.out.println("Produto atualizado com sucesso!");
                        System.out.println();
                        break;

                    case OpcoesMenu.DELETAR_PRODUTO:
                        codbarra = ProdutoFactory.inserirCodigoDeBarras();
                        produtoDAO.deletarPorId(codbarra);
                        System.out.println("Produto deletado com sucesso!");
                        System.out.println();
                        break;

                    case OpcoesMenu.ENCONTRAR_POR_ID:
                        codbarra = ProdutoFactory.inserirCodigoDeBarras();
                        System.out.println(produtoDAO.encontrarPorId(codbarra));
                        System.out.println();
                        break;

                    case OpcoesMenu.ENCONTRAR_POR_MARCA:
                        marca = ProdutoFactory.inserirMarca();
                        produtos = produtoDAO.encontrarPorMarca(marca);
                        for (Produto produto : produtos){
                            System.out.println(produto);
                            System.out.println();
                        }
                        break;

                    case OpcoesMenu.LISTAR_TODOS:
                        produtos = produtoDAO.encontrarTodos();
                        for (Produto produto : produtos){
                            System.out.println(produto);
                            System.out.println();
                        }
                        break;

                    case OpcoesMenu.SAIR:
                        System.out.println("Programa encerrado.");
                        sc.close();
                        produtoDAO.finalizarOperacoes();
                        emf.close();
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        System.out.println();
                }
            } catch (ProdutoException e1) {
                System.out.println("Erro: " + e1.getMessage());
                System.out.println();
            } catch (InputMismatchException e2) {
                System.out.println("Erro: o dado inserido é inválido");
                System.out.println();
            } catch (Exception e3) {
                System.out.println("Erro inesperado: " + e3.getMessage());
                System.out.println();
            }
        } while (continuar);
    }

    public static StringBuilder exibirMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== Menu de Operações de Produto =====\n");
        menu.append("1. Inserir um novo produto\n");
        menu.append("2. Atualizar um produto por código de barras\n");
        menu.append("3. Deletar um produto por código de barras\n");
        menu.append("4. Encontrar um produto por código de barras\n");
        menu.append("5. Encontrar produtos por marca\n");
        menu.append("6. Listar todos os produtos\n");
        menu.append("0. Sair\n");
        menu.append("========================================\n");
        return menu;
    }
}
