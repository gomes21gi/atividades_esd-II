public class FilaPrioridade {
    private Chamado[] heap;
    private int tamanho;

    public FilaPrioridade(int capacidade) {
        heap = new Chamado[capacidade];
        tamanho = 0;
    }

    // Item 1.1
    private boolean temPrioridadeSobre(Chamado a, Chamado b) {
        if (a.prioridade > b.prioridade){
            return true;
        }

        if (a.prioridade == b.prioridade && a.id < b.id){
            return true;
        }

        return false;
    }

    // Item 1.2
    public void inserir(Chamado c) {
        if (tamanho >= heap.length) {
            System.out.println("Fila cheia!");
            return;
        }

        heap[tamanho] = c;
        subir(tamanho);
        tamanho++;
    }

    private void subir(int posicaoAtual){
        int pai = (posicaoAtual - 1) / 2;

        while (posicaoAtual > 0 && temPrioridadeSobre(heap[posicaoAtual], heap[pai])) {
            Chamado temp = heap[posicaoAtual];
            heap[posicaoAtual] = heap[pai];
            heap[pai] = temp;
            posicaoAtual = pai;
            pai = (posicaoAtual - 1) / 2;
        }
    }

    // Item 1.3
    public Chamado removerMaior() {
        if (tamanho == 0){
            return null;
        }

        Chamado raiz = heap[0];
        tamanho--;
        heap[0] = heap[tamanho];
        heap[tamanho] = null;
        descer(0);
        return raiz;
    }

    private void descer(int posicaoAtual) {
        int filhoEsquerdo = (2 * posicaoAtual) + 1; 
        int filhoDireito = (2 * posicaoAtual) + 2;
        int maior = posicaoAtual;

        if (filhoEsquerdo < tamanho) {
            if (temPrioridadeSobre(heap[filhoEsquerdo], heap[maior]) == true) {
                maior = filhoEsquerdo;
            }
        }

        if (filhoDireito < tamanho) {
            if (temPrioridadeSobre(heap[filhoDireito], heap[maior]) == true) {
                maior = filhoDireito;
            }
        }

        if (maior == posicaoAtual) {
            return;
        }

        if (maior != posicaoAtual) {
            Chamado temp = heap[posicaoAtual];
            heap[posicaoAtual] = heap[maior];
            heap[maior] = temp;

            descer(maior);
        }
    }

    public void imprimirVetor() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}