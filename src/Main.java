import java.util.LinkedList;
import java.util.Queue;

class Node
{
    int value;
    Node left;
    Node right;
    public Node(int value)
    {
        this.value = value;
    }
}
class BST
{
    Node root;
    public BST(int value)
    {
        this.root = new Node(value);
    }

    public void insert(int val)
    {
        this.root = insert(this.root, val);
    }
    private Node insert(Node node, int val)
    {
        if(node == null)
        {
            return new Node(val);
        }
        if(val < node.value)
        {
            node.left = insert(node.left, val);
        }
        if(val > node.value)
        {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public boolean searchRecursive(int val)
    {
        return this.searchRecursive(this.root, val);
    }
    private boolean searchRecursive(Node node, int val)
    {
        if(node == null)
        {
            return false;
        }
        if(node.value < val)
        {
            return searchRecursive(node.right, val);
        }
        else if(node.value > val)
        {
            return searchRecursive(node.left, val);
        }
        return true;
    }
    public boolean searchIterative(int val)
    {
        Node current = this.root;
        while(current != null)
        {
            if(current.value == val)
            {
                return true;
            }
            else if(current.value > val)
            {
                current = current.left;
            }
            else
            {
                current = current.right;
            }
        }
        return false;
    }
    public void inOrderTraverse()
    {
        this.inOrderTraverse(this.root);
    }
    private void inOrderTraverse(Node node)
    {
        if(node == null)
        {
            return;
        }
        inOrderTraverse(node.left);
        System.out.print(node.value + " ");
        inOrderTraverse(node.right);
    }
    public void preOrderTraverse()
    {
        this.preOrderTraverse(this.root);
    }
    private void preOrderTraverse(Node node)
    {
        if(node == null)
        {
            return;
        }
        System.out.print(node.value + " ");
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }
    public void postOrderTraverse()
    {
        this.postOrderTraverse(this.root);
    }
    private void postOrderTraverse(Node node)
    {
        if(node == null)
        {
            return;
        }
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.value + " ");
    }

    public int getMinRecursive()
    {
        if(this.root == null)
        {
            throw new RuntimeException("Invalid");
        }
        return this.getMinRecursive(this.root);
    }
    private int getMinRecursive(Node node)
    {
        if(node.left == null)
        {
            return node.value;
        }
        return getMinRecursive(node.left);
    }
    public int getMaxRecursive()
    {
        if(this.root == null)
        {
            throw new RuntimeException("Invalid");
        }
        return this.getMaxRecursive(this.root);
    }
    private int getMaxRecursive(Node node)
    {
        if(node.right == null)
        {
            return node.value;
        }
        return getMaxRecursive(node.right);
    }

    public int getMinIterative()
    {
        if(this.root == null)
        {
            throw new RuntimeException("Invalid");
        }
        Node current = this.root;
        while(current.left != null)
        {
            current = current.left;
        }
        return current.value;
    }
    public int getMaxIterative()
    {
        if(this.root == null)
        {
            throw new RuntimeException("Invalid");
        }
        Node current = this.root;
        while(current.right != null)
        {
            current = current.right;
        }
        return current.value;
    }


    public int getHeightRecursive()
    {
        return this.getHeightRecursive(this.root);
    }
    private int getHeightRecursive(Node node)
    {
        if(node == null)
        {
            return -1;
        }
        return 1 + Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right));
    }
    public int getHeightIterative()
    {
        Node current = this.root;
        int max = -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(current);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i = 0; i < size; ++i)
            {
                Node temp = queue.poll();
                if(temp.left != null)
                {
                    queue.add(temp.left);
                }
                if(temp.right != null)
                {
                    queue.add(temp.right);
                }
            }
            ++max;
        }

        return max;
    }
    public int getSuccessor(Node node)
    {
        if(node.right != null)
        {
            return getMinRecursive(node);
        }
        Node successor = null;
        Node ancestor = this.root;
        while(node != ancestor)
        {
            if(node.value < ancestor.value)
            {
                successor = ancestor;
                ancestor = ancestor.left;
            }
            else
            {
                ancestor = ancestor.right;
            }
        }
        return successor.value;

    }
    public int getPredecessor(Node node)
    {
        if(node.left != null)
        {
            return getMaxRecursive(node.left);
        }
        Node ancestor = this.root;
        Node predecessor = null;
        while(node != ancestor)
        {
            if(node.value < ancestor.value)
            {
                ancestor = ancestor.left;
            }
            else
            {
                predecessor = ancestor;
                ancestor = ancestor.right;
            }
        }
        return predecessor.value;
    }
    public void delete(int val)
    {
        this.delete(this.root, val);
    }
    private Node delete(Node node, int key)
    {
        if(node == null)
        {
            return null;
        }
        if(node.value < key)
        {
            node.right = delete(node.right, key);
        }
        else if(node.value > key)
        {
            node.left = delete(node.left, key);
        }
        else
        {
            if(node.left == null)
            {
                Node temp = node.right;
                return temp;
            }
            else if(node.right == null)
            {
                Node temp = node.left;
                return temp;
            }
            else
            {
                int temp = getMaxRecursive(node.left);
                node.value = temp;
                node.left = delete(node.left, temp);
            }
        }
        return node;
    }


    public void levelOrder()
    {
        if (root == null)
        {
            System.out.println("BST is empty.");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while(!queue.isEmpty())
        {
            Node temp = queue.poll();
            System.out.print(temp.value + " ");
            if(temp.left != null)
            {
                queue.add(temp.left);
            }
            if(temp.right != null)
            {
                queue.add(temp.right);
            }
        }
    }
}


public class Main
{
    public static void main(String[] args)
    {

        BST bst = new BST(10);

        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);

        System.out.println("In-order traversal:");
        bst.inOrderTraverse();

        System.out.println("Pre-order traversal:");
        bst.preOrderTraverse();

        System.out.println("Post-order traversal:");
        bst.postOrderTraverse();

        System.out.println("Search for value 7 (Recursive): " + bst.searchRecursive(7));
        System.out.println("Search for value 9 (Recursive): " + bst.searchRecursive(9));

        System.out.println("Search for value 7 (Iterative): " + bst.searchIterative(7));
        System.out.println("Search for value 9 (Iterative): " + bst.searchIterative(9));

        System.out.println("Minimum value (Recursive): " + bst.getMinRecursive());
        System.out.println("Maximum value (Recursive): " + bst.getMaxRecursive());

        System.out.println("Minimum value (Iterative): " + bst.getMinIterative());
        System.out.println("Maximum value (Iterative): " + bst.getMaxIterative());

        System.out.println("Height of the tree (Recursive): " + bst.getHeightRecursive());
        System.out.println("Height of the tree (Iterative): " + bst.getHeightIterative());

        System.out.println("Level-order traversal:");
        bst.levelOrder();


        System.out.println("Deleting node with value 10:");
        bst.delete(10);

        System.out.println("In-order traversal after deletion:");
        bst.inOrderTraverse();
    }
}