import java.util.Scanner;
// ANSI color codes for console output
class ConsoleColors {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
}

// =================== ABSTRACT BOOKING ===================
abstract class Booking {
    Scanner sc = new Scanner(System.in);
    String from, to, date;
    int numPass;
    double baseFare;

    void collectDetails() {
        System.out.print(ConsoleColors.CYAN + "Enter Source: " + ConsoleColors.RESET);
        from = sc.nextLine();
        System.out.print(ConsoleColors.CYAN + "Enter Destination: " + ConsoleColors.RESET);
        to = sc.nextLine();
        while (true) {
            System.out.print(ConsoleColors.CYAN + "Enter Travel Date (DD-MM-YYYY): " + ConsoleColors.RESET);
            date = sc.nextLine();
            if (date.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$")) {
                break;
            } else {
                System.out.println(ConsoleColors.RED + "Invalid date format! Please enter as DD-MM-YYYY." + ConsoleColors.RESET);
            }
        }
        while (true) {
            System.out.print(ConsoleColors.CYAN + "Enter Number of Passengers (max 6): " + ConsoleColors.RESET);
            numPass = sc.nextInt();
            sc.nextLine();
            if (numPass >= 1 && numPass <= 6) {
                break;
            } else {
                System.out.println(ConsoleColors.RED + "You can book for up to 6 passengers only. Please enter a valid number." + ConsoleColors.RESET);
            }
        }
    }

    abstract void bookTicket(DB dbobj);

    // Common method to print billing in rectangle box
    void printBill(String name, int age, String seat, double base, double gst, double sc, double finalFare) {
        System.out.println(ConsoleColors.YELLOW + "======================================================" + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.GREEN + "| Name: %-15s | Age: %-3d | Seat: %-10s |\n" + ConsoleColors.RESET, name, age, seat);
        System.out.println(ConsoleColors.YELLOW + "------------------------------------------------------" + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "| Base Fare      : %-10.2f |\n" + ConsoleColors.RESET, base);
        System.out.printf(ConsoleColors.BLUE + "| GST            : %-10.2f |\n" + ConsoleColors.RESET, gst);
        System.out.printf(ConsoleColors.BLUE + "| Service Charge : %-10.2f |\n" + ConsoleColors.RESET, sc);
        System.out.printf(ConsoleColors.BOLD + "| Final Fare     : %-10.2f |\n" + ConsoleColors.RESET, finalFare);
        System.out.println(ConsoleColors.YELLOW + "======================================================\n" + ConsoleColors.RESET);
    }

    // Overload for Car & Bike (no seat column)
    void printBill(String name, int age, double base, double gst, double sc, double finalFare) {
        System.out.println(ConsoleColors.YELLOW + "======================================================" + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.GREEN + "| Name: %-15s | Age: %-3d |\n" + ConsoleColors.RESET, name, age);
        System.out.println(ConsoleColors.YELLOW + "------------------------------------------------------" + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "| Base Fare      : %-10.2f |\n" + ConsoleColors.RESET, base);
        System.out.printf(ConsoleColors.BLUE + "| GST            : %-10.2f |\n" + ConsoleColors.RESET, gst);
        System.out.printf(ConsoleColors.BLUE + "| Service Charge : %-10.2f |\n" + ConsoleColors.RESET, sc);
        System.out.printf(ConsoleColors.BOLD + "| Final Fare     : %-10.2f |\n" + ConsoleColors.RESET, finalFare);
        System.out.println(ConsoleColors.YELLOW + "======================================================\n" + ConsoleColors.RESET);
    }
}

// =================== BUS ===================
class BusBooking extends Booking {
    double gstRate = 0.18;
    double serviceCharge = 50;

    void bookTicket(DB dbobj) {
    System.out.println(ConsoleColors.WHITE + "\nAvailable Bus Routes:" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "1. Hyderabad -> Vijayawada   600" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "2. Hyderabad -> Bangalore    700" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "3. Hyderabad -> Chennai      1000" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "4. Vijayawada -> Vizag       650" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "5. Bangalore -> Mysore       300" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "6. Chennai -> Coimbatore     680" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "7. Vizag -> Arunachalam      750" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "8. Pune -> Mumbai            500" + ConsoleColors.RESET);

        collectDetails();

        if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("VIJAYAWADA"))
            baseFare = 600;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("BANGALORE"))
            baseFare = 700;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("CHENNAI"))
            baseFare = 1000;
        else if (from.toUpperCase().equals("VIJAYAWADA") && to.toUpperCase().equals("VIZAG"))
            baseFare = 650;
        else if (from.toUpperCase().equals("BANGALORE") && to.toUpperCase().equals("MYSORE"))
            baseFare = 300;
        else if (from.toUpperCase().equals("CHENNAI") && to.toUpperCase().equals("COIMBATORE"))
            baseFare = 680;
        else if (from.toUpperCase().equals("VIZAG") && to.toUpperCase().equals("ARUNACHALAM"))
            baseFare = 750;
        else if (from.toUpperCase().equals("PUNE") && to.toUpperCase().equals("MUMBAI"))
            baseFare = 500;
        else {
            System.out.println(ConsoleColors.RED + "Invalid route! Please try again." + ConsoleColors.RESET);
            return;
        }

        double totalFare = 0;
    System.out.println(ConsoleColors.BOLD + "\n================ BUS BOOKING SUMMARY ================" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.CYAN + "From: " + from + " | To: " + to + " | Date: " + date + ConsoleColors.RESET);
    System.out.println(ConsoleColors.BOLD + "======================================================" + ConsoleColors.RESET);

        for (int i = 1; i <= numPass; i++) {
            System.out.println(ConsoleColors.YELLOW + "Passenger " + i + ":" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET);
            String name = sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET);
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Seat Preference (Window/Normal): " + ConsoleColors.RESET);
            String seat = sc.nextLine();

            double fare = baseFare;
            if (age > 60) fare *= 0.75;
            double gstAmount = fare * gstRate;
            fare += gstAmount;
            fare += serviceCharge;

            totalFare += fare;

            printBill(name, age, seat, baseFare, gstAmount, serviceCharge, fare);
        }

        // Balance check
        if (dbobj.getBalance() >= totalFare) {
            dbobj.setBalance(dbobj.getBalance()-totalFare);
            System.out.printf(ConsoleColors.BOLD + "TOTAL FARE: %.2f\n" + ConsoleColors.RESET, totalFare);
            System.out.printf(ConsoleColors.GREEN + "  Amount Deducted. Remaining Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());
            System.out.println(ConsoleColors.GREEN + "  Your bus booking is confirmed!\n" + ConsoleColors.RESET);
        } else {
            System.out.printf(ConsoleColors.RED + "  Insufficient Balance! Your Balance: %.2f | Required: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance(), totalFare);
        }
    }
}

// =================== TRAIN ===================
class TrainBooking extends Booking {
    double gstRate = 0.18;
    double serviceCharge = 60;

    void bookTicket(DB dbobj) {
    System.out.println(ConsoleColors.WHITE + "\nAvailable Train Routes:" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "1. Hyderabad -> Vijayawada   400" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "2. Hyderabad -> Bangalore    640" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "3. Hyderabad -> Chennai      710" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "4. Vijayawada -> Vizag       600" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "5. Bangalore -> Mysore       400" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "6. Chennai -> Coimbatore     560" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "7. Vizag -> Arunachalam      590" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "8. Pune -> Mumbai            450" + ConsoleColors.RESET);

        collectDetails();

        if (from.equals("HYDERABAD") && to.equals("VIJAYAWADA"))
            baseFare = 400;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("BANGALORE"))
            baseFare = 640;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("CHENNAI"))
            baseFare = 710;
        else if (from.toUpperCase().equals("VIJAYAWADA") && to.toUpperCase().equals("VIZAG"))
            baseFare = 600;
        else if (from.toUpperCase().equals("BANGALORE") && to.toUpperCase().equals("MYSORE"))
            baseFare = 400;
        else if (from.toUpperCase().equals("CHENNAI") && to.toUpperCase().equals("COIMBATORE"))
            baseFare = 560;
        else if (from.toUpperCase().equals("VIZAG") && to.toUpperCase().equals("ARUNACHALAM"))
            baseFare = 590;
        else if (from.toUpperCase().equals("PUNE") && to.toUpperCase().equals("MUMBAI"))
            baseFare = 450;
        else {
            System.out.println(ConsoleColors.RED + "Invalid route! Please try again." + ConsoleColors.RESET);
            return;
        }

        double totalFare = 0;
    System.out.println(ConsoleColors.BOLD + "\n================ TRAIN BOOKING SUMMARY ================" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.CYAN + "From: " + from + " | To: " + to + " | Date: " + date + ConsoleColors.RESET);
    System.out.println(ConsoleColors.BOLD + "======================================================" + ConsoleColors.RESET);

        for (int i = 1; i <= numPass; i++) {
            System.out.println(ConsoleColors.YELLOW + "Passenger " + i + ":" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET);
            String name = sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET);
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Seat Preference (Window/Normal): " + ConsoleColors.RESET);
            String seat = sc.nextLine();

            double fare = baseFare;
            double gstAmount = fare * gstRate;
            fare += gstAmount;
            fare += serviceCharge;

            totalFare += fare;

            printBill(name, age, seat, baseFare, gstAmount, serviceCharge, fare);
        }

        // Balance check
        if (dbobj.getBalance() >= totalFare) {
            dbobj.setBalance(dbobj.getBalance()-totalFare);
            System.out.printf(ConsoleColors.BOLD + "TOTAL FARE: %.2f\n" + ConsoleColors.RESET, totalFare);
            System.out.printf(ConsoleColors.GREEN + "  Amount Deducted. Remaining Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());
            System.out.println(ConsoleColors.GREEN + "  Your train booking is confirmed!\n" + ConsoleColors.RESET);
        } else {
            System.out.printf(ConsoleColors.RED + "  Insufficient Balance! Your Balance: %.2f | Required: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance(), totalFare);
        }
    }
}

// =================== FLIGHT ===================
class FlightBooking extends Booking {
    double gstRate = 0.18;
    double serviceCharge = 100;

    void bookTicket(DB dbobj) {
    System.out.println(ConsoleColors.WHITE + "\nAvailable Flight Routes:" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "1. Hyderabad -> Vijayawada   2400" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "2. Hyderabad -> Bangalore    4000" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "3. Hyderabad -> Chennai      3000" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "4. Vijayawada -> Vizag       2800" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "5. Bangalore -> Mysore       2300" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "6. Chennai -> Coimbatore     2500" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "7. Vizag -> Arunachalam      2300" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "8. Pune -> Mumbai            2900" + ConsoleColors.RESET);

        collectDetails();

        if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().toUpperCase().equals("VIJAYAWADA"))
            baseFare = 2400;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("BANGALORE"))
            baseFare = 4000;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("CHENNAI"))
            baseFare = 3000;
        else if (from.toUpperCase().equals("VIJAYAWADA") && to.toUpperCase().equals("VIZAG"))
            baseFare = 2800;
        else if (from.toUpperCase().equals("BANGALORE") && to.toUpperCase().equals("MYSORE"))
            baseFare = 2300;
        else if (from.toUpperCase().equals("CHENNAI") && to.toUpperCase().equals("COIMBATORE"))
            baseFare = 2500;
        else if (from.toUpperCase().equals("VIZAG") && to.toUpperCase().equals("ARUNACHALAM"))
            baseFare = 2300;
        else if (from.toUpperCase().equals("PUNE") && to.toUpperCase().equals("MUMBAI"))
            baseFare = 2900;
        else {
            System.out.println(ConsoleColors.RED + "Invalid route! Please try again." + ConsoleColors.RESET);
            return;
        }

        double totalFare = 0;
    System.out.println(ConsoleColors.BOLD + "\n================ FLIGHT BOOKING SUMMARY ================" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.CYAN + "From: " + from + " | To: " + to + " | Date: " + date + ConsoleColors.RESET);
    System.out.println(ConsoleColors.BOLD + "======================================================" + ConsoleColors.RESET);

        for (int i = 1; i <= numPass; i++) {
            System.out.println(ConsoleColors.YELLOW + "Passenger " + i + ":" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET);
            String name = sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET);
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Seat Preference (Window/Normal): " + ConsoleColors.RESET);
            String seat = sc.nextLine();

            double fare = baseFare;
            double gstAmount = fare * gstRate;
            fare += gstAmount;
            fare += serviceCharge;

            totalFare += fare;

            printBill(name, age, seat, baseFare, gstAmount, serviceCharge, fare);
        }

        // Balance check
        if (dbobj.getBalance() >= totalFare) {
            dbobj.setBalance(dbobj.getBalance()-totalFare);
            System.out.printf(ConsoleColors.BOLD + "TOTAL FARE: %.2f\n" + ConsoleColors.RESET, totalFare);
            System.out.printf(ConsoleColors.GREEN + "  Amount Deducted. Remaining Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());
            System.out.println(ConsoleColors.GREEN + "  Your flight booking is confirmed!\n" + ConsoleColors.RESET);
        } else {
            System.out.printf(ConsoleColors.RED + "  Insufficient Balance! Your Balance: %.2f | Required: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance(), totalFare);
        }
    }
}

// =================== CAR ===================
class CarBooking extends Booking {
    double gstRate = 0.12;
    double serviceCharge = 80;

    void bookTicket(DB dbobj) {
    System.out.println(ConsoleColors.WHITE + "\nAvailable Car Routes:" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "1. Hyderabad -> Vijayawada   2000" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "2. Hyderabad -> Bangalore    3500" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "3. Hyderabad -> Chennai      4000" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "4. Pune -> Mumbai            1800" + ConsoleColors.RESET);

        collectDetails();

        if (from.equals("HYDERABAD") && to.equals("VIJAYAWADA"))
            baseFare = 2000;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("BANGALORE"))
            baseFare = 3500;
        else if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("CHENNAI"))
            baseFare = 4000;
        else if (from.toUpperCase().equals("PUNE") && to.toUpperCase().equals("MUMBAI"))
            baseFare = 1800;
        else {
            System.out.println(ConsoleColors.RED + "Invalid route! Please try again." + ConsoleColors.RESET);
            return;
        }

        double totalFare = 0;
    System.out.println(ConsoleColors.BOLD + "\n================ CAR BOOKING SUMMARY ================" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.CYAN + "From: " + from + " | To: " + to + " | Date: " + date + ConsoleColors.RESET);
    System.out.println(ConsoleColors.BOLD + "======================================================" + ConsoleColors.RESET);

        for (int i = 1; i <= numPass; i++) {
            System.out.println(ConsoleColors.YELLOW + "Passenger " + i + ":" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET);
            String name = sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET);
            int age = sc.nextInt();
            sc.nextLine();

            double fare = baseFare;
            double gstAmount = fare * gstRate;
            fare += gstAmount;
            fare += serviceCharge;

            totalFare += fare;

            printBill(name, age, baseFare, gstAmount, serviceCharge, fare);
        }

        // Balance check
        if (dbobj.getBalance() >= totalFare) {
            dbobj.setBalance(dbobj.getBalance()-totalFare);
            System.out.printf(ConsoleColors.BOLD + "TOTAL FARE: %.2f\n" + ConsoleColors.RESET, totalFare);
            System.out.printf(ConsoleColors.GREEN + "  Amount Deducted. Remaining Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());
            System.out.println(ConsoleColors.GREEN + "  Your car booking is confirmed!\n" + ConsoleColors.RESET);
        } else {
            System.out.printf(ConsoleColors.RED + "  Insufficient Balance! Your Balance: %.2f | Required: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance(), totalFare);
        }
    }
}

// =================== BIKE ===================
class BikeBooking extends Booking {
    double gstRate = 0.05;
    double serviceCharge = 30;

    void bookTicket(DB dbobj) {
    System.out.println(ConsoleColors.WHITE + "\nAvailable Bike Routes:" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "1. Hyderabad -> Vijayawada   500" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "2. Hyderabad -> Bangalore    800" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE + "3. Pune -> Mumbai            450" + ConsoleColors.RESET);

        collectDetails();

        if (from.toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("VIJAYAWADA"))
            baseFare = 500;
        else if (from.toUpperCase().toUpperCase().equals("HYDERABAD") && to.toUpperCase().equals("BANGALORE"))
            baseFare = 800;
        else if (from.toUpperCase().equals("PUNE") && to.toUpperCase().equals("MUMBAI"))
            baseFare = 450;
        else {
            System.out.println(ConsoleColors.RED + "Invalid route! Please try again." + ConsoleColors.RESET);
            return;
        }

        double totalFare = 0;
    System.out.println(ConsoleColors.BOLD + "\n================ BIKE BOOKING SUMMARY ================" + ConsoleColors.RESET);
    System.out.println(ConsoleColors.CYAN + "From: " + from + " | To: " + to + " | Date: " + date + ConsoleColors.RESET);
    System.out.println(ConsoleColors.BOLD + "======================================================" + ConsoleColors.RESET);

        for (int i = 1; i <= numPass; i++) {
            System.out.println(ConsoleColors.YELLOW + "Rider " + i + ":" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET);
            String name = sc.nextLine();
            System.out.print(ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET);
            int age = sc.nextInt();
            sc.nextLine();

            double fare = baseFare;
            double gstAmount = fare * gstRate;
            fare += gstAmount;
            fare += serviceCharge;

            totalFare += fare;

            printBill(name, age, baseFare, gstAmount, serviceCharge, fare);
        }

        // Balance check
        if (dbobj.getBalance() >= totalFare) {
            dbobj.setBalance(dbobj.getBalance()-totalFare);
            System.out.printf(ConsoleColors.BOLD + "TOTAL FARE: %.2f\n" + ConsoleColors.RESET, totalFare);
            System.out.printf(ConsoleColors.GREEN + "  Amount Deducted. Remaining Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());
            System.out.println(ConsoleColors.GREEN + "  Your bike booking is confirmed!\n" + ConsoleColors.RESET);
        } else {
            System.out.printf(ConsoleColors.RED + "  Insufficient Balance! Your Balance: %.2f | Required: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance(), totalFare);
        }
    }
}

// =================== MAIN ===================
class BookingApp {
    //static double balance = 1000;  // Added Balance
    static DB dbobj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    System.out.println(ConsoleColors.BOLD + ConsoleColors.GREEN + "WELCOME TO BOOKING.COM!!!" + ConsoleColors.RESET);
    System.out.printf(ConsoleColors.BOLD + ConsoleColors.YELLOW + "  Your Wallet Balance: %.2f\n" + ConsoleColors.RESET, dbobj.getBalance());

        Booking booking;
        while (true) {
            System.out.println(ConsoleColors.BOLD + ConsoleColors.WHITE + "\n============================= OneHub Travelling.com =============================" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Welcome ! Choose your booking module:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "1. Bus Booking" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "2. Train Booking" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "3. Flight Booking" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "4. Car Booking" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "5. Bike Booking" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "6. Exit" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.CYAN + "Enter your choice: " + ConsoleColors.RESET);
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1)
                booking = new BusBooking();
            else if (choice == 2)
                booking = new TrainBooking();
            else if (choice == 3)
                booking = new FlightBooking();
            else if (choice == 4)
                booking = new CarBooking();
            else if (choice == 5)
                booking = new BikeBooking();
            else if (choice == 6) {
                System.out.println(ConsoleColors.GREEN + "  Thank you for using OneHub.com. Have a Safe Journey!" + ConsoleColors.RESET);
                break;
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Try again." + ConsoleColors.RESET);
                continue;
            }

            booking.bookTicket(dbobj);
        }
    }
}
