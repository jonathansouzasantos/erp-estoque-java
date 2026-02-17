import java.util.ArrayList;
import java.util.Scanner;

/**
 * PROJETO: ERP StockMaster - Módulo de Controle de Estoque
 * OBJETIVO: Praticar fundamentos de Java, Listas e Lógica de Negócio.
 * IDE Sugerida: IntelliJ IDEA (já configurada com JDK 21).
 */
public class ERPEstoque {

    // Classe interna para representar o Produto (Entidade do seu Banco de Dados no futuro)
    static class Produto {
        String nome;
        int quantidade;
        double preco;

        Produto(String nome, int quantidade, double preco) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.preco = preco;
        }

        @Override
        public String toString() {
            return String.format("Produto: %-15s | Qtd: %-5d | Preço: R$ %.2f", nome, quantidade, preco);
        }
    }

    public static void main(String[] args) {
        // Lista dinâmica para armazenar os produtos (simulando um Banco de Dados Oracle)
        ArrayList<Produto> estoque = new ArrayList<>();
        Scanner leitor = new Scanner(System.in);
        int opcao = -1;

        System.out.println("=== SISTEMA ERP STOCKMASTER - INICIALIZANDO ===");
        // Nota: O Maven 3.9.12 que você configurou gerencia as dependências deste projeto.

        while (opcao != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Estoque (Visão Desktop)");
            System.out.println("3. Simular Venda (Baixa no Estoque)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Produto: ");
                    String nome = leitor.nextLine();
                    System.out.print("Quantidade Inicial: ");
                    int qtd = leitor.nextInt();
                    System.out.print("Preço de Venda: ");
                    double preco = leitor.nextDouble();

                    estoque.add(new Produto(nome, qtd, preco));
                    System.out.println("✅ Produto cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- RELATÓRIO DE ESTOQUE ATUAL ---");
                    // O uso de Streams (Java 8+) tornaria isso mais "limpo", você verá isso na DIO.
                    if (estoque.isEmpty()) {
                        System.out.println("Estoque vazio.");
                    } else {
                        estoque.forEach(p -> System.out.println(p.toString()));
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do produto para vender: ");
                    String busca = leitor.nextLine();
                    for (Produto p : estoque) {
                        if (p.nome.equalsIgnoreCase(busca) && p.quantidade > 0) {
                            p.quantidade--;
                            System.out.println("🛒 Venda realizada! Restam " + p.quantidade + " unidades.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando ERP... Backup realizado no Log.");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida!");
            }
        }
        leitor.close();
    }
}