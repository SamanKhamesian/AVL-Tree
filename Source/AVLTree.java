import java.util.Stack;

public class AVLTree
{
    static Node Root;

    //********************************************** Secondary Functions *********************************************//

    /*
    * Function : getHeight
    * Description : return the height of the node
    * Order of Method : T(n) = O(1)
    */
    int getHeight(Node key)
    {
        if (key == null)
           return 0;

        else
            return key.height;
    }

    /*
    * Function : getBalance
    * Description : return the balance of the AVL tree
    * Order of Method : T(n) = O(1)
    */
    int getBalance(Node key)
    {
        if (key == null)
           return 0;

        else
            return (getHeight(key.right) - getHeight(key.left));
    }

    /*
    * Function : updateHeight
    * Description : Update the height of the node
    * Order of Method : T(n) = O(1)
    */
    void updateHeight(Node key)
    {
        int leftSubtreeHeight = getHeight(key.left);
        int rightSubtreeHeight = getHeight(key.right);

        key.height = Math.max(leftSubtreeHeight , rightSubtreeHeight) + 1;
    }

    /*
    * Function : rotateLeft
    * Description : Rotate the AVL tree to left
    * Order of Method : T(n) = O(1)
    */
    Node rotateLeft(Node oldRoot)
    {
        Node newRoot = oldRoot.right;
        Node temp = newRoot.left;

        newRoot.left = oldRoot;
        oldRoot.right = temp;

        updateHeight(oldRoot);
        updateHeight(newRoot);

        return newRoot;
    }


    /*
    * Function : rotateRight
    * Description : Rotate the AVL tree to right
    * Order of Method : T(n) = O(1)
    */
    Node rotateRight(Node oldRoot)
    {
        Node newRoot = oldRoot.left;
        Node temp = newRoot.right;

        newRoot.right = oldRoot;
        oldRoot.left = temp;

        updateHeight(oldRoot);
        updateHeight(newRoot);

        return newRoot;
    }


    /*
    * Function : rotateRight
    * Description : Rotate the AVL tree to right
    * Order of Method : T(n) = O(1)
    */
    Node balanceTree(Node root)
    {
        updateHeight(root);

        int balance = getBalance(root);

        if (balance == 2)
        {
            if (getBalance(root.right) < 0)
                root.right = rotateRight(root.right);

            return rotateLeft(root);
        }

        if (balance == -2)
        {
            if (getBalance(root.left) > 0)
                root.left = rotateLeft(root.left);

            return rotateRight(root);
        }

        return root;
    }


    /*
    * Function : insertNode
    * Description : Insert a new node to the AVL tree
    * Order of Method : T(n) = O(log(n))
    */
    Node insertNode(Node root, String key)
    {
        if (root == null)
            return new Node(key);

        else if (key.compareToIgnoreCase(root.name) < 0)
            root.left = insertNode(root.left, key);

        else
            root.right = insertNode(root.right, key);

        return balanceTree(root);
    }

    /*
    * Function : findSuccessor
    * Description : find a smallest key in right-subtree
    * Order of Method : T(n) = O(log(h))
    */
    Node findSuccessor(Node root)
    {
        if (root.left != null)
            return findSuccessor(root.left);

        else
            return root;
    }

    /*
    * Function : removeNode
    * Description : remove key from AVL tree
    * Order of Method : T(n) = O(log(n))
    */
    Node removeNode(Node root, String key)
    {
        if (root == null)
            return root;

        else if (key.compareToIgnoreCase(root.name) < 0)
            root.left = removeNode(root.left, key);

        else if (key.compareToIgnoreCase(root.name) > 0)
            root.right = removeNode(root.right, key);

        else
        {
            if (root.right == null)
                root = root.left;

            else if (root.left == null)
                root = root.right;

            else
            {
                Node temp = findSuccessor(root.right);
                root.name = temp.name;
                root.right = removeNode(root.right, root.name);
            }
        }

        if (root == null)
            return root;

        else
            return balanceTree(root);
    }

    //************************************************ Main Functions ************************************************//

    /*
    * Function : search
    * Description : Binary-Search in AVL tree
    * Order of Method : T(n) = O(log(n))
    */
    Node search(Node root, String key)
    {
        if (root == null || key.compareToIgnoreCase(root.name) == 0)
            return root;

        if (key.compareTo(root.name) < 0)
            return search(root.left, key);

        else
            return search(root.right, key);
    }

     /*
    * Function : add
    * Description : Add a new Name into AVL tree
    * Order of Method : T(n) = O(log(n))
    */
    void add(String key)
    {
        if (search(Root , key) == null)
        {
            Root = insertNode(Root , key);
            System.out.println("\n" + key + " added successfully :)");
        }

        else
            System.out.println("\n" + key + " has been added :(");
    }

    /*
    * Function : delete
    * Description : Delete a Name from AVL tree
    * Order of Method : T(n) = O(log(n))
    */
    void delete(String key)
    {
        if (search(Root , key) != null)
        {
            Root = removeNode(Root , key);
            System.out.println("\n" + key + " deleted successfully :)");
        }

        else
            System.out.println("\n" + key + " not found in AVL Tree :(");
    }

    /*
   * Function : depth
   * Description : return the depth of key
   * Order of Method : T(n) = O(log(n))
   */
    int depth(String key)
    {
        Node temp = search(Root, key);

        if (temp == null)
            return -1;

        else
        {
            System.out.println("\n" + "The depth of " + key + " is : " + (Root.height - temp.height));
            return (Root.height - temp.height);
        }
    }

    /*
    * Function : showingPreOrder
    * Description : Showing Pre-Order of AVL tree
    * Order of Method : T(n) = O(n)
    */
    void showingPreOrder(Node key)
    {
        System.out.print("\nPre-Order : ");
        Stack <Node> stack = new Stack <Node>();

        if (key == null)
            return;

        else
        {
            stack.push(key);

            while (!stack.empty())
            {
                key = stack.pop();
                System.out.print(key.name + " ");

                if (key.right != null)
                    stack.push(key.right);

                if (key.left != null)
                    stack.push(key.left);
            }
        }

        System.out.println();
    }

    /*
    * Function : showingInOrder
    * Description : Showing In-Order of AVL tree
    * Order of Method : T(n) = O(n)
    */
    void showingInOrder(Node key)
    {
        System.out.print("\nIn-Order  : ");
        Stack <Node> stack = new Stack <Node>();

        while (!stack.empty() || key != null)
        {
            if (key != null)
            {
                stack.push(key);
                key = key.left;
            }

            else
            {
                key = stack.pop();
                System.out.print(key.name + " ");
                key = key.right;
            }
        }

        System.out.println();
    }
}