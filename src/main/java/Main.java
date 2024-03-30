import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Para utilizar este programa, siga as instruções a seguir:
 *
 * 1. Certifique-se de ter um servidor MySQL em execução localmente na porta 3306.
 *
 * 2. Crie um banco de dados chamado "mercado".
 *
 * Isso permitirá que o programa se conecte ao banco de dados corretamente.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mercado");
        EntityManager em = emf.createEntityManager();


        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("===Dados do produto===");
                System.out.print("Código de barras: ");
                String codBarra = sc.nextLine();

                if (em.find(Produto.class, codBarra) == null){
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();

                    sc.nextLine();

                    Produto produto = new Produto(codBarra, nome, marca, quantidade);

                    em.getTransaction().begin();
                    em.persist(produto);
                    em.getTransaction().commit();

                    System.out.println("Processo concluído!");

                    break;
                }
                else{
                    System.out.println("Um produto com o mesmo código de barra já está cadastrado.");
                    System.out.println();
                }
            }
            catch (Exception e) {
                System.out.println("Erro: Operação cancelada");
                sc.nextLine();
                System.out.println();
            }
        }



    }
}
