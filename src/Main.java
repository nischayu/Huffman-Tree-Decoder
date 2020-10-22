/* Main.java
 *  @version May 3, 2020
 *  @author Nischay Uppal
 *  @date May 4, 2020
 *  @description: Main source file which runs program
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HuffmanDecoder huffmanDecoder = new HuffmanDecoder();
        BufferedReader fileReader = new BufferedReader(new FileReader("C:\\compressed.MZIP"));

        int lineNumber = 0;
        int padding = 0;

        String lineString = "";
        String treeString = "";
        String encodedString = "";

        //Reading the file and writing each line to its corresponding variable
        while ((lineString = fileReader.readLine()) != null) {
            switch(lineNumber) {
                case 1:
                    treeString = lineString;
                    break;
                case 2:
                    padding = Integer.parseInt(lineString);
                    break;
                case 3:
                    encodedString = lineString;
                    break;
            }
            lineNumber++;
        }
        fileReader.close();

        //Outputting the original decoded String.
        try {
            System.out.println(huffmanDecoder.huffmanDecode(treeString, padding, encodedString));
            //System.out.println(huffmanDecoder.huffmanDecode("(((68 75) 69)((79 82)((32 65)(66 67))))",3,"ø\u001Cêéh"));

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
