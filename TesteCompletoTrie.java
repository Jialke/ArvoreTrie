public class TesteCompletoTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insere

        trie.inserir("casa");
        trie.inserir("carro");
        trie.inserir("cachorro");
        trie.inserir("cavalo");
        trie.inserir("cama");

        System.out.println("=== Busca de palavras inseridas ===");
        System.out.println("casa: " + trie.buscar("casa"));         // t
        System.out.println("carro: " + trie.buscar("carro"));       // t
        System.out.println("cachorro: " + trie.buscar("cachorro")); // t
        System.out.println("cavalo: " + trie.buscar("cavalo"));     // t
        System.out.println("cama: " + trie.buscar("cama"));         // t
        System.out.println("café: " + trie.buscar("café"));         // f
        System.out.println();

        // Palavras com pre fixo
        System.out.println("--- Palavras com prefixo 'ca' ---");
        String[] prefixoCa = trie.palavrasComPrefixo("ca");
        for (String p : prefixoCa) {
            System.out.println(p);
        }
        System.out.println();

        // Maior pre fixo valido
        System.out.println("--- Maior prefixo válido ---");
        System.out.println("casamento -> " + trie.maiorPrefixo("casamento")); // casa
        System.out.println("carroceria -> " + trie.maiorPrefixo("carroceria")); // carro
        System.out.println("cachorrinho -> " + trie.maiorPrefixo("cachorrinho")); // cachorro
        System.out.println("cavalo -> " + trie.maiorPrefixo("cavalo")); // cavalo
        System.out.println("caverna -> " + trie.maiorPrefixo("caverna")); // ca
        System.out.println();

        // Remover
        System.out.println("--- Remover palavras ---");
        trie.remover("carro");
        System.out.println("carro removido. Buscar 'carro': " + trie.buscar("carro")); // f
        System.out.println("casa ainda existe? " + trie.buscar("casa"));              // t

        // Palavras pós remoção (teste por pre fixo)
        System.out.println();
        System.out.println("--- Palavras com prefixo 'ca' após remoção ---");
        String[] prefixoCa2 = trie.palavrasComPrefixo("ca");
        for (String p : prefixoCa2) {
            System.out.println(p);
        }
    }
}
