import java.util.Scanner;

class items
{
    //static DB dbobj;
    static Scanner sc = new Scanner(System.in);

    // ANSI color codes
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String CYAN = "\u001B[36m";

    double soups() 
    {
        double rate = 0;

        System.out.println(BLUE + "+----------------------------+" + RESET);
        System.out.println(YELLOW + "|           SOUPS            |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(GREEN + "|  1. Veg Manchow - 99       |" + RESET);
        System.out.println(RED   + "|  2. Chicken Manchow - 129  |" + RESET);
        System.out.println(CYAN  + "|  3. Chicken Corn Soup - 102|" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(BLUE + "+----------------------------+" + RESET);

        int n = sc.nextInt();
        if(n>=1 && n<=3) 
        {
            System.out.println(CYAN + "Enter quantity: " + RESET);
            int n1 = sc.nextInt();
            if(n==1) 
                rate = n1*99;
            else if(n==2)
                rate = n1*129;
            else 
                rate = n1*102;
            System.out.println(GREEN + "Item added to cart: " + rate + RESET);
        } 
        else System.out.println(RED + "Invalid item" + RESET);

        return rate;
    }

    double chicken_biryani() 
    {
        double rate = 0;

        System.out.println(BLUE + "+----------------------------+" + RESET);
        System.out.println(YELLOW + "|       CHICKEN BIRYANI      |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(GREEN + "|  1. Single - 299           |" + RESET);
        System.out.println(RED   + "|  2. Special Nattu Kodi - 499|" + RESET);
        System.out.println(CYAN  + "|  3. Boneless - 699         |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(BLUE + "+----------------------------+" + RESET);

        int n = sc.nextInt();
        if(n>=1 && n<=3)
        {
            System.out.println(CYAN + "Enter quantity: " + RESET);
            int n1 = sc.nextInt();
            if(n==1) 
                rate = n1*299;
            else if(n==2)
                rate = n1*499;
            else 
                rate = n1*699;
            System.out.println(GREEN + "Item added to cart: " + rate + RESET);
        } 
        else 
            System.out.println(RED + "Pack not available" + RESET);
        return rate;
    }

    double deserts() 
    {
        double rate = 0;

        System.out.println(BLUE + "+----------------------------+" + RESET);
        System.out.println(YELLOW + "|          DESSERTS          |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(GREEN + "|  1. Jamun - 159            |" + RESET);
        System.out.println(RED   + "|  2. Apricot Delight - 189  |" + RESET);
        System.out.println(CYAN  + "|  3. Khubani ka Meetha - 250|" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(BLUE + "+----------------------------+" + RESET);

        int n = sc.nextInt();
        if(n>=1 && n<=3) 
        {
            System.out.println(CYAN + "Enter quantity: " + RESET);
            int n1 = sc.nextInt();
            if(n==1) 
                rate = n1*159;
            else if(n==2) 
                rate = n1*189;
            else 
                rate = n1*250;
            System.out.println(GREEN + "Item added to cart: " + rate + RESET);
        } 
        else 
        System.out.println(RED + "Pack not available" + RESET);
        return rate;
    }

    int otpgenerator() 
    {
        int otp = 1000 + (int)(Math.random()*8999);
        System.out.println("Your OTP: " + otp);
        return otp;
    }

    void waiting() 
    {
        for(int i=0;i<20;i++) 
        {
            try 
            { Thread.sleep(200); 
                
            } 
            catch(Exception e) {}
            if(i%3==1)
                System.out.print("\r|");
            else if(i%3==2)
                System.out.print("\r/");
            else 
                System.out.print("\r-");
        }
    }
     void menue()
    { 
        System.out.println(BLUE + "+----------------------------+" + RESET);
        System.out.println(YELLOW + "|           MENU             |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(GREEN + "|  1. Soups                  |" + RESET);
        System.out.println(RED   + "|  2. Chicken Biryani         |" + RESET);
        System.out.println(CYAN  + "|  3. Desserts               |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(BLUE + "+----------------------------+" + RESET);
    }
}

class kodi_Kura_chitti_gare extends items 
{
    double bill=0;
    double amount=0;
    double gst=0.0;
    double discount=0.0;

    
    void billing(double rate)
    {
        

        System.out.println("\n========== BILL RECEIPT ==========");
        System.out.println("----------------------------------");
        System.out.println("Item Total     : Rs " + rate);
        System.out.println("GST (20%)      : Rs " + gst);
        if(rate>=5000) 
            System.out.println("Discount       : Rs " + discount);
        System.out.println("----------------------------------");
        System.out.println("Final Amount   : Rs " + ((rate>=5000)? (amount-discount) : amount));
        System.out.println("===================================");
        System.out.println("Thank you for ordering !");
    }

    void selection(DB obj) 
    {
        menue();
        System.out.println("choose the item:");
        int n=sc.nextInt();
        if(n>=1 && n<=3) 
        {
            if(n==1) 
                bill+=soups();
            else if(n==2) 
                bill+=chicken_biryani();
            else
                bill+=deserts();
        }
        else 
            System.out.println("Item not Available");

        System.out.println("1.To continue ordering\n2.Billing\n3.exit");
        int n1=sc.nextInt();
        if(n1==1) 
            selection(obj);
        else if(n1==2)
        {
            discount=0.1;
            gst=bill*0.20;
            amount=bill+gst;
            if(amount>0) 
            {
                if(amount<=obj.getBalance()) 
                {
                    int otp=otpgenerator();
                    System.out.println("for confirming the order please enter the otp ");
                    int ot=sc.nextInt();
                    if(ot==otp) {
                        System.out.println("Your order is placed Succesfully\nYour order is on the way");
                        waiting();
                        System.out.println("Enjoy your order\nYour Bill is Generating");
                        waiting();
                        billing(bill);
                        double b=obj.getBalance()-amount;
                        obj.setBalance(b);
                        
                    } 
                    else 
                        System.out.println("Invalid OTP. Order cancelled.");
                } 
                else 
                    System.out.println("Insufficient Balanace");
            }
            else 
                System.out.println("NO Item Selected");
        } 
        else 
            System.out.println("TQ:)");
    }
}

class Parampara extends items 
{
    double bill=0;
    double amount=0;
    double gst=0.0;
    double discount=0.0;

    /*void menue()
    { 
        //System.out.println("Menu:\n1.Soups\n2.chicken biryani\n3.desserts");
        System.out.println(BLUE + "+----------------------------+" + RESET);
    System.out.println(YELLOW + "|           MENU             |" + RESET);
    System.out.println(BLUE + "|                            |" + RESET);
    System.out.println(GREEN + "|  1. Soups                  |" + RESET);
    System.out.println(RED   + "|  2. Chicken Biryani         |" + RESET);
    System.out.println(CYAN  + "|  3. Desserts               |" + RESET);
    System.out.println(BLUE + "|                            |" + RESET);
    System.out.println(BLUE + "+----------------------------+" + RESET);
    }*/

    void billing(double rate) 
    {
        
        if(rate>=5000)
        {
            System.out.println("\n========== BILL RECEIPT ==========");
            System.out.println("----------------------------------");
            System.out.println("Item Total     : Rs " + rate);
            System.out.println("GST (10%)      : Rs " + gst);
            System.out.println("Discount       : Rs " + discount);
            System.out.println("----------------------------------");
            System.out.println("Final Amount   : Rs " + (amount - discount));
            System.out.println("===================================");
            System.out.println("Thank you for ordering !");
        } else 
        {
            System.out.println("\n========== BILL RECEIPT ==========");
            System.out.println("----------------------------------");
            System.out.println("Item Total     : Rs " + rate);
            System.out.println("GST (10%)      : Rs " + gst);
            System.out.println("----------------------------------");
            System.out.println("Final Amount   : Rs " + (amount));
            System.out.println("===================================");
            System.out.println("Thank you for ordering !");
        }
    }

    void selection(DB obj)
    {
        menue();
        System.out.println("choose the item:");
        int n=sc.nextInt();
        if(n>=1 && n<=3) 
        {
            if(n==1) 
                bill+=soups();
            else if(n==2)
                bill+=chicken_biryani();
            else
                bill+=deserts();
        } 
        else 
            System.out.println("Item not Available");

        System.out.println("1.To continue ordering\n2.Billing\n3.exit");
        int n1=sc.nextInt();
        if(n1==1) 
            selection(obj);
        else if(n1==2) 
        {
            if(bill>0) 
            {
                discount=0.10;
                gst=bill*0.10;
                amount=bill+gst;
                if(amount<=obj.getBalance()) 
                {
                    int otp=otpgenerator();
                    System.out.println("for confirming the order please enter the otp ");
                    int ot=sc.nextInt();
                    if(ot==otp) 
                    {
                    
                        System.out.println("Your order is placed Succesfully\nYour order is on the way");
                        waiting();
                        System.out.println("Enjoy your order\nYour Bill is Generating");
                        waiting();
                        billing(bill);
                         double b=obj.getBalance()-amount;
                        obj.setBalance(b);
                        
                    }
                    else 
                    System.out.println("Invalid OTP. Order cancelled.");
                }
                else 
                    System.out.println("Insufficient Balanace");
            }   
            else
                System.out.println("NO Item Selected");
        }
        else
            System.out.println("TQ:)");
    }
}

class Antera extends items 
{
    double bill=0;
    double amount=0;
    double gst=0.0;
    double discount=0.0;
    

    void billing(double rate) 
    {
        
        if(rate>=5000)
        {
            System.out.println("\n========== BILL RECEIPT ==========");
            System.out.println("----------------------------------");
            System.out.println("Item Total     : Rs " + rate);
            System.out.println("GST (10%)      : Rs " + gst);
            System.out.println("Discount       : Rs " + discount);
            System.out.println("----------------------------------");
            System.out.println("Final Amount   : Rs " + (amount - discount));
            System.out.println("===================================");
            System.out.println("Thank you for ordering !");
        }
        else 
        {
            System.out.println("\n========== BILL RECEIPT ==========");
            System.out.println("----------------------------------");
            System.out.println("Item Total     : Rs " + rate);
            System.out.println("GST (10%)      : Rs " + gst);
            System.out.println("----------------------------------");
            System.out.println("Final Amount   : Rs " + (amount));
            System.out.println("==================================");
            System.out.println("Thank you for ordering !          ");
        }
    }

    void selection(DB obj) 
    {
        menue();
        System.out.println("choose the item:");
        int n=sc.nextInt();
        if(n>=1 && n<=3) 
        {
            if(n==1) 
                bill+=soups();
            else if(n==2) 
                bill+=chicken_biryani();
            else 
                bill+=deserts();
        } else
            System.out.println("Item not Available");

        System.out.println("1.To continue ordering\n2.Billing\n3.exit");
        int n1=sc.nextInt();
        if(n1==1) 
            selection(obj);
        else if(n1==2) 
        {
            if(bill>0) 
            {
                discount=0.10;
                gst=bill*0.10;
                amount=bill+gst;
                if(amount<=obj.getBalance())
                {
                    int otp=otpgenerator();
                    System.out.println("for confirming the order please enter the otp ");
                    int ot=sc.nextInt();
                    if(ot==otp) 
                    {
                        System.out.println("Your order is placed Succesfully\nYour order is on the way");
                        waiting();
                        System.out.println("Enjoy your order\nYour Bill is Generating");
                        waiting();
                        billing(bill);
                        double b=obj.getBalance()-amount;
                        obj.setBalance(b);
                        
                    } 
                    else 
                        System.out.println("Invalid OTP. Order cancelled.");
                } 
                else 
                    System.out.println("Insufficient Balanace");
            }   
            else 
                System.out.println("NO Item Selected");
        } 
        else
            System.out.println("TQ:)");
    }
}

class Food
{
    static Scanner sc=new Scanner(System.in);
    static DB dbobj;
    public static void main(String args[])
    {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String CYAN = "\u001B[36m";

        // Welcome Banner
        System.out.println(GREEN + "============================================" + RESET);
        System.out.println(YELLOW + "         WELCOME TO FOOD HUB" + RESET);
        System.out.println(GREEN + "============================================" + RESET);
        System.out.println(CYAN + "          Choose Your Restaurant" + RESET + "\n");

        // Restaurant List
        System.out.println(BLUE + "+----------------------------+" + RESET);
        System.out.println(YELLOW + "|       RESTAURANTS          |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(GREEN + "|  1. Kodi Kura Chitti Gare  |" + RESET);
        System.out.println(RED   + "|  2. Parampara              |" + RESET);
        System.out.println(CYAN  + "|  3. Antera                 |" + RESET);
        System.out.println(BLUE + "|                            |" + RESET);
        System.out.println(BLUE + "+----------------------------+" + RESET);

        int n = sc.nextInt();
        if (n >= 1 && n <= 3)
        {
            if (n == 1) 
                new kodi_Kura_chitti_gare().selection(dbobj);
            else if (n == 2) 
                new Parampara().selection(dbobj);
            else 
                new Antera().selection(dbobj);
            
        }  
        else 
        {
            System.out.println(RED + "Invalid choice" + RESET);
            main(null);
        }
    }
}
