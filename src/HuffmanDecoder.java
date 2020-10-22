/* Huffman Decoder.java
 *  @version May 2, 2020
 *  @author Nischay Uppal
 *  @date May 4, 2020
 *  @description: Huffman Decoder which contains functions to decode an encoded string using Huffman's Tree.
 * */

import java.util.ArrayList;

public class HuffmanDecoder {
    /**
     * huffmanDecode
     * This method takes in the values provided in the encoded file, and decodes the encodedString.
     * @param treeString is the huffman tree provided in the encoded file
     * @param padding is the number of extra bits of padding to make the data a complete byte
     * @param encodedString is the character representation of the encoded string (last line of file)
     * @return the decoded text.
     */
    public String huffmanDecode(String treeString, int padding, String encodedString) throws Exception {
        /* first convert the provided binary tree string to a usable array which preserves relationships and converts ascii to char.
        * Then represent the entire binary tree as a single Tree Node which is the root.
        * Finally, iterate through the binary representation of the encoded string (using convertToBinary)
        * and traverse through the treeNode to convert a sequence of bits (of variable length) into a character. Add these characters together to obtain the decoded text.
        * */
        ArrayList<Character> treeItems = stringTreeToArray(treeString);
        TreeNode<Character> treeRoot = getTreeRoot(treeItems);
        return convertBits(treeRoot,padding,convertToBinary(encodedString));
    }

    /**
     * convertToBinary
     * This method converts the encodedString (provided in file), to a binary sequence.
     * @param encodedString is the encoded string provided in the last line of the file
     * @return the binary representation of the encoded string.
     */
    private String convertToBinary(String encodedString){
        String binaryString = "";
        for(int i = 0; i<encodedString.length(); i++){
            int currentChar = encodedString.charAt(i);
            binaryString = binaryString + String.format("%8s", Integer.toBinaryString(currentChar & 0xFF)).replace(' ', '0');
        }
        return binaryString;
    }

    /**
     * convertBits
     * This method iterates through each bit of the binary representation of the string and traverses the binary tree to
     * arrive at character values. these character values are added together to return a decoded string of text.
     * @param treeRoot is the root node which houses the binary tree.
     * @param padding is the number of extra padding digits in the encodedStream
     * @param encodedStream is the binary representation of the encodedString
     * @return decodedString --> the decoded result (the original result).
     */
    private String convertBits(TreeNode<Character> treeRoot, int padding, String encodedStream) {
        String decodedString = "";
        TreeNode<Character> currentNode = treeRoot;

        /**for each bit in the encodedStream, excluding the extra padding bits as the end
         * get the currentBit at index i. If currentBit is 0, set the currentNode as the left child node.
         * If currentBit is 1, set the currentNode as the right child node.
         */
        for (int i = 0;i < encodedStream.length()-padding;i++){
            char currentBit = encodedStream.charAt(i);
            if(currentBit == '0'){
                currentNode = currentNode.getLeft();
            } else if(currentBit == '1') {
                currentNode = currentNode.getRight();
            }

            //If the current node is a leaf, add the value associated with the currentNode to the decodedString.
            // Then reset the position of the current node back to the tree root.
            if(currentNode.isLeaf()) {
                decodedString = decodedString + currentNode.getItem();
                currentNode = treeRoot;
            } else {
                continue;
            }
        }
        return decodedString;
    }

    /**
     * getTreeRoot
     * This method uses a stack to compress the binary tree into a single node in a stack which is the root node of the
     * binary tree.
     * @param  treeString is the binary tree string provided in the file, converted to an arraylist of characters using
     * the stringTreeToArray method.
     * @return the root node of the binary tree. Since the return is a tree node, the returned root node will have the
     * entire structure of the binary tree.
     */
    private TreeNode<Character> getTreeRoot(ArrayList<Character> treeString) throws Exception {
        GenericStack<TreeNode<Character>> charNodes = new GenericStack();

        /** for each item in the arrayList, treeString,
         * If the item is an opening bracket, ignore it.
         * Else if the item is a closing bracket, pop the previous two Treenodes, create a new node which is a parent of the
         * two nodes, and push that parent node to the stack.
         * If none of the above, then create a new TreeNode which contains the item, and push it to the stack.
         * At the end of this for loop, a single TreeNode exists as the root of the huffman tree.
         */
        for (char item: treeString) {
            if(item == '(') {
                continue;
            } else if (item == ')') {
                TreeNode<Character> n1 = charNodes.pop();
                TreeNode<Character> n2 = charNodes.pop();

                TreeNode<Character> newChar = new TreeNode<>(' ');
                newChar.setLeft(n2);
                newChar.setRight(n1);
                charNodes.push(newChar);
            } else {
                TreeNode<Character> newNode = new TreeNode<>(item);
                charNodes.push(newNode);
            }
        }
        //pop and return the root node from the stack
        return charNodes.pop();
    }

    /**
     * stringTreeToArray
     * This method converts the tree string provided in the file into a usable arrayList of characters which also preserves
     * the relationships between the integers (represented by opening and closing brackets). The integer values in the string are
     * converted to their corresponding characters.
     * @param treeString huffman tree provided in the encoded file
     * @return treeItems --> returns the provided huffman tree in the form of an ArrayList.
     */
    private ArrayList<Character> stringTreeToArray(String treeString){
        //create a new arraylist which will contain all the items in the tree.
        ArrayList<Character> treeItems = new ArrayList<>();
        String currentItem = "";

        /**
         * as we traverse through the tree string, we first check if we encounter a '(' or ')' or ' '.
         * if we do encounter one of these characters, we first check if the currentItem (a term which we are building)
         * is empty. If it is not empty, we add the character representation of the current character
         * (which is an integer (ascii value)). Then we reset the currentItem back to "". if the current character we are checking
         * is not a space, then we simply add the current character to the arrayList.
         * If the currentItem is empty, we add the currentChar to the currentItem.
         */
        for(int i = 0; i< treeString.length();i++){
            char currentChar = treeString.charAt(i);
            if((currentChar== '(') || (currentChar == ')') || (currentChar == ' ')){
                if(currentItem.length()!=0){
                    treeItems.add((char) Integer.parseInt(currentItem));
                    currentItem="";
                }
                if(currentChar != ' '){
                    treeItems.add(currentChar);
                }
            } else {
                currentItem = currentItem + currentChar;
            }
        }
        System.out.println(treeItems);
        return treeItems;
    }
}