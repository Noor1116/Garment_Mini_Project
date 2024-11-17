
package garmentminiproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;



class Garment {
    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
double calculateDiscountPrice(double discountPercentage) {
        return price * (1 - discountPercentage / 100);
    }
}


class Order {
    public String orderId;
    public Date orderDate;
    public List<Garment> garments = new ArrayList<>();
    private double totalAmount;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    double calculateTotalAmount(double discountPercentage) {
        totalAmount = 0;
        for (Garment g : garments) {
            totalAmount += g.calculateDiscountPrice(discountPercentage);
        }
        return totalAmount;
    }

    void printOrderDetails(Customer customer, double discountPercentage) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("----------------------- Invoice ------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + sdf.format(orderDate));
        System.out.println("---------------------------------------------------------");
        System.out.println("Billed to:");
        System.out.println("---------------------------------------------------------");
        System.out.println("Name: " + customer.name);
        System.out.println("Email: " + customer.email);
        System.out.println("Phone: " + customer.phone);
        System.out.println("Address: " + customer.address);
        
        System.out.println("\nItems:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-10s\n", "Item", "Quantity", "Unit Price", "Total");
        System.out.println("---------------------------------------------------------");
        
        double subTotal = 0;
        for (Garment g : garments) {
            double itemTotal = g.price;
            subTotal += itemTotal;
            System.out.printf("%-15s %-10d %-15.2f %-10.2f\n", g.name, 1, g.price, itemTotal);
        }
        
        System.out.println("---------------------------------------------------------");
        System.out.printf("Subtotal: %40.2f\n", subTotal);
        double discountedTotal = calculateTotalAmount(discountPercentage);
        System.out.printf("Discount Applied: %30.1f%%\n", discountPercentage);
        System.out.printf("Discounted Price: %32.2f\n", subTotal - discountedTotal);
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total Amount after Discount: %21.2f\n", discountedTotal);
        System.out.println("---------------------------------------------------------");
        System.out.println("\n| Thank You |");
    }
}
class Customer {
    public String customerId;
    public String name;
    public String email;
    public String phone;
    public String address;

    public Customer(String customerId, String name, String email, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
void placeOrder(Order order, double discountPercentage) {
        order.printOrderDetails(this, discountPercentage);
    }
}





public class GarmentMiniproject {


    public static void main(String[] args) {
        Garment g1 = new Garment();
        g1.name = "Silk";
        g1.description = "Good Product";
        g1.price= 600;
        double x = g1.calculateDiscountPrice(10);
        System.out.println(x);

    }
}
