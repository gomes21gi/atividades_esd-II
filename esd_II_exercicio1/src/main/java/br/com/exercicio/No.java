package br.com.exercicio;

public class No {
    int valor;
    int altura;
    No esquerdo;
    No direito;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1;
        this.esquerdo = null;
        this.direito = null;
    }
}