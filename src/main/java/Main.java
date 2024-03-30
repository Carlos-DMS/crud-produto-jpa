import model.entities.Produto;
import model.exceptions.ProdutoException;
import model.services.ProdutoFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.InputMismatchException;
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
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                ProdutoFactory.setScanner(sc);

                Produto produto = ProdutoFactory.cadastroProduto(em);

                if (produto != null) {
                    em.getTransaction().begin();
                    em.persist(produto);
                    em.getTransaction().commit();

                    System.out.println("Produto cadastrado com sucesso!");
                    System.out.println();

                    System.out.println("Informações do produto: ");
                    System.out.println(produto);

                    break;
                }
            }
            catch (ProdutoException e1) {
                System.out.println("Erro: " + e1.getMessage());
                sc.nextLine();
                System.out.println();
            }
            catch (InputMismatchException e2){
                System.out.println("Erro: o dado inserido é inválido");
                sc.nextLine();
                System.out.println();
            }
            catch (Exception e3){
                System.out.println("Erro inesperado: " + e3.getMessage());
                sc.nextLine();
                System.out.println();
            }
        }
        sc.close();
        em.close();
        emf.close();
    }
}
