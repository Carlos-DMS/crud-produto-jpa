package model.services;

import dao.ProdutoDAO;
import model.entities.Produto;
import model.exceptions.ProdutoException;

import java.time.LocalDate;
import java.util.Scanner;

public class ProdutoFactory {

    private static Scanner sc;

    public static void setScanner(Scanner sc) {
        ProdutoFactory.sc = sc;
    }

    public static Produto cadastroProduto(ProdutoDAO produtoDAO){
        sc.nextLine();

        System.out.println("===Dados do produto===");
        System.out.print("Código de barras: ");
        String codBarra = sc.nextLine();

        if (!produtoDAO.verificarExistenciaPorId(codBarra)) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            tratamentoString(nome);

            System.out.print("Marca: ");
            String marca = sc.nextLine();
            tratamentoString(marca);

            System.out.print("Preço unitário: ");
            double precoUnitario = sc.nextDouble();
            tratamentoValores(precoUnitario);

            System.out.print("Quantidade: ");
            int quantidade = sc.nextInt();
            tratamentoValores(quantidade);

            return new Produto(codBarra, nome, marca, precoUnitario, quantidade);
        }
        return null;
    }

    public static void atualizarProduto(Produto produto){
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        tratamentoString(nome);

        System.out.print("Marca: ");
        String marca = sc.nextLine();
        tratamentoString(marca);

        System.out.print("Preço unitário: ");
        double precoUnitario = sc.nextDouble();
        tratamentoValores(precoUnitario);

        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();
        tratamentoValores(quantidade);

        produto.setNome(nome);
        produto.setMarca(marca);
        produto.setPrecoUnitario(precoUnitario);
        produto.setQuantidade(quantidade);
        produto.setDataDeAlteracao(LocalDate.now());
    }

    public static String inserirCodigoDeBarras(){
        sc.nextLine();

        System.out.print("Código de barras: ");

        return sc.nextLine();
    }

    public static String inserirMarca(){
        sc.nextLine();

        System.out.print("Marca: ");

        return sc.nextLine();
    }

    public static void tratamentoString(String texto){
        if (texto.isBlank()) {
            throw new ProdutoException("Não deixe o campo em branco.");
        }
    }

    public static void tratamentoValores(Number valor) {
        if (valor.doubleValue() <= 0){
            throw new ProdutoException("O valor inserido deve ser maior que 0.");
        }
    }
}
