import java.util.Comparator;

public class Delivery {
    int priority;
    int deliveryID;
    String streetAddress;
    int suburb;
    String flowers;
    String customerName;
    String recipientName;
    String message;
    int price;

    public Delivery() {

    }

    @Override
    public String toString() {
        return "Priority: " + priority + ", ID: " + deliveryID + ", Address: " + streetAddress
                + ", Suburb: " + suburb + ", Flowers: " + flowers + ", Customer Name: " + customerName
                + ", Recipient Name:  " + recipientName + ", Message: " + message + ", Price: " + price;
    }

}

