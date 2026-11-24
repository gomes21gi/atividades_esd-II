public class BubbleSort {

    public static void main (String[] args) {
        int[] lista = {5,2,4,1,3};
        mostrarLista(lista);
        bubbleSort(lista);
    }

    public static void bubbleSort(int[] lista) {
        int tamanho = lista.length;
        for (int x = 0; x < tamanho; x++) {

            for (int i = 0; i < lista.length - 1; i++) {
                int j = i + 1;
                if (lista[i] > lista[j]) {
                    int aux = lista[j];
                    lista[j] = lista[i];
                    lista[i] = aux;
                }
            }
            tamanho--;
        }

        mostrarLista(lista);
    }

    public static void mostrarLista(int[] lista) {
        System.out.println("Minha lista: ");
        for (int i = 0; i < lista.length; i++) {
            System.out.printf("%s, ", lista[i]);
        }
    }
}