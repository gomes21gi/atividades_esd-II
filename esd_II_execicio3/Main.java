public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.adicionarAresta("Joinville", "Araquari", 25);
        grafo.adicionarAresta("Joinville", "Guaramirim", 30);
        grafo.adicionarAresta("Joinville", "Garuva", 40);
        grafo.adicionarAresta("Joinville", "Barra do Sul", 70);
        grafo.adicionarAresta("Araquari", "Barra do Sul", 35);
        grafo.adicionarAresta("Araquari", "São Francisco do Sul", 30);
        grafo.adicionarAresta("Araquari", "Guaramirim", 20);
        grafo.adicionarAresta("Garuva", "São Francisco do Sul", 50);
        grafo.adicionarAresta("São Francisco do Sul", "Barra do Sul", 15);
        grafo.adicionarVertice("Florianópolis");

        Dijkstra dijkstra = new Dijkstra(grafo, "Joinville");

        imprimirResultado(dijkstra, "Joinville");
        imprimirResultado(dijkstra, "Araquari");
        imprimirResultado(dijkstra, "Guaramirim");
        imprimirResultado(dijkstra, "Garuva");
        imprimirResultado(dijkstra, "Barra do Sul");
        imprimirResultado(dijkstra, "São Francisco do Sul");
        imprimirResultado(dijkstra, "Florianópolis");
    }

    private static void imprimirResultado(Dijkstra dijkstra, String destino) {
        Integer distancia = dijkstra.distanciaAte(destino);
        if (distancia == null) {
            System.out.println(destino + ": inalcançável");
        } else {
            System.out.println(destino + ": " + distancia + " " + dijkstra.caminhoAte(destino));
        }
    }
}
