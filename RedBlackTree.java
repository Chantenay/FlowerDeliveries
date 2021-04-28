import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//Based on implementation from COMP2100 2019 and CLRS
@SuppressWarnings("SuspiciousNameCombination")
public class RedBlackTree {
    private Node root;
    private Node nil;

    public enum Colour {
        RED,
        BLACK
    }

    public class Node {
        boolean nil;
        Colour colour;
        Delivery key;
        Node parent, left, right;

        public Node(Delivery delivery) {
            this.key = delivery;
            this.colour = Colour.RED;
            this.nil = false;
        }

        public Node() {
            this.key = null;
            this.colour = Colour.RED;
            this.nil = true;
        }

    }

    public RedBlackTree() {
        nil = new Node();
        nil.colour = Colour.BLACK;
        root = nil;
    }

    public void insert(Delivery d) {
        Node n = new Node(d);
        insert(n);
    }

    public void insert(Node z) {
        Node y = nil;
        Node x = root;
        while (x != nil) {
            y = x;
            if (z.key.deliveryID < x.key.deliveryID)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.key.deliveryID < y.key.deliveryID)
            y.left = z;
        else
            y.right = z;
        z.left = nil;
        z.right = nil;
        z.colour = Colour.RED;
        insertFixup(z);
    }

    public void insertFixup(Node z) {
        while (z.parent.colour == Colour.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.colour == Colour.RED) {
                    z.parent.colour = Colour.BLACK;
                    y.colour = Colour.BLACK;
                    z.parent.parent.colour = Colour.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.colour = Colour.BLACK;
                    z.parent.parent.colour = Colour.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (y.colour == Colour.RED) {
                    z.parent.colour = Colour.BLACK;
                    y.colour = Colour.BLACK;
                    z.parent.parent.colour = Colour.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.colour = Colour.BLACK;
                    z.parent.parent.colour = Colour.RED;
                    leftRotate(z.parent.parent);
                }
            }

        }
        root.colour = Colour.BLACK;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != nil)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == nil)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != nil)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == nil)
            root = y;
        else if (x == x.parent.right)
            x.parent.right = y;
        else
            x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    private void preOrder(Node tree) {
        if(tree != null && tree.key != null) {
            System.out.print(tree.key.deliveryID +" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrderFull(Node tree) {
        if(tree != null && tree.key != null) {
            System.out.println(tree.key);
            preOrderFull(tree.left);
            preOrderFull(tree.right);
        }
    }

    public void preOrderFull() {
        preOrderFull(root);
        System.out.println();
    }

    public Node search(int key) {
        return find(root, key);
    }

    public Delivery searchDelivery(int key) {
        Node n = find(root, key);
        if (n == null) {
            return null;
        } else {
            return n.key;
        }
    }

    private Node find(Node x, int key) {
        if (x.key == null)
            return null;

        if (key < x.key.deliveryID)
            return find(x.left, key);
        else if (key > x.key.deliveryID)
            return find(x.right, key);
        else
            return x;
    }


    public ArrayList<Delivery> toArrayList() {
        return toArrayList(root);
    }

    private ArrayList<Delivery> toArrayList(Node node) {
        ArrayList<Delivery> array = new ArrayList<>();
        if (node.key == null) {
            return new ArrayList<>();
        }

        if (node.left == null && node.right == null) {
            array.add(node.key);
            return array;
        } else if (node.left != null && node.right == null) {
            array = toArrayList(node.left);
        } else if (node.right != null && node.left == null) {
            array = toArrayList(node.right);
        } else {
            array = toArrayList(node.left);
            array.addAll(toArrayList(node.right));
        }

        array.add(node.key);

        return array;
    }

    static void logicalRedBlack() throws IOException {
        RedBlackTree tree = new RedBlackTree();
        ArrayList<Delivery> treeArray = new ArrayList<>();

        System.out.println("------------------------------------------------------------------");
        System.out.println("----------------------- Test empty RB tree -----------------------");

        System.out.println("Find node ID 18, should return null. Yours: " + (tree.search(17)));
        System.out.println("Find node ID -1, should return null. Yours: " + (tree.search(-1)));

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------- Test non-empty RB tree ---------------------");

        for(int i=0; i<20; ++i) {
            Delivery d = new Delivery();
            d.deliveryID = i;
            d.price = i;
            d.message = Integer.toString(i);
            d.recipientName = Integer.toString(i);
            d.customerName = Integer.toString(i);
            d.flowers =  Integer.toString(i);
            d.suburb = i;
            d.streetAddress =  Integer.toString(i);
            d.priority = i;
            tree.insert(d);
            treeArray.add(d);
        }

        System.out.println("Search result:");
        System.out.println("Find delivery with ID 18, should return correct delivery. Yours: " + (tree.search(18).key.deliveryID));
        System.out.println("Find delivery with ID 23, should return null. Yours: " + (tree.search(23)));
        System.out.println("Find delivery with ID -1, should return null. Yours: " + (tree.search(-1)));
        System.out.println("Find delivery with ID 1, should return correct delivery. Yours: " + (tree.search(1).key.deliveryID));

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------- Test tree to ArrayList ---------------------");

        ArrayList<Delivery> treeToArray = tree.toArrayList();

        treeArray.sort(new IDComparator());
        treeToArray.sort(new IDComparator());
        System.out.println("Tree to array list should be equivalent to array list with each node inserted at creation. Yours: " + treeArray.equals(treeToArray));

        RedBlackTree rbEmpty = new RedBlackTree();
        System.out.println("Empty tree should return empty array list. Yours: " + rbEmpty.toArrayList().equals(new ArrayList<>()));
        System.out.println("------------------------------------------------------------------");

        FlowerDeliveries.testAlgorithms();

    }

    static void empiricalRedBlack() throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.println("Which test would you like to run?");
        System.out.println("[1] Size 10 ");
        System.out.println("[2] Size 1000 ");
        System.out.println("[3] Size 60000 ");
        System.out.println("[4] Size 400000 ");
        System.out.println("[5] Size 1000000 ");

        int choice = in.nextInt();

        int size = 10;
        String test = "test-f1-1.txt";
        int[] notIn = {3, 27, 73};
        int[] isIn = {96, 8, 88};

        if  (choice == 2) {
            size = 1000;
            test = "test-f1-2.txt";
            notIn[0] = 3461;
            notIn[1] = 7199;
            notIn[2] = 7869;
            isIn[0] = 4680;
            isIn[1] = 9565;
            isIn[2] = 36;
        } else if  (choice == 3) {
            size = 60000;
            test = "test-f1-3.txt";
            notIn[0] = 31209;
            notIn[1] = 320573;
            notIn[2] = 274059;
            isIn[0] = 386571;
            isIn[1] = 126873;
            isIn[2] = 75601;
        } else if  (choice == 4) {
            size = 400000;
            test = "test-f1-4.txt";
            notIn[0] = 2974517;
            notIn[1] = 1517990;
            notIn[2] = 3582800;
            isIn[0] = 37937;
            isIn[1] = 2818004;
            isIn[2] = 250304;
        } else if (choice == 5) {
            size = 999423;
            test = "test-f1-5.txt";
            notIn[0] = 9768604;
            notIn[1] = 7125207;
            notIn[2] = 8346144;
            isIn[0] = 216298;
            isIn[1] = 2813098;
            isIn[2] = 1182451;
        }

        BufferedReader br = new BufferedReader(new FileReader(test));

        Delivery[] array = new Delivery[size];
        int i = 0;

        String st;
        while ((st = br.readLine()) != null) {
            Delivery d = new Delivery();
            d.deliveryID = Integer.parseInt(st);
            array[i] = d;
            i++;
        }
        RedBlackTree rb = new RedBlackTree();

        long startTime = System.nanoTime();

        for (Delivery k : array) {
            rb.insert(k);
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.

        System.out.println("Time taken: " + duration);


        System.out.println("Running tests where delivery with ID has been inserted: ");
        System.out.println("Find delivery with ID " + isIn[0] + ", should return correct delivery ID. Yours: " + (rb.search(isIn[0]).key.deliveryID));
        System.out.println("Find delivery with ID " + isIn[1] + ", should return correct delivery ID. Yours: " + (rb.search(isIn[1]).key.deliveryID));
        System.out.println("Find delivery with ID " + isIn[2] + ", should return correct delivery ID. Yours: " + (rb.search(isIn[2]).key.deliveryID));

        System.out.println("Running tests where delivery with ID has not been inserted: ");
        System.out.println("Find delivery with ID "+ notIn[0] + ", should return null. Yours: " + (rb.search(notIn[0])));
        System.out.println("Find delivery with ID "+ notIn[1] + ", should return null. Yours: " + (rb.search(notIn[1])));
        System.out.println("Find delivery with ID "+ notIn[2] + ", should return null. Yours: " + (rb.search(notIn[2])));

        FlowerDeliveries.testAlgorithms();

    }

    static class IDComparator implements Comparator<Delivery> {
        public int compare(Delivery o1, Delivery o2) {
            return Integer.compare(o1.deliveryID, o2.deliveryID);
        }
    }

}
