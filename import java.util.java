import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Car
class Car {
    private String make;
    private String model;
    private int year;
    private double price;
    private boolean available;

    // initialising car object
    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.available = true; // car is available
    }

    // getting car make
    public String getMake() {
        return make;
    }

    // getting car make
    public String getModel() {
        return model;
    }

    // getting car year
    public int getYear() {
        return year;
    }

    // getting car price
    public double getPrice() {
        return price;
    }

    // Method to check if car is available
    public boolean isAvailable() {
        return available;
    }

    // Method to set the availability of car
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Class representing a Customer
class Customer {
    private String name;
    private int age;
    private String gender;
    private String drivingLicense;
    private String nationalID;
    private Car bookedCar;

    // initialising customer object
    public Customer(String name, int age, String gender, String drivingLicense, String nationalID) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.drivingLicense = drivingLicense;
        this.nationalID = nationalID;
    }

    // getting customer name
    public String getName() {
        return name;
    }

    // getting customer age
    public int getAge() {
        return age;
    }

    // getting customer gender
    public String getGender() {
        return gender;
    }

    // getting customer driving license
    public String getDrivingLicense() {
        return drivingLicense;
    }

    // getting customer national ID
    public String getNationalID() {
        return nationalID;
    }

    // getting booked car
    public Car getBookedCar() {
        return bookedCar;
    }

    // Method to book a car
    public void bookCar(Car car) {
        this.bookedCar = car;
    }
}

// Class representing the Rental Agency
class RentalAgency {
    private ArrayList<Car> cars; // List of cars available for rent
    private ArrayList<Customer> customers; // List of customers who booked cars

    // Constructor to initialize RentalAgency with a list of cars
    public RentalAgency(ArrayList<Car> cars) {
        this.cars = cars;
        this.customers = new ArrayList<>(); // Initialize empty customer list
    }

    // Method to get the list of available cars
    public ArrayList<Car> getAvailableCars() {
        ArrayList<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    // Method to book a car for a customer
    public void bookCar(Car car, Customer customer) {
        if (car.isAvailable()) {
            car.setAvailable(false); // Set car as not available
            customer.bookCar(car); // Assign car to customer
            customers.add(customer); // Add customer to list
            System.out.println("Car booked successfully.");
        } else {
            System.out.println("Car is not available.");
        }
    }

    // Method to display all booked cars and respective customers
    public void displayBookedCars() {
       if (customers.isEmpty()) {
            System.out.println("No cars booked.");
        } else {
            for (Customer customer : customers) {
                Car car = customer.getBookedCar();
                System.out.println("Customer: " + customer.getName() +
                        " - Car: " + car.getMake() + " " + car.getModel() +
                        " (" + car.getYear() + ")");
            }
        }
    }
}

// Main class for the Car Rental System
public class CarRentalSystem {

   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Car> carList = new ArrayList<>();
        
        // Adding cars to the car list
        carList.add(new Car("Toyota", "Camry", 2020, 55.0));
        carList.add(new Car("Honda", "Civic", 2019, 50.0));
        carList.add(new Car("Ford", "Mustang", 2021, 75.0));

        // Creating rental agency with the car list
        RentalAgency rentalAgency = new RentalAgency(carList);

        // Main loop for the car rental system menu
       while (true) {
            System.out.println("\nCar Rental System");
            System.out.println("1. View Available Cars");
            System.out.println("2. Book a Car");
            System.out.println("3. View Booked Cars");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayAvailableCars(rentalAgency);
                    break;
                case 2:
                    bookCar(rentalAgency, scanner);
                    break;
                case 3:
                    rentalAgency.displayBookedCars();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display available cars for rent
    private static void displayAvailableCars(RentalAgency rentalAgency) {
        ArrayList<Car> availableCars = rentalAgency.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("No cars available for rental.");
        } else {
            System.out.println("Available Cars:");
            for (Car car : availableCars) {
                System.out.println(car.getMake() + " " + car.getModel() + " (" + car.getYear() + ") - $" + car.getPrice() + " per day");
            }
        }
    }

    // Method to handle the car booking process
    private static void bookCar(RentalAgency rentalAgency, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter your driving license number: ");
        String drivingLicense = scanner.nextLine();
        System.out.print("Enter your national ID: ");
        String nationalID = scanner.nextLine();

        // Create a new customer with the provided details
        Customer customer = new Customer(name, age, gender, drivingLicense, nationalID);
        ArrayList<Car> availableCars = rentalAgency.getAvailableCars();

       if (availableCars.isEmpty()) {
            System.out.println("No cars available for booking.");
            return;
        }

        System.out.println("Available Cars:");
        for (int i = 0; i < availableCars.size(); i++) {
            Car car = availableCars.get(i);
            System.out.println((i + 1) + ". " + car.getMake() + " " + car.getModel() + " (" + car.getYear() + ") - $" + car.getPrice() + " per day");
        }

        System.out.print("Select the car to book (enter the number): ");
        int carChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (carChoice < 1 || carChoice > availableCars.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            Car carToBook = availableCars.get(carChoice - 1);
            rentalAgency.bookCar(carToBook, customer);
        }
    }
}
