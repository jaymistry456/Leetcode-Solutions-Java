// https://www.geeksforgeeks.org/problems/inorder-successor-in-bst/1


/*
Definition for Node
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
};
*/

class Solution {
    public int inOrderSuccessor(Node root, Node k) {
        // code here
        Node result = null;
        Node curr = root;
        
        while(curr != null) {
            if (curr.data > k.data) {
                result = curr;
                curr = curr.left;
            } else {
               curr = curr.right; 
            }
        }
        
        return result == null ? -1 : result.data;
    }
}