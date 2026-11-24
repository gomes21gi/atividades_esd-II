import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private final Map<String, List<Aresta>> adjacentes = new LinkedHashMap<>();

    public void adicionarVertice(String vertice) {
        adjacentes.putIfAbsent(vertice, new ArrayList<>());
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("Peso negativo nao permitido: " + peso);
        }

        adicionarVertice(origem);
        adicionarVertice(destino);
        adjacentes.get(origem).add(new Aresta(destino, peso));
        adjacentes.get(destino).add(new Aresta(origem, peso));
    }

    public Map<String, List<Aresta>> getAdjacentes() {
        return adjacentes;
    }

    public List<Aresta> getAdjacencia(String vertice) {
        return adjacentes.get(vertice);
    }
}
