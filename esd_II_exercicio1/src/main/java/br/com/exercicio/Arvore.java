package br.com.exercicio;

public class Arvore {
    private No raiz;

    // Função utilitária para obter a altura do nó
    private int getAltura(No no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    // Função utilitária para obter o máximo entre dois inteiros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Função para calcular o Fator de Balanceamento
    private int getFatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return getAltura(no.esquerdo) - getAltura(no.direito);
    }

    // Rotação Simples à Direita (Casos LL)
    private No rotacaoDireita(No desbalanceado) {
        No filhoEsquerdo = desbalanceado.esquerdo;
        No subArvoreDireita = filhoEsquerdo.direito;

        // Realiza a rotação
        filhoEsquerdo.direito = desbalanceado;
        desbalanceado.esquerdo = subArvoreDireita;

        // Atualiza as alturas
        desbalanceado.altura = max(getAltura(desbalanceado.esquerdo), getAltura(desbalanceado.direito)) + 1;
        filhoEsquerdo.altura = max(getAltura(filhoEsquerdo.esquerdo), getAltura(filhoEsquerdo.direito)) + 1;

        return filhoEsquerdo;
    }

    // Rotação Simples à Esquerda (Casos RR)
    private No rotacaoEsquerda(No desbalanceado) {
        No filhoDireito = desbalanceado.direito;
        No subArvoreEsquerda = filhoDireito.esquerdo;

        // Realiza a rotação
        filhoDireito.esquerdo = desbalanceado;
        desbalanceado.direito = subArvoreEsquerda;

        // Atualiza as alturas
        desbalanceado.altura = max(getAltura(desbalanceado.esquerdo), getAltura(desbalanceado.direito)) + 1;
        filhoDireito.altura = max(getAltura(filhoDireito.esquerdo), getAltura(filhoDireito.direito)) + 1;

        return filhoDireito;
    }

    // Método de Inserção
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        // 1. Inserção normal de Árvore Binária de Pesquisa
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerdo = inserirRecursivo(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = inserirRecursivo(no.direito, valor);
        } else {
            return no; // Valores duplicados não são permitidos
        }

        // 2. Atualiza a altura do nó ancestral
        no.altura = 1 + max(getAltura(no.esquerdo), getAltura(no.direito));

        // 3. Calcula o Fator de Balanceamento
        int fb = getFatorBalanceamento(no);

        // 4. Casos de Rotação

        // Caso LL (Rotação Simples à Direita)
        if (fb > 1 && valor < no.esquerdo.valor) {
            return rotacaoDireita(no);
        }

        // Caso RR (Rotação Simples à Esquerda)
        if (fb < -1 && valor > no.direito.valor) {
            return rotacaoEsquerda(no);
        }

        // Caso LR (Rotação Dupla à Direita)
        if (fb > 1 && valor > no.esquerdo.valor) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        // Caso RL (Rotação Dupla à Esquerda)
        if (fb < -1 && valor < no.direito.valor) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    // Método de Remoção
    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No no, int valor) {
        // 1. Remoção padrão (Etapa 1)
        if (no == null) {
            return no;
        }

        if (valor < no.valor) {
            no.esquerdo = removerRecursivo(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = removerRecursivo(no.direito, valor);
        } else {
            // Nó com apenas um filho ou sem filhos
            if ((no.esquerdo == null) || (no.direito == null)) {
                No temp = null;
                if (temp == no.esquerdo) {
                    temp = no.direito;
                } else {
                    temp = no.esquerdo;
                }

                if (temp == null) { // Caso Folha
                    no = null;
                } else { // Caso Um Filho
                    no = temp;
                }
            } else {
                // Nó com dois filhos: Pega o sucessor em ordem (menor na direita)
                No temp = noMinimo(no.direito);
                no.valor = temp.valor;
                no.direito = removerRecursivo(no.direito, temp.valor);
            }
        }

        if (no == null) {
            return no;
        }

        // 2. Atualização de Alturas e Cálculo de Fator de Balanceamento (Etapa 2)
        no.altura = max(getAltura(no.esquerdo), getAltura(no.direito)) + 1;
        int fb = getFatorBalanceamento(no);

        // Casos de desbalanceamento pós-remoção

        // Caso LL
        if (fb > 1 && getFatorBalanceamento(no.esquerdo) >= 0) {
            return rotacaoDireita(no);
        }

        // Caso LR
        if (fb > 1 && getFatorBalanceamento(no.esquerdo) < 0) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        // Caso RR
        if (fb < -1 && getFatorBalanceamento(no.direito) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Caso RL
        if (fb < -1 && getFatorBalanceamento(no.direito) > 0) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No noMinimo(No no) {
        No atual = no;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    // Método utilitário para imprimir a árvore em Pré-ordem para testes
    public void imprimirPreOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + "(A:" + no.altura + " FB:" + getFatorBalanceamento(no) + ") ");
            preOrdem(no.esquerdo);
            preOrdem(no.direito);
        }
    }
}
