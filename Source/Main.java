public class Main
{
    public static void main(String[] args)
    {
        AVLTree tree = new AVLTree();

        tree.add("Amanda");
        tree.add("Harry");
        tree.add("Harry");
        tree.add("Xavi");
        tree.add("Hector");
        tree.add("Mary");
        tree.add("Ron");

        tree.showingInOrder(tree.Root);
        tree.showingPreOrder(tree.Root);

        tree.delete("Ron");
        tree.search(tree.Root, "Mary");
        tree.delete("Mary");

        tree.showingInOrder(tree.Root);
        tree.showingPreOrder(tree.Root);
    }
}