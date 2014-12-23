import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;



public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() throws FileNotFoundException{
    	System.setIn(new FileInputStream("w6/burrows/encodedSecretMessage.txt"));

    	LinkedList<Integer> R = new LinkedList<>();
    	for (int i = 0; i < 256; i++) {
			R.add(i);
		}
    	while (!BinaryStdIn.isEmpty()){
    		int ch = BinaryStdIn.readChar();
    		for (int i = 0; i < R.size(); i++) {
				if(R.get(i) == ch) {
					BinaryStdOut.write(R.get(i), 8);
					R.add(0, R.remove(i));}
			}
    	}
    	BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() throws FileNotFoundException{
    	System.setIn(new FileInputStream("w6/burrows/encodedSecretMessage.txt"));

    	int[] R = new int[256];
    	for (int i = 0; i < R.length; i++) {
			R[i] = i;
		}
    	while (!BinaryStdIn.isEmpty()){
    		int ch = BinaryStdIn.readChar();
    		int tempstore = R[ch];
    		char x = (char) R[ch];
    		BinaryStdOut.write(x);
    		for (int i = ch ; i > 0; i--) {
    			R[i] = R[i-1];
    		}
    		R[0] = tempstore;
    	}
    	BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) throws FileNotFoundException{
    	if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}