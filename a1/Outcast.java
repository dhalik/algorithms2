public class Outcast {

    private WordNet wordNet;


    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int[] dist = new int[nouns.length];

        for (int i = 0; i < nouns.length; i++) {
            dist[i] = 0;
            for (String s2 : nouns) {
                dist[i] += wordNet.distance(nouns[i], s2);
            }
        }

        int maxIdx = -1;
        int max = -1;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > max) {
                maxIdx = i;
                max = dist[i];
            }
        }
        return nouns[maxIdx];
    }

    public static void main(String[] args)  // see test client below
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
