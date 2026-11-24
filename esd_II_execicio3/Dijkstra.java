import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    private final String origem;
    private final Map<String, Integer> distancias = new HashMap<>();
    private final Map<String, String> predecessores = new HashMap<>();

    public Dijkstra(Grafo grafo, String origem) {
        if (grafo.getAdjacentes().get(origem) == null) {
            throw new IllegalArgumentException("Vertice de origem inexistente: " + origem);
        }

        this.origem = origem;

        for (String vertice : grafo.getAdjacentes().keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }

        calcular(grafo);
    }

    private void calcular(Grafo grafo) {
        PriorityQueue<No> fila = new PriorityQueue<>();
        distancias.put(origem, 0);
        fila.add(new No(origem, 0));

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            if (atual.distancia != distancias.get(atual.vertice)) {
                continue;
            }

            for (Aresta aresta : grafo.getAdjacencia(atual.vertice)) {
                int novaDistancia = atual.distancia + aresta.getPeso();
                if (novaDistancia < distancias.get(aresta.getDestino())) {
                    distancias.put(aresta.getDestino(), novaDistancia);
                    predecessores.put(aresta.getDestino(), atual.vertice);
                    fila.add(new No(aresta.getDestino(), novaDistancia));
                }
            }
        }
    }

    public Integer distanciaAte(String destino) {
        Integer distancia = distancias.get(destino);
        return distancia == null || distancia == Integer.MAX_VALUE ? null : distancia;
    }

    public List<String> caminhoAte(String destino) {
        if (distanciaAte(destino) == null) {
            return Collections.emptyList();
        }

        List<String> caminho = new ArrayList<>();
        for (String atual = destino; atual != null; atual = predecessores.get(atual)) {
            caminho.add(atual);
            if (atual.equals(origem)) {
                break;
            }
        }
        Collections.reverse(caminho);
        return caminho;
    }

    private static class No implements Comparable<No> {
        private final String vertice;
        private final int distancia;

        private No(String vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(No outro) {
            return Integer.compare(distancia, outro.distancia);
        }
    }
}
