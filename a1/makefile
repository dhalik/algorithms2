all: 
	@javac -cp ../algs4.jar:../stdlib.jar WordNet.java SAP.java Outcast.java
	@java -cp ../algs4.jar:../stdlib.jar:. Outcast wordnet/synsets.txt wordnet/hypernyms.txt wordnet/outcast5.txt wordnet/outcast8.txt wordnet/outcast29.txt

clean:
	rm *.class
