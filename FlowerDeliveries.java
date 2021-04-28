import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class FlowerDeliveries {

    public static RedBlackTree redBlackTree = new RedBlackTree();
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("┌───────────────── •✧✧• ─────────────────┐");
        System.out.println(" -           Delivery Generator          - ");
        System.out.println("└───────────────── •✧✧• ─────────────────┘");

        System.out.println("[1] Enter today's Deliveries: ");
        System.out.println("[2] Run schedule test: ");
        System.out.println("[3] Run algorithm tests: ");

        int choice = in.nextInt();

        //Deliveries
        if (choice == 1) {
            enterDelivery();
        } else if  (choice == 2) {
            testSchedule();
        } else {
            testAlgorithms();
        }

    }

    static void testAlgorithms() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Select algorithm to test: ");
        System.out.println("[1] Red Black Tree Tests ");
        System.out.println("[2] Dijkstra Tests ");
        System.out.println("[3] Priority Queue Tests ");

        int choice = in.nextInt();

        if (choice == 1) {
            System.out.println("[1] Basic Logic Tests ");
            System.out.println("[2] Empirical Analysis ");
            int choice2 = in.nextInt();

            if (choice2 == 1) {
                RedBlackTree.logicalRedBlack();
            } else {
                RedBlackTree.empiricalRedBlack();
            }

        } else if  (choice == 2) {
            System.out.println("[1] Basic Logic Tests ");
            System.out.println("[2] Empirical Analysis ");
            int choice2 = in.nextInt();

            if (choice2 == 1) {
                Dijkstra.logicalDijkstra();
            } else {
                Dijkstra.empiricalDijkstra();
            }


        } else {
            System.out.println("[1] Basic Logic Tests ");
            System.out.println("[2] Empirical Analysis ");
            int choice2 = in.nextInt();

            if (choice2 == 1) {
                MyPriorityQueue.logicalPriorityQueue();
            } else {
                MyPriorityQueue.empiricalPriorityQueue();
            }

        }

    }

    static void enterDelivery() {
        Scanner in = new Scanner(System.in);
        //Add delivery
        Delivery delivery = new Delivery();
        System.out.print("Enter the priority (0 lowest, 10 highest): ");
        delivery.priority = Integer.valueOf(in.nextLine());
        System.out.print("Enter order ID: ");
        delivery.deliveryID = Integer.valueOf(in.nextLine());
        System.out.print("Enter the street address: ");
        delivery.streetAddress = in.nextLine();
        System.out.print("Enter the flowers: ");
        delivery.flowers = in.nextLine();
        System.out.print("Enter the suburb (according to graph, e.g. Downer = 0): ");
        delivery.suburb = Integer.valueOf(in.nextLine());
        System.out.print("Enter the customer name: ");
        delivery.customerName = in.nextLine();
        System.out.print("Enter the recipient name: ");
        delivery.recipientName = in.nextLine();
        System.out.print("Enter the message: ");
        delivery.message = in.nextLine();
        System.out.print("Enter the price: ");
        delivery.price = Integer.valueOf(in.nextLine());
        System.out.print("Delivery added: \n" + delivery.toString());

        redBlackTree.insert(delivery); 

        System.out.println("\nEnter another?");
        System.out.print("[1] Yes\n");
        System.out.print("[2] No\n");

        int choice = in.nextInt();

        //Deliveries
        if (choice == 1) {
            enterDelivery();
        } else {
            mainMenu();
        }
    }

    static void mainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nWhat would you like to do? \n");
        System.out.print("[1] Manage Deliveries\n");
        System.out.print("[2] Generate schedule\n");
        System.out.print("[3] Exit\n");
        System.out.print("Enter your choice (1-3): ");
        int choice = in.nextInt();

        switch (choice) {
            //Deliveries
            case 1 -> deliveryMenu();
            //Generate Schedule
            case 2 -> {

                generateSchedule();
            }
            //Exit
            case 3 -> exit(0);
            //Invalid input
            default -> {
                System.out.print("\nPlease enter valid choice: ");
                mainMenu();
            }
        }

    }

    static void deliveryMenu() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nWhat would you like to do? \n");
        System.out.print("[1] Add delivery \n");
        System.out.print("[2] List deliveries \n");
        System.out.print("[3] Look-up delivery \n");
        //System.out.print("[4] Edit delivery \n");
        System.out.print("[4] Back \n");
        System.out.print("[5] Exit \n");
        System.out.print("Enter your choice (1-5): ");
        int choice = Integer.valueOf(in.nextLine());

        switch (choice) {
            case 1 -> {
                //Add delivery
                Delivery delivery = new Delivery();
                System.out.print("Enter the priority (0 lowest, 10 highest): ");
                delivery.priority = Integer.valueOf(in.nextLine());
                System.out.print("Enter order ID: ");
                delivery.deliveryID = Integer.valueOf(in.nextLine());
                System.out.print("Enter the street address: ");
                delivery.streetAddress = in.nextLine();
                System.out.print("Enter the flowers: ");
                delivery.flowers = in.nextLine();
                System.out.print("Enter the suburb (according to graph, e.g. Downer = 0): ");
                delivery.suburb = Integer.valueOf(in.nextLine());
                System.out.print("Enter the customer name: ");
                delivery.customerName = in.nextLine();
                System.out.print("Enter the recipient name: ");
                delivery.recipientName = in.nextLine();
                System.out.print("Enter the message: ");
                delivery.message = in.nextLine();
                System.out.print("Enter the price: ");
                delivery.price = Integer.valueOf(in.nextLine());
                System.out.print("Delivery added: \n" + delivery.toString());
                redBlackTree.insert(delivery); 
            }
            case 2 -> {
                //List deliveries
                System.out.print("\nList deliveries\n");
                redBlackTree.preOrderFull();
            }
            case 3 -> {
                System.out.print("\nLook-up delivery\n");
                System.out.print("Enter the delivery ID\n");
                int lookUpID = in.nextInt();
                Delivery n = redBlackTree.searchDelivery(lookUpID);
                if (n != null) {
                    System.out.println(n);
                } else {
                    System.out.println("Invalid ID");
                }
            }
            case 4 -> mainMenu();
            case 5 -> exit(0);
            default -> {
                System.out.print("\nPlease enter valid choice: ");
                deliveryMenu();
            }
        }
        deliveryMenu();
    }

    static void generateSchedule() {
        Scanner in = new Scanner(System.in);

        //Transform RB tree so values can be inserted to priority queue
        ArrayList<Delivery> RBTreeArray = redBlackTree.toArrayList();

        //Check if any deliveries have actually been added
        if(RBTreeArray.size() == 0) {
            System.out.println("\nAdd deliveries first");
            deliveryMenu();
        }

        System.out.print("\nSchedule :\n");

        //Create priority queue
        MyPriorityQueue priorityQueue = new MyPriorityQueue(RBTreeArray.size());

        //Loop through queue and add values to PQ
        for (Delivery i : RBTreeArray) {
            priorityQueue.insert(i);
        }

        //Generate schedule
        ArrayList<Delivery> schedule = new ArrayList<>();
        ArrayList<Delivery> currentDeliveries = new ArrayList<>();

        //Start calculations
        Delivery current = priorityQueue.extractMax();
        currentDeliveries.add(current);

        int currentLocation = 6; //Starting at Ainslie (6th in array) (where flower shop is located)

        // used to check if we've found all deliveries of current highest priority
        boolean foundAll;

        //while we're still processing deliveries
        while (priorityQueue.size > 0 || currentDeliveries.size() > 0) {
            int currentPriority = current.priority;
            foundAll = false;

            //find all of current highest priority
            //if we've haven't found all of current priority or we haven't looked at all deliveries
            while (!foundAll && priorityQueue.size > 0) {
                if(priorityQueue.peekMax().priority < currentPriority) {
                    foundAll = true;
                } else {
                    Delivery found = priorityQueue.extractMax();
                    currentDeliveries.add(found);
                }
            }

            Delivery minDelivery = null;

            //System.out.println(currentDeliveries);

            if(currentDeliveries.size() == 0) {
                schedule.add(current);
                currentDeliveries.remove(current);
                currentLocation = current.suburb;
            } else {
                while (currentDeliveries.size() > 0) {
                    //Find the one closest to us
                    int minDistance = Integer.MAX_VALUE;
                    int[] dijkstraArray = Dijkstra.findDistance(currentLocation, Dijkstra.graph);
                    for (Delivery d : currentDeliveries) {
                        if (dijkstraArray[d.suburb] < minDistance) {
                            minDistance = dijkstraArray[d.suburb];
                            minDelivery = d;
                        }
                    }
                    //System.out.println(i + " " + currentDeliveries.toString());
                    schedule.add(minDelivery);
                    currentDeliveries.remove(minDelivery);
                    currentLocation = minDelivery.suburb;
                }
            }

            if (priorityQueue.size > 0) {
                current = priorityQueue.extractMax();
                currentDeliveries.add(current);
            }

            //System.out.println("current "+ current);

        }

        //print outcome
        for (Delivery x : schedule) {
            System.out.println(x);
        }

        //present menu

        System.out.print("\nWhat would you like to do? \n");
        System.out.print("[1] Menu \n");
        System.out.print("[2] Exit \n");
        System.out.print("Enter your choice (1-2): ");
        int choice;
        choice = in.nextInt();

        if (choice == 1) {//Add customer
            mainMenu();
        } else {//Invalid input
            exit(0);
        }

    }

    static void testSchedule() {

        Delivery d = new Delivery();
        d.priority = 10;
        d.deliveryID = 1;
        d.streetAddress = "8 Something Street";
        d.suburb = 6;
        d.flowers = "flowers";
        d.customerName = "first";
        d.recipientName = "first";
        d.message = "hi";
        redBlackTree.insert(d);

        Delivery d1 = new Delivery();
        d1.priority = 10;
        d1.deliveryID = 2;
        d1.streetAddress = "10 Blah St";
        d1.suburb = 1;
        d1.flowers = "flowers";
        d1.customerName = "second";
        d1.recipientName = "second";
        d1.message = "hi 2";
        redBlackTree.insert(d1);

        Delivery d2 = new Delivery();
        d2.priority = 9;
        d2.deliveryID = 3;
        d2.streetAddress = "58 Flower Cres";
        d2.suburb = 0;
        d2.flowers = "flowers";
        d2.customerName = "third";
        d2.recipientName = "third";
        d2.message = "hi 3";
        redBlackTree.insert(d2);

        Delivery d3 = new Delivery();
        d3.priority = 8;
        d3.deliveryID = 4;
        d3.streetAddress = "90 A Ave";
        d3.suburb = 12;
        d3.flowers = "flowers";
        d3.customerName = "fourth";
        d3.recipientName = "fourth";
        d3.message = "hi 4";
        redBlackTree.insert(d3);

        Delivery d4 = new Delivery();
        d4.priority = 10;
        d4.deliveryID = 5;
        d4.streetAddress = "6 B Blvd";
        d4.suburb = 10;
        d4.flowers = "flowers";
        d4.customerName = "fifth";
        d4.recipientName = "fifth";
        d4.message = "hi 5";
        redBlackTree.insert(d4);

        Delivery d5 = new Delivery();
        d5.priority = 1;
        d5.deliveryID = 6;
        d5.streetAddress = "10 Holt Street";
        d5.suburb = 7;
        d5.flowers = "flowers";
        d5.customerName = "Holt";
        d5.recipientName = "Jake";
        d5.message = "hi Holt";
        redBlackTree.insert(d5);

        Delivery d6 = new Delivery();
        d6.priority = 1;
        d6.deliveryID = 7;
        d6.streetAddress = "1 Sad Street";
        d6.suburb = 11;
        d6.flowers = "flowers";
        d6.customerName = "Happy";
        d6.recipientName = "Sad";
        d6.message = "from Happy";
        redBlackTree.insert(d6);

        Delivery d7 = new Delivery();
        d7.priority = 5;
        d7.deliveryID = 8;
        d7.streetAddress = "13 Scary Street";
        d7.suburb = 12;
        d7.flowers = "flowers";
        d7.customerName = "Jack";
        d7.recipientName = "Freddie";
        d7.message = "from happy Friday";
        redBlackTree.insert(d7);

        generateSchedule();

    }


}
