package model.services;

import model.entities.Produto;
import model.exceptions.ProdutoException;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class ProdutoFactory {
    public static Produto cadastroProduto(EntityManager em, Scanner sc){
        System.out.println("===Dados do produto===");
        System.out.print("Código de barras: ");
        String codBarra = sc.nextLine();

        if (em.find(Produto.class, codBarra) == null) {
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
        else {
            System.out.println("Um produto com o mesmo código de barra já está cadastrado.");
            System.out.println();
        }
        return null;
    }

    public static void tratamentoString(String texto){
        if (texto.isBlank()) {
            throw new ProdutoException("Não deixe o campo em branco");
        }
    }

    public static void tratamentoValores(Number valor) {
        if (valor.doubleValue() <= 0){
            throw new ProdutoException("O valor inserido deve ser maior que 0");
        }
    }
}
