public class Trie {
    private static final int R = 26; // Letras 'a' a 'z'
    private No raiz;

    private static class No {
        boolean fimPalavra;  // indica se termina uma palavra
        No[] filhos = new No[R];
    }

    // Fila manual para armazenar palavras (ja que não podemos usar QUEUE)
    private static class Fila {
        String[] dados;
        int inicio = 0;
        int fim = 0;

        Fila(int capacidade) { dados = new String[capacidade]; }

        void adicionar(String s) { dados[fim++] = s; }

        boolean vazia() { return inicio == fim; }

        String remover() { return dados[inicio++]; }
    }

    private int indice(char c) {
        return c - 'a';
    }

    // Inserir palavra
    public void inserir(String palavra) {
        raiz = inserir(raiz, palavra, 0);
    }

    private No inserir(No x, String palavra, int d) {
        if (x == null) x = new No();
        if (d == palavra.length()) {
            x.fimPalavra = true;
            return x;
        }
        char c = palavra.charAt(d);
        int i = indice(c);
        x.filhos[i] = inserir(x.filhos[i], palavra, d+1);
        return x;
    }

    // Buscar palavra
    public boolean buscar(String palavra) {
        No x = buscar(raiz, palavra, 0);
        return x != null && x.fimPalavra;
    }

    private No buscar(No x, String palavra, int d) {
        if (x == null) return null;
        if (d == palavra.length()) return x;
        char c = palavra.charAt(d);
        int i = indice(c);
        return buscar(x.filhos[i], palavra, d+1);
    }

    // Remover palavra
    public void remover(String palavra) {
        raiz = remover(raiz, palavra, 0);
    }

    private No remover(No x, String palavra, int d) {
        if (x == null) return null;
        if (d == palavra.length()) {
            x.fimPalavra = false;
        } else {
            char c = palavra.charAt(d);
            int i = indice(c);
            x.filhos[i] = remover(x.filhos[i], palavra, d+1);
        }

        // Apagar nó se não é fim de palavra e não tem filhos
        if (x.fimPalavra) return x;
        for (int i = 0; i < R; i++) {
            if (x.filhos[i] != null) return x;
        }
        return null;
    }

    // Palavras com prefixo
    public String[] palavrasComPrefixo(String prefixo) {
        No x = buscar(raiz, prefixo, 0);
        if (x == null) return new String[0]; // fila falsa
        // Supondo no máximo 1000 palavras (mais que isso ai minha ram)
        Fila fila = new Fila(1000);
        coletar(x, prefixo, fila);
        String[] resultado = new String[fila.fim];
        for (int i = 0; i < fila.fim; i++) resultado[i] = fila.dados[i];
        return resultado;
    }

    private void coletar(No x, String prefixo, Fila fila) {
        if (x == null) return;
        if (x.fimPalavra) fila.adicionar(prefixo);
        for (int i = 0; i < R; i++) {
            char c = (char) ('a' + i);
            coletar(x.filhos[i], prefixo + c, fila);
        }
    }

    // Maior prefixo válido de uma palavra
    public String maiorPrefixo(String palavra) {
        int max = -1;
        No x = raiz;
        for (int d = 0; x != null && d < palavra.length(); d++) {
            if (x.fimPalavra) max = d;
            char c = palavra.charAt(d);
            int i = indice(c);
            x = x.filhos[i];
        }
        if (x != null && x.fimPalavra) max = palavra.length();
        return (max == -1) ? null : palavra.substring(0, max);
    }
}
