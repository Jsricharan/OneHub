import java.util.Scanner;

class User{
    static DB obj = new DB(); 
    static Scanner sc=new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String PURPLE = "\u001B[35m";
    public static final String ORANGE = "\u001B[38;5;208m";;


    static boolean isValidPassword(String password)
    {
        if(password.length() < 8 )
        {
            System.out.println(RED + "Password must be at least 8 characters long." + RESET);
            return false;
        }
        if(!password.matches(".*[!@#$%^&*()].*"))
        {
            System.out.println(RED + "Password must contain at least one special character (!@#$%^&*())." + RESET);
            return false;
        }
        if(!password.matches(".*[0-9].*"))
        {
            System.out.println(RED + "Password must contain at least one digit." + RESET);
            return false;
        }
        if(!password.matches(".*[a-z].*"))
        {
            System.out.println(RED + "Password must contain at least one lowercase letter." + RESET);
            return false;
        }
        if(!password.matches(".*[A-Z].*"))
        {
            System.out.println(RED + "Password must contain at least one uppercase letter." + RESET);
            return false;
        }
        return true;
    }

    static void resetPass(DB obj) {
        while (true) {
            System.out.print(CYAN + "Enter Mobile Number to reset password: " + RESET);
            String input = sc.next();
            long mob;

            try {
                mob = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input! Please enter digits only." + RESET);
                continue;
            }

            if (mob < 6000000000L || mob > 9999999999L) {
                System.out.println(RED + "Mobile number out of range. Try again." + RESET);
                continue;
            }

            if (mob == obj.getMobileno()) {
                while(true)
                {
                    int otp=obj.otp();
                    System.out.println(otp);
                    System.out.print("Enter otp : ");
                    if(otp==sc.nextInt())
                    {
                        System.out.println("OTP Verified Sucessfully...");
                        break;
                    }
                    else{
                        System.out.println("Invalid OTP Please try again...");
                    }
                }
                System.out.print(YELLOW + "Enter new Password: " + RESET);
                obj.setPassword(sc.next());
                System.out.println(GREEN + "Password Reset Successful!" + RESET);
                login(obj);
                break;
            } else {
                System.out.println(RED + "Invalid Mobile Number." + RESET);
                System.out.println(CYAN + "Press 1 to re-enter mobile number or any other key to exit: " + RESET);
                if (!sc.next().equals("1")) {
                    System.out.println(YELLOW + "Exiting password reset..." + RESET);
                    break;
                }
            }
        }
    }

    
    static void resetUser(DB obj)
    {
        System.out.print("Enter Mobile Number to reset Username: ");
        long mob=sc.nextLong();
        if(mob>=6000000000l && mob<=9999999999l)
        {
            if(mob==obj.getMobileno())
            {
                while(true)
                {
                    int otp=obj.otp();
                    System.out.println(otp);
                    System.out.print("Enter otp : ");
                    if(otp==sc.nextInt())
                    {
                        System.out.println("OTP Verified Sucessfully...");
                        break;
                    }
                    else{
                        System.out.println("Invalid OTP Please try again...");
                    }
                }
                System.out.print("Enter new UserName : ");
                obj.setUsername(sc.next());
                System.out.println("Username Reset Sucessfull.....");
                login(obj);
            }
            else
            {
                System.out.println("Invalid Mobile Number .....\npress 1 to re-enter mobile no or press any key to exit.....");
                if(sc.next().charAt(0)=='1')
                {
                    resetUser(obj);
                    main(null);
                }
            }
        }
        else
        {
            System.out.println("Invalid Mobile Number");
            System.out.println("press 1 to re-enter Mobile No\npress 2 to Login\npress any key to exit..");
            char opt=sc.next().charAt(0);
            if(opt=='1')
            {
                resetUser(obj);
            }
            else if(opt=='2')
            {
                login(obj);
            }
        }
    }
    static void showUser(DB obj)
    {
        System.out.print("Enter Mobile Number to Show Username: ");
        long mob=sc.nextLong();
        if(mob>=6000000000l && mob<=9999999999l)
        {
            if(mob==obj.getMobileno())
            {
                while(true)
                {
                    int otp=obj.otp();
                    System.out.println(otp);
                    System.out.print("Enter otp : ");
                    if(otp==sc.nextInt())
                    {
                        System.out.println("Your UserName : "+obj.getUsername());
                        break;
                    }
                    else{
                        System.out.println("Invalid OTP Please try again...");
                    }
                }
                login(obj);
            }
            else
            {
                System.out.println("Invalid Mobile Number .....\npress 1 to re-enter mobile no or press any key to exit.....");
                if(sc.next().charAt(0)=='1')
                {
                    showUser(obj);
                    //login(obj);
                }
                login(obj);
            }
        }
        else
        {
            System.out.println("Invalid Mobile Number");
            System.out.println("press 1 to re-enter Mobile No\npress 2 to Login\npress any key to exit..");
            char opt=sc.next().charAt(0);
            if(opt=='1')
            {
                showUser(obj);
            }
            else if(opt=='2')
            {
                main(null);
            }
        }
        
    }

    static void login(DB obj)
    {
        System.out.println(CYAN + "===================================" + RESET);
        System.out.println(YELLOW + "             LOGIN PORTAL          " + RESET);
        System.out.println(CYAN + "===================================" + RESET);
        System.out.print(CYAN + "Enter User Name : " + RESET);
        String user=sc.next();
        System.out.print(YELLOW + "Enter Password  : " + RESET);
        String pass=sc.next();
        if (user.equals(obj.getUsername()) && pass.equals(obj.getPassword())) {
            System.out.println(GREEN + "Login Successful!" + RESET);
            menu();
        } 
        else if (!user.equals(obj.getUsername()) && !pass.equals(obj.getPassword())) {
            System.out.println(RED + "Invalid Username and Password!" + RESET);
            showUser(obj);
        } 
        else if (!user.equals(obj.getUsername())) {
            System.out.println(RED + "Invalid Username!" + RESET);
            resetUser(obj);
        } 
        else {
            System.out.println(RED + "Invalid Password!" + RESET);
            resetPass(obj);
        }
    }


    static void signup(DB obj) {
    System.out.println(CYAN + "===================================" + RESET);
    System.out.println(YELLOW + "             SIGN UP               " + RESET);
    System.out.println(CYAN + "===================================" + RESET);

    System.out.print (ORANGE + "Enter Username       : "+RESET);
    String user = sc.next();
    String pass;
    while (true) 
        {   System.out.println(YELLOW  + "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&*())." + RESET);
            System.out.print("Enter password: ");
            pass = sc.next();
            if (isValidPassword(pass)) 
            {
                break;
            } 
            else 
            {
                System.out.println(YELLOW  + "Please try again...\n" + RESET);
            }
        } 
    System.out.print (GREEN + "Enter Mobile Number  : "+ RESET);
    long mb = sc.nextLong();
    

    if (mb >= 6000000000L && mb <= 9999999999L) {
        while(true)
                {
                    int otp=obj.otp();
                    System.out.println(otp);
                    System.out.print("Enter otp : ");
                    if(otp==sc.nextInt())
                    {
                        System.out.println("OTP Verified Sucessfully...");
                        break;
                    }
                    else{
                        System.out.println("Invalid OTP Please try again...");
                    }
                }
        obj.setMobileno(mb);
        obj.setPassword(pass);
        obj.setUsername(user);

        System.out.println(GREEN + "Sign Up Successful! You can log in now." + RESET);
        login(obj);
    } else {
        System.out.println(RED + "Invalid Mobile Number! Please try again." + RESET);
        signup(obj);
    }
}

    static void menu()
    {
        System.out.println(CYAN + "     ============================================" + RESET);
        System.out.println(GREEN + "                   Select any one" + RESET);
        System.out.println(CYAN + "     ============================================" + RESET);

        System.out.println(YELLOW + "       1. Movie Tickets Booking" + RESET);
        System.out.println(BLUE   + "       2. Travelling (Bus, Train, Car, Bike, Flight)" + RESET);
        System.out.println(GREEN  + "       3. Donation" + RESET);
        System.out.println(RED    + "       4. Food Order" + RESET);
        System.out.println(PURPLE + "       5. Check Balance" + RESET);
        System.out.println(YELLOW + "       6. Top-Up Wallet" + RESET);
        System.out.println(RED    + "       7. Exit" + RESET);

        System.out.println(CYAN + "     ============================================" + RESET);
        char opt=sc.next().charAt(0);
        if(opt=='1')
        {
            Movie.dbobj = obj;
            Movie.main(null);
        }
        else if(opt=='2')
        {
            BookingApp.dbobj = obj;
            BookingApp.main(null);
        }
        else if(opt=='3')
        {
            //Category.db=obj;
            HelperColoredFinal.db=obj;
            HelperColoredFinal.main(null);

        }
        else if(opt=='4')
        {
            Food.dbobj=obj;
            Food.main(null);
        }
        else if(opt=='5')
        {
            System.out.println(CYAN+"Your balance : "+RESET+obj.getBalance());
            menu();
        }
        else if(opt=='6')
        {
            System.out.print("Enter Amount to Top-Up : ");
            int a=sc.nextInt();
            if(a>0)
            {
                System.out.println("TopUp Sucessfull..");
                obj.setBalance(a+obj.getBalance());
            }
            else{
                System.out.println("Invalid Amount");
            }
            menu();
        }
        else if(opt=='7')
        {
            main(null);
        }
        else
        {
            System.out.println(RED+"Invalid Option please try again later.."+RESET);
            menu();
        }
    }
    public static void main(String args[])
    {
        System.out.println(CYAN + "==========================================================" + RESET);
        System.out.println(PURPLE + "               >> WELCOME TO " + YELLOW + "ONEHUB"+PURPLE+" <<" + RESET);
        System.out.println(CYAN + "==========================================================" + RESET);
         System.out.println(BLUE + "         +-------------------------+" + RESET);
        System.out.println(BLUE + "         |        " + YELLOW + "MAIN MENU" + BLUE + "        |" + RESET);
        System.out.println(BLUE + "         +-------------------------+" + RESET);
        System.out.println(BLUE + "         | " + GREEN + "1. Login" + BLUE + "                |" + RESET);
        System.out.println(BLUE + "         | " + GREEN + "2. SignUp" + BLUE + "               |" + RESET);
        System.out.println(BLUE + "         | " + RED + "3. Exit " + BLUE + "                |" + RESET);
        System.out.println(BLUE + "         +-------------------------+" + RESET);
        System.out.print(CYAN + "Choose an option: " + RESET);
        char ch=sc.next().charAt(0);
        if(ch=='1')
        {
            login(obj);
        }
        else if(ch=='2')
        {
            signup(obj);
        }
        else if(ch=='3')
        {
             System.out.println(BLUE   + "THANK YOU VISIT AGAIN " + RESET);
            return;
        }
        else
        {
            System.out.println(RED + " Invalid Input " + RESET);
            main(args);
        }
    }
}