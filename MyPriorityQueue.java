import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

//Based on sudo-code implementation in [CLRS]
public class MyPriorityQueue {
    private static int queueSize = 100;
    private Delivery [] heap;
    int size;

    public MyPriorityQueue() {
        heap = new Delivery[queueSize];
        size = 0;
    }

    public MyPriorityQueue(int newSize) {
        queueSize = newSize;
        heap = new Delivery[queueSize];
        size = 0;
    }

    //Returns the value of the max item
    //Page 163 CLRS
    Delivery peekMax() {
        if(size > 0)
            return heap[0];
        return null;
    }

    //Returns the max item (and removes from Queue)
    //Page 163 CLRS
    Delivery extractMax() {
        if(size == 0)
            return null;
        Delivery maxItem = heap[0];
        heap[0] = heap[size - 1];
        size = size - 1;
        maxHeapify(0);
        return maxItem;
    }

    //Helper functions from page 152 CLRS
    //Returns index of parent
    public static int parent(int i) {
        return (i - 1) / 2;
    }
    //Returns index of left child
    public static int left(int i) {
        return 2*i + 1;
    }
    //Returns index of right child
    public static int right(int i) {
        return 2 * i + 2;
    }

    //Inserts new delivery into priority queue
    //Based on sudo-code page 164 CLRS
    public void insert(Delivery delivery) {
        if (size >= queueSize) {
            throw new ArrayStoreException("queue is full");
        }
        heap[size] = delivery;
        size = size + 1;
        int i = size - 1;
        while (i != 0 && heap[MyPriorityQueue.parent(i)].priority < heap[i].priority) {
            //Taken from "Heap-Increase-Key" page 164
            Delivery temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    //Used to maintain the max-heap property
    //Page 154 CLRS
    public void maxHeapify(int i){
        int left = left(i);
        int right = right(i);
        int largest = i;
        if (left <= size && heap[left].priority > heap[largest].priority) {
            largest = left;
        }
        if (right <= size && heap[right].priority > heap[largest].priority) {
            largest = right;
        }
        if (largest != i) {
            Delivery temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest);
        }
    }

    static void logicalPriorityQueue() throws IOException {
        MyPriorityQueue queue = new MyPriorityQueue();

        //Testing priority queue:
        //Test one input:
        //Test size = 0
        System.out.println("------------------------------------------------");
        System.out.println("----------Test adding and removing one----------");

        System.out.println("Size should be 0, your size: " + queue.size);
        //Add one item
        Delivery d = new Delivery();
        d.priority = 10;
        d.deliveryID = 1;
        d.streetAddress = "8 Something Street";
        d.suburb = 6;
        d.flowers = "flowers";
        d.customerName = "first";
        d.recipientName = "first";
        d.message = "hi";
        queue.insert(d);
        System.out.println("Size should be 1, your size: " + queue.size);
        System.out.println("Peek max should be 10 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 10, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 0, your size: " + queue.size);


        System.out.println("------------------------------------------------");
        System.out.println("----------- Test right order removal -----------");
        //Testing removal in right order
        Delivery d1 = new Delivery();
        d1.priority = 10;
        queue.insert(d1);

        System.out.println("Size should be 1, your size: " + queue.size);

        Delivery d2 = new Delivery();
        d2.priority = 9;
        queue.insert(d2);

        System.out.println("Size should be 2, your size: " + queue.size);

        Delivery d3 = new Delivery();
        d3.priority = 8;
        queue.insert(d3);

        System.out.println("Size should be 3, your size: " + queue.size);

        Delivery d4 = new Delivery();
        d4.priority = 10;
        queue.insert(d4);

        System.out.println("Size should be 4, your size: " + queue.size);

        Delivery d5 = new Delivery();
        d5.priority = 1;
        queue.insert(d5);

        System.out.println("Size should be 5, your size: " + queue.size);

        Delivery d6 = new Delivery();
        d6.priority = 1;
        queue.insert(d6);

        System.out.println("Size should be 6, your size: " + queue.size);

        Delivery d7 = new Delivery();
        d7.priority = 5;
        queue.insert(d7);

        System.out.println("Size should be 7, your size: " + queue.size);

        System.out.println("Peek max should be 10 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 10, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 6, your size: " + queue.size);

        System.out.println("Peek max should be 10 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 10, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 5, your size: " + queue.size);

        System.out.println("Peek max should be 9 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 9, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 4, your size: " + queue.size);

        System.out.println("Peek max should be 8 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 8, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 3, your size: " + queue.size);

        System.out.println("Peek max should be 5 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 5, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 2, your size: " + queue.size);


        System.out.println("Peek max should be 1 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 1, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 1, your size: " + queue.size);

        System.out.println("Peek max should be 1 your max: " + queue.peekMax().priority);
        System.out.println("Extract max should be 1, your max: " + queue.extractMax().priority);
        System.out.println("Size should be 0, your size: " + queue.size);

        System.out.println("------------------------------------------------");
        System.out.println("- Test trying to remove when nothing in queue --");

        System.out.println("Peek max should return null, your peek max: " + queue.peekMax());
        System.out.println("Extract max should return null, your max: " + queue.extractMax());

        System.out.println("------------------------------------------------");


        FlowerDeliveries.testAlgorithms();

    }


    static void empiricalPriorityQueue() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Which test would you like to run?");
        System.out.println("[1] Size 10 ");
        System.out.println("[2] Size 10000 ");
        System.out.println("[3] Size 100000 ");
        System.out.println("[4] Size 400000 ");
        System.out.println("[5] Size 1000000 ");

        int choice = in.nextInt();

        int size = 10;
        String test = "test-f3-1.txt";

        if  (choice == 2) {
            size = 10000;
            test = "test-f3-2.txt";
        } else if  (choice == 3) {
            size = 100000;
            test = "test-f3-3.txt";
        } else if  (choice == 4) {
            size = 400000;
            test = "test-f3-4.txt";
        } else if (choice == 5) {
            size = 1000000;
            test = "test-f3-5.txt";
        }

        BufferedReader br = new BufferedReader(new FileReader(test));

        Delivery[] array = new Delivery[size];
        int i = 0;

        String st;
        while ((st = br.readLine()) != null) {
            Delivery d = new Delivery();
            d.deliveryID = Integer.parseInt(st);
            st = br.readLine();
            d.priority = Integer.parseInt(st);
            array[i] = d;
            i++;
        }

        //System.out.println(Arrays.toString(array));

        MyPriorityQueue pq = new MyPriorityQueue(size);

        long startTimeInsert = System.nanoTime();

        for (Delivery k : array) {
            pq.insert(k);
        }

        long endTimeInsert = System.nanoTime();

        long durationInsert = (endTimeInsert - startTimeInsert);  //divide by 1000000 to get milliseconds.


        Delivery previous;
        Delivery current;

        int removed = 1;

        boolean retrievedInOrder = true;

        long startTimeRemove = System.nanoTime();

        previous = pq.extractMax();

        while ( (current = pq.extractMax()) != null ) {
            removed ++;
            if(current.priority > previous.priority)
                retrievedInOrder = false;
            previous = current;
        }

        long endTimeRemove = System.nanoTime();

        long durationRemove = (endTimeRemove - startTimeRemove);  //divide by 1000000 to get milliseconds.

        System.out.println("Time taken to add: " + durationInsert);
        System.out.println("Time taken to remove: " + durationRemove);
        System.out.println("Test that items were removed in priority order: " + retrievedInOrder);
        retrievedInOrder = removed == size;
        System.out.println("Test that correct number of items was removed: " + retrievedInOrder);

        FlowerDeliveries.testAlgorithms();

    }

}
