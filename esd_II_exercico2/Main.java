public class Main {
    static Chamado[] criarChamados() {
        int[] prioridades = {3, 3, 5, 4, 5, 1, 4, 2};
        Chamado[] chamados = new Chamado[prioridades.length];
        for (int i = 0; i < prioridades.length; i++) {
            chamados[i] = new Chamado(i + 1, prioridades[i]);
        }
        return chamados;
    }

    // Item 2.1
    static void insertionSort(Chamado[] v) {
        int deslocamentos = 0;

    
        for (int i = 1; i < v.length; i++) {
            Chamado chave = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].prioridade < chave.prioridade) {
                v[j + 1] = v[j];
                j--;
                deslocamentos++;
            }
            v[j + 1] = chave;
        }
        System.out.println("Deslocamentos no Insertion Sort: " + deslocamentos);
    }

    // Item 2.2
    static void selectionSort(Chamado[] v) {
        int trocas = 0;

        for (int i = 0; i < v.length - 1; i++) {
            int indiceMaior = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].prioridade > v[indiceMaior].prioridade) {
                    indiceMaior = j;
                }
            }
            if (indiceMaior != i) {
                Chamado temp = v[i];
                v[i] = v[indiceMaior];
                v[indiceMaior] = temp;
                trocas++;
            }
        }
        System.out.println("Trocas no Selection Sort: " + trocas);
    }

    public static void main(String[] args) {
        Chamado[] chamados = criarChamados();
        FilaPrioridade fila = new FilaPrioridade(chamados.length);
        for (int i = 0; i < chamados.length; i++) {
            fila.inserir(chamados[i]);
        }
        System.out.println("Estado do heap:");
        fila.imprimirVetor();

        System.out.println("Ordem de Atendimento:");

        for (int i = 0; i < chamados.length; i++) {
            Chamado atendido = fila.removerMaior();
            System.out.println(atendido + " ");
        }

        // Item 2.3
        // Cópias dos chamados para ver os testes funcionando certinho
        Chamado[] copiaInsertion = chamados.clone();
        Chamado[] copiaSelection = chamados.clone();

        System.out.println("\nTeste Insertion Sort");
        insertionSort(copiaInsertion);
        for (int i = 0; i < copiaInsertion.length; i++) {
            System.out.print(copiaInsertion[i] + " ");
        }
        System.out.println();

        System.out.println("\nTeste Selection Sort");
        selectionSort(copiaSelection);
        for (int i = 0; i < copiaSelection.length; i++) {
            System.out.print(copiaSelection[i] + " ");
        }
        System.out.println();

        // O Insertion Sort é estável, ele mantém a ordem de chegada nos chamados de prioridade 3 (1° e 2°). 
        // Já o Selection Sort é instável, onde ele inverte a ordem original dos chamados de prioridade 3, colocando o 2° antes do 1°.
    }
}