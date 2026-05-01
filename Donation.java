  import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// ===== User2 class =====
class User2 {
    private String name;
    private double totalDonations;
    private String lastDonationTime;

    public User2(String name) {
        this.name = name;
        this.totalDonations = 0.0;
        this.lastDonationTime = "No donations yet";
    }

    public String getName() { return name; }

    public void addDonation(double amount) {
        this.totalDonations += amount;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.lastDonationTime = dtf.format(LocalDateTime.now());
    }

    public double getTotalDonations() { return totalDonations; }
    public String getLastDonationTime() { return lastDonationTime; }
}

// ===== SubSector class =====
class SubSector {
    private String name;
    private String asciiArt;
    private String example;
    private double totalDonated;

    public SubSector(String name, String asciiArt, String example) {
        this.name = name;
        this.asciiArt = asciiArt;
        this.example = example;
        this.totalDonated = 0.0;
    }

    public void donate(double amount) { totalDonated += amount; }

    public void printPoster(String color) {
        System.out.println(color + asciiArt + HelperColoredFinal.RESET);
        System.out.println(color + ">> " + name + "  (Example: " + example + ")" + HelperColoredFinal.RESET);
        System.out.println(color + "   Total in this sub-sector: Rs " + String.format("%.2f", totalDonated) + HelperColoredFinal.RESET);
    }

    public String getName() { return name; }
    public double getTotalDonated() { return totalDonated; }
    public String getExample() { return example; }
}

// ===== Category class =====
class Category {
    private String name;
    private String asciiArt;
    private String basicInfo;
    private String skills;
    private SubSector s1, s2, s3;
    private double categoryTotal;

    public Category(String name, String asciiArt, String basicInfo, String skills,
                    SubSector s1, SubSector s2, SubSector s3) {
        this.name = name; this.asciiArt = asciiArt; this.basicInfo = basicInfo; this.skills = skills;
        this.s1 = s1; this.s2 = s2; this.s3 = s3; this.categoryTotal = 0.0;
    }

    public void showHeader(String color) {
        System.out.println(color + asciiArt + HelperColoredFinal.RESET);
        System.out.println(color + "=== " + name + " ===" + HelperColoredFinal.RESET);
    }

    public void showFull(String color) {
        showHeader(color);
        System.out.println(color + "Basic Info:" + HelperColoredFinal.RESET); System.out.println(basicInfo + "\n");
        System.out.println(color + "Skills / Benefits:" + HelperColoredFinal.RESET); System.out.println(skills + "\n");
        System.out.println(color + "Sub-sectors (press number to view & donate):" + HelperColoredFinal.RESET);
        System.out.println("1) " + s1.getName() + " - " + s1.getExample());
        System.out.println("2) " + s2.getName() + " - " + s2.getExample());
        System.out.println("3) " + s3.getName() + " - " + s3.getExample());
    }

    public void handleSub(int choice, Scanner sc, User2 user, DB db, String color) {
        SubSector sel = null;
        if (choice == 1) sel = s1;
        else if (choice == 2) sel = s2;
        else if (choice == 3) sel = s3;
        else { System.out.println("Invalid sub-sector choice."); return; }

        sel.printPoster(color);
        System.out.print("Enter donation amount (Rs) or 0 to cancel: ");
        String line = sc.nextLine().trim();
        double amt;
        try { amt = Double.parseDouble(line); } catch (Exception e) { System.out.println("Invalid amount."); return; }

        if (amt <= 0) { System.out.println("No donation made."); return; }
        if (amt > db.getBalance()) { System.out.println("Insufficient balance! You only have Rs " + db.getBalance()); return; }

        sel.donate(amt); categoryTotal += amt; user.addDonation(amt);
        db.setBalance(db.getBalance() - amt);

        System.out.println("Donation recorded: Rs " + amt + " -> " + sel.getName());
        System.out.println("Remaining Balance: Rs " + db.getBalance());
    }

    public double getCategoryTotal() { return categoryTotal; }
    public String getName() { return name; }
}
class HelperColoredFinal {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";
    static DB db;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //DB db = new DB();                  
        User2 user = new User2(db.getUsername());  // Use DB username

        // --- Create sub-sectors ---
        SubSector health1 = new SubSector("Blood Donation", "  ____\n.\"    \".\n/  BLOOD  \\\n|   DROP    |\n \\  ____  /\n '._.'\n", "Community blood donation drives");
        SubSector health2 = new SubSector("Medical Aid", " ___________\n|  MEDICAL  |\n|   AID     |\n|  +  +  +  |\n|_|\n", "Emergency first response & kits");
        SubSector health3 = new SubSector("Free Health Camps", " _____________\n/             \\\n/   HEALTH     \\\n|     CAMP       |\n \\               /\n  \\_/\n", "Awareness and free check-up camps");

        SubSector edu1 = new SubSector("School Fund", " __________________\n|  SCHOOL BUILDING  |\n|  [====]  [====]   |\n|  []  []   |\n ------------------\n", "Support for school infrastructure");
        SubSector edu2 = new SubSector("Library Support", " _____________\n/  BOOK SHELF  \\\n|  [|] [|] [|]   |\n \\_/\n", "Buying books and building libraries");
        SubSector edu3 = new SubSector("Student Scholarships", " _______\n/ _____ \\\n| |     | |\n| |SCHOL| |\n \\_/ \n", "Scholarships for deserving students");

        SubSector sport1 = new SubSector("Cricket Sponsorship", " ||====|\n ||Bat |\n ||====|\n  (bat & ball)\n", "Sponsoring local cricket teams & gear");
        SubSector sport2 = new SubSector("Kabaddi Training", " (O)   (O)\n /|\\   /|\\\n  /     \\\n (Kabaddi drill)\n", "Coaching and kabaddi camps");
        SubSector sport3 = new SubSector("Football Kits", " ______\n/      \\\n|  BALL  |\n \\/\n", "Providing kits and travel support");

        // --- Create categories ---
        Category health = new Category("Health", RED + "#############################################" + RESET,
            "Health focuses on emergency response, blood drives and public health camps.",
            "Skills: First Aid, CPR basics, health awareness, camp organisation.", health1, health2, health3);

        Category education = new Category("Education", YELLOW + "*********************************************" + RESET,
            "Education empowers communities via scholarships, libraries and school support.",
            "Skills: Mentoring, tutoring, resource management.", edu1, edu2, edu3);

        Category sports = new Category("Sports", GREEN + "+++++++++++++++++++++++++++++++++++++++++++++" + RESET,
            "Sports promotes fitness and teamwork by supporting players and events.",
            "Skills: Coaching, teamwork, fitness training.", sport1, sport2, sport3);

        boolean inSession = true;
        while (inSession) {
            clearScreen();
            System.out.println("Welcome, " + user.getName()
                               + " | Total donated: Rs " + String.format("%.2f", user.getTotalDonations())
                               + " | Wallet Balance: Rs " + db.getBalance());
            System.out.println();
            System.out.println("Select an option:");
            System.out.println("1) Health");
            System.out.println("2) Education");
            System.out.println("3) Sports");
            System.out.println("0) Exit & Print Receipt");
            System.out.print("Choice: ");
            String c = sc.nextLine().trim();

            switch(c) {
                case "0":
                    printReceipt(user, health, education, sports, db);
                    inSession = false;
                    break;
                case "1": handleCategory(health, RED, sc, user, db); break;
                case "2": handleCategory(education, YELLOW, sc, user, db); break;
                case "3": handleCategory(sports, GREEN, sc, user, db); break;
                default: System.out.println("Invalid choice."); pause(sc); break;
            }
        }

        //sc.close();
    }

    private static void handleCategory(Category cat, String color, Scanner sc, User2 user, DB db) {
        clearScreen();
        cat.showFull(color);
        System.out.print("Enter sub-sector number (1-3) or 0 to go back: ");
        String s = sc.nextLine().trim();
        if (s.equals("0")) return;
        try { int sel = Integer.parseInt(s); cat.handleSub(sel, sc, user, db, color); }
        catch (NumberFormatException ex) { System.out.println("Invalid input."); }
        pause(sc);
    }

    private static void clearScreen() { for (int i=0;i<8;i++) System.out.println(); }
    private static void pause(Scanner sc) { System.out.println("\nPress Enter to continue..."); sc.nextLine(); }

    private static void printReceipt(User2 user, Category h, Category e, Category s, DB db) {
        System.out.println();
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
        System.out.println(MAGENTA + "|                 DONATION RECEIPT               |" + RESET);
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
        System.out.printf(MAGENTA + "| Name: %-38s |\n" + RESET, user.getName());
        System.out.printf(MAGENTA + "| Total Donated: Rs %-24.2f |\n" + RESET, user.getTotalDonations());
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
        System.out.printf(MAGENTA + "| %-18s : Rs %12.2f |\n" + RESET, h.getName(), h.getCategoryTotal());
        System.out.printf(MAGENTA + "| %-18s : Rs %12.2f |\n" + RESET, e.getName(), e.getCategoryTotal());
        System.out.printf(MAGENTA + "| %-18s : Rs %12.2f |\n" + RESET, s.getName(), s.getCategoryTotal());
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
        System.out.printf(MAGENTA + "| Last Donation Time: %-20s |\n" + RESET, user.getLastDonationTime());
        System.out.printf(MAGENTA + "| Wallet Balance: Rs %-22.2f |\n" + RESET, db.getBalance());
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
        System.out.println(MAGENTA + "| Thank you for supporting the Helper Platform   |" + RESET);
        System.out.println(MAGENTA + "+------------------------------------------------+" + RESET);
    }
}
