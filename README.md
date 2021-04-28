# Background
Developed for ANU's Algorithms (COMP3600) Course in S2 2020. It uses red-black trees, Dijkstra's algorithm and priority queues. 

# Application Description:
The Flower Delivery software allows a flower delivery company to schedule their deliveries. The company needs to be able to add and look-up deliveries through the software, which is accomplished by storing deliveries in a red-black tree.
The flower delivery company also want the schedule to be in order of delivery priority, where the highest priority deliveries are scheduled first, in order of how close they are to the previous delivery location. This is accomplished by the software through a max-heap and using Dijkstra’s algorithm to find which delivery is closest to the previously scheduled delivery, or the store’s location if no deliveries have been scheduled yet.
Functionalities:
- Adding deliveries
- Looking up deliveries
- Listing deliveries
- Generating delivery schedule

# How to use:
To compile and run the program:
Execute "javac FlowerDeliveries.java" INSIDE THE FOLDER to compile the program.
Execute "java FlowerDeliveries" INSIDE THE FOLDER to run the program.

When the program is running, you will be greeted with the options: 
[1] Enter todays deliveries
[2] Run schedule test
[3] Run algorithm test

**Entering 1 will take you to the delivery input screen:**
    You cannot return to this menu from here. Here you can input new deliveries. First, enter the priority. It must be an integer between 0 and 10 (inclusive). Then enter an order ID. This must be unique, but has no size limitations. Then enter the address (any string) and flowers (any string). Then enter the suburb. This must be an integer from 0 to 12 (inclusive), where each integer corresponds to a suburb the flower shop delivers to:
    0 corresponds to Downer,
    1 corresponds to Watson,
    2 corresponds to Lineham. 
    3 corresponds to Dickson, 
    4 corresponds to Hackett, 
    and so on (full list is in Dijkstra.java).
    Then enter the customer name (any string), recipient name (any string), message (any string), price (integer). You can then enter more deliveries by entering 1, or go to the main menu by pressing 2. 

**Entering 2** will automatically add deliveries to the red-black delivery storage tree. It will also generate a schedule for these deliveries. This was used to make sure schedule generation was behaving correctly. The added deliveries are persistent, so can be used to test delivery lookup and list functionality. You can then close the program or go to the main menu. 

**Entering 3** will take you to the algorithm testing screen. Here you can test each algorithm on different input sizes, or run the basic tests used to debug the algorithms and ensure correct working. Running one of the tests used for empirical analysis shows some test cases used to ensure correct output as well as the run time.
    Dijkstra has an extra test, as when the graph grows large, because it becomes so interconneted, the minimum distances between nodes become close to the minimum (minimum distance between nodes being 3, maximum being 15 in the test graph). The extra test (6) is to show how less connected graphs have higher travel times between nodes which are not directly connected. 

**Main Menu:**
Entering 1 will take you to the manage delivery page: 
    Entering 1 again will allow you to add another delivery. 
    Entering 2 will list all the deliveries in tree-traversal order. 
    Entering 3 will take you to the delivery look-up page:
        Entering a valid ID will print the corresponding delivery.
        Entering an invalid ID will print "Invalid ID"
    Entering 4 will take you back to the main menu
    Entering 5 will close the program

Entering 2 will generate a schedule. 

Entering 3 will close the program. 
