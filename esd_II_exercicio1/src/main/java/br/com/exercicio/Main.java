package br.com.exercicio;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        System.out.println("--- Teste 1: Inserção com Rotação RR ---");
        // Sequência: 10, 20, 30 (vai forçar uma rotação à esquerda)
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);
        System.out.print("Pré-ordem esperada: 20 -> 10 -> 30 | Obtida: ");
        arvore.imprimirPreOrdem();

        System.out.println("\n--- Teste 2: Inserção com Rotação RL ---");
        // Limpando e testando outra sequência: 10, 30, 20
        ArvoreAVL arvore2 = new ArvoreAVL();
        arvore2.inserir(10);
        arvore2.inserir(30);
        arvore2.inserir(20);
        System.out.print("Pré-ordem esperada: 20 -> 10 -> 30 | Obtida: ");
        arvore2.imprimirPreOrdem();

        System.out.println("\n--- Teste 3: Sequência complexa e Remoção ---");
        ArvoreAVL arvore3 = new ArvoreAVL();
        int[] valores = {40, 20, 60, 10, 30, 50, 70};
        for (int v : valores) {
            arvore3.inserir(v);
        }

        System.out.print("Árvore após inserções: ");
        arvore3.imprimirPreOrdem();

        System.out.println("Removendo os nós 60 e 40...");
        arvore3.remover(60);
        arvore3.remover(40);

        System.out.print("Árvore após remoções: ");
        arvore3.imprimirPreOrdem();
    }
}