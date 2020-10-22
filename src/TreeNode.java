/* TreeNode.java
 *  @version May 1, 2020
 *  @author Nischay Uppal
 *  @date May 4, 2020
 *  @description: Custom implementation of a Node in a Binary Tree.
 * */

public class TreeNode<T>{
    //Each TreeNode consists of a value, a left child TreeNode and a right child TreeNode
    private T item;
    private TreeNode<T> left;
    private TreeNode<T> right;

    /**
     * TreeNode
     * This is the constructor for TreeNode. A TreeNode consists of a value and
     * may consist of a left child node and right child node.
     * @param item of type T which the TreeNode will contain.
     */
    public TreeNode(T item){
        this.item = item;
        this.left = null;
        this.right = null;
    }
    //getters and setters
    public T getItem(){
        return this.item;
    }
    public void setItem(T item){
        this.item = item;
    }
    public TreeNode<T> getLeft(){
        return this.left;
    }
    public void setLeft(TreeNode<T> item){
        this.left = item;
    }
    public TreeNode<T> getRight(){
        return this.right;
    }
    public void setRight(TreeNode<T> item){
        this.right = item;
    }

    /**
     * isLeaf
     * This method checks if a TreeNode is a leaf on a binary tree. If the node has no child TreeNodes, it is a leaf.
     * @return true if the TreeNode is a leaf. Return false if it's not.
     */
    public boolean isLeaf(){
        return (this.getLeft() == null && this.getRight() == null);
    }
}