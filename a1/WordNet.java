import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class WordNet {

    private Map<Integer, String> synsetMap = new HashMap<Integer, String>();
    private Map<String, Set<Integer>> wordToSynsetMap = new HashMap<String, Set<Integer>>();
    private Set<String> allWords = new HashSet<String>();
    private Digraph wordNet;
    private int maxId = -1;
    private SAP pathFinder;

    // constructor takes the name of the two input files
    // nlogn
    public WordNet(String synsets, String hypernyms) {
        In synIn = new In(synsets);
        In hyperIn = new In(hypernyms);
        constructMap(synIn);
        constructDigraph(hyperIn);
        pathFinder = new SAP(wordNet);
    }

    private void constructMap(In in) {
        while (in.hasNextLine()) {
            String s = in.readLine();
            String[] vals = s.split(",");
            ArrayList<String> stringsInSet = new ArrayList<String>(Arrays.asList(vals[1].split(" ")));
            Set<String> synset = new HashSet<String>(stringsInSet);
            allWords.addAll(synset);
            int id = Integer.parseInt(vals[0]);

            if (id > maxId) {
                maxId = id;
            }

            for (String word : stringsInSet) {
                if (wordToSynsetMap.containsKey(word)) {
                    wordToSynsetMap.get(word).add(id);
                }
                else {
                    Set<Integer> wordSet = new HashSet<Integer>();
                    wordSet.add(id);
                    wordToSynsetMap.put(word, wordSet);
                }
            }
            synsetMap.put(id, vals[1]);
        }
    }

    private void constructDigraph(In in) {
        String[] lines = in.readAllLines();
        wordNet = new Digraph(maxId + 1);
        for (String s : lines) {
            String[] conn = s.split(",");
            int root = Integer.parseInt(conn[0]);
            for (int i = 1; i < conn.length; i++) {
                int edge = Integer.parseInt(conn[i]);
                wordNet.addEdge(root, edge);
            }
        }
    }

    // returns all WordNet nouns
    // O(1)
    public Iterable<String> nouns() { return allWords; }

    // is the word a WordNet noun?
    // log(n)
    public boolean isNoun(String word) { return wordToSynsetMap.containsKey(word); }

    // distance between nounA and nounB (defined below)
    // O(n)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null){
            throw new NullPointerException();
        }
        if (!isNoun(nounA))
            throw new IllegalArgumentException();
        if (!isNoun(nounB))
            throw new IllegalArgumentException();
        Iterable<Integer> id1 = wordToSynsetMap.get(nounA);
        Iterable<Integer> id2 = wordToSynsetMap.get(nounB);
        int length = pathFinder.length(id1, id2);

        return length;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // O(n)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA))
            throw new IllegalArgumentException();
        if (!isNoun(nounB))
            throw new IllegalArgumentException();
        Iterable<Integer> id1 = wordToSynsetMap.get(nounA);
        Iterable<Integer> id2 = wordToSynsetMap.get(nounB);
        int ancestor = pathFinder.ancestor(id1, id2);

        return synsetMap.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
