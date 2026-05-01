import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class Movie
{
	static Scanner sc=new Scanner(System.in);
	static HashMap<String,Boolean> Seats=new HashMap<>();
	static ArrayList<String> mT = new ArrayList<>();	//store movie theaters list
	static ArrayList<String> ml = new ArrayList<>();	//store movies list
	static ArrayList<String> TempSeats = new ArrayList<>(6);
	static String[] dateArray = new String[4];
	static String[] time = new String[5];
	static int noSeats;
	final String RESET = "\u001B[0m";
	final String RED = "\u001B[31m";
	final String GREEN = "\u001B[32m";
	final String YELLOW = "\u001B[33m";
	final String BLUE = "\u001B[34m";
	final String CYAN = "\u001B[36m";
	final String PURPLE = "\u001B[35m";
	final String WHITE_BG = "\u001B[47m";
	final String BLACK = "\u001B[30m";
	static DB dbobj;



	// Constructor is used to initalize the seats...
	static
	{
		// Set all Seats as False initally...
		for(char i='A'; i<='M'; i++)
		{
			for(int j=1; j<=21; j++)
			{
				Seats.put(""+i+j,false);
			}
		}
		Seats.put("D10",true);
		Seats.put("D13",true);
		Seats.put("E11",true);
		//add movies to movie list(ml)
		ml.add("Little Hearts");
		ml.add("Mirai");
		ml.add("OG");
		ml.add("Kotha Lokah");
		ml.add("Kishkindhapuri");
		//store 4 dates in array starting from now
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for(int i=0; i<4; i++)
		{
			LocalDate dates = today.plusDays(i);
			String formattedDate = dates.format(formatter);
			dateArray[i] = formattedDate;

		}
		//store time in array
		time[0]="10.40 AM";
		time[1]="01.25 PM";
		time[2]="04.15 PM";
		time[3]="07.30 PM";
		time[4]="10.00 PM";

		//movie theater
		mT.add("AMB Cinemas : Gachibowli");
		mT.add("Prasad Multiplex : Hyderabad");
		mT.add("PVR : Nexus, Kukatpally");
		mT.add("Aparna Cinemas : Nallagonda");
		mT.add("AAA Cinemas : Ameerpet");
		mT.add("Cinepolis : Lulu, Hyderabad");

	}

	void movielist() {
		for (int i = 0; i < ml.size(); i++) {
			System.out.println((i + 1) + ". " + ml.get(i));
		}
	}
	void movieTheater() {
		for (int i = 0; i < mT.size(); i++) {
			System.out.println((i + 1) + ". " + mT.get(i));
		}
	}
	void dates()
	{
		for (int i = 0; i < dateArray.length; i++) {
			System.out.println((i+1)+". "+dateArray[i]);
		}

	}
	void seatLayout()
	{
		System.out.println("\n\n\n\t\tPrice : 250");
		System.out.println("\t\t" + PURPLE + "=========================================  "+ RED+"Seat Layout"+PURPLE +"  =========================================\n" + RESET);
		for (char i = 'M'; i >= 'A'; i--) {
			System.out.print("\t" + i + "\t");
			for (int j = 1; j <= 21; j++) {
				String seat = i + String.valueOf(j);
				if (TempSeats.contains(seat)) {
					System.out.print(YELLOW + "[" + j + "] " + RESET);
				} else if (Seats.get(seat)) {
					System.out.print(RED + "[" + j + "] " + RESET);
				} else {
					System.out.print(GREEN + "[" + j + "] " + RESET);
				}
			}
			System.out.println();
		}
		System.out.println(PURPLE + "\n\t\t=============================================================================================" + RESET);
		System.out.println(CYAN + "\t\t\t\t\t\t\t Screen This Side " + RESET);
		System.out.println(PURPLE + "\t\t=============================================================================================" + RESET);
		System.out.println(GREEN+"\t\t[O]"+RESET+" Avaliable"+YELLOW+"\t\t\t\t[O]"+RESET+" Selected"+RED+"\t\t\t\t[O]"+RESET+" Occupied");
	}
	void receipt(int cart,int movieIndex,int TheaterIndex, int dateIndex, int timeIndex,double tax)
	{
		System.out.println("please Wait...");
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
		String borderTop = CYAN + "+------------------------------------------------------+" + RESET;
		String borderBottom = CYAN + "+------------------------------------------------------+" + RESET;
		String borderMid = CYAN + "|------------------------------------------------------|" + RESET;

		System.out.println("\n" + borderTop);
		System.out.println(PURPLE + "|\t\t   MOVIE BOOKING RECEIPT             \t|" + RESET);
		System.out.println(borderMid);

		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Movie", ml.get(movieIndex));
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Theater", mT.get(TheaterIndex));
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Date", dateArray[dateIndex]);
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Time", time[timeIndex]);
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Seats", TempSeats);
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Price",  cart);
		System.out.printf(GREEN + "|  %-10s : %-35s    |\n" + RESET, "Tax",  tax);

		System.out.println(borderMid);
		System.out.println(YELLOW + "|     Thank you for booking with us! Enjoy Show        |" + RESET);
		System.out.println(borderBottom + "\n\n");
	}

	void movieBooking()
	{
		System.out.println(CYAN + "==============================================" + RESET);
		System.out.println(PURPLE + "\t\t  Welcome to Movie Booking  " + RESET);
		System.out.println(CYAN + "==============================================" + RESET);
		System.out.println(GREEN + "--- Movie List ---" + RESET);
		movielist();
		System.out.print(YELLOW + " Select any one of the movies: " + RESET);
		int movieIndex;
		while(true)
		{
			movieIndex = sc.nextInt() - 1;
			if(movieIndex<ml.size())
			{
				break;
			}
			else {
				System.out.println(RED+"Invalid Movie in above list"+RESET);
				System.out.print("Please Enter valid movie in above list : ");
			}
		}
		//select date
		System.out.println("\n\n" + GREEN + "----- Select Date -----" + RESET);
		dates();
		int dateIndex;
		while(true)
		{
			dateIndex=sc.nextInt()-1;
			if(dateIndex<4)
			{
				break;
			}
			else
			{
				System.out.println(RED+"Invalid date.."+RESET);
				System.out.print("Re-enter Valid date in the above list : ");
			}
		}
		//select time
		System.out.println("\n\n" + GREEN + "----- Select Time -----" + RESET);
		for(int i=0; i<time.length; i++)
		{
			System.out.println((i+1)+". "+time[i]);
		}
		int timeIndex;
		while(true)
		{
			timeIndex=sc.nextInt()-1;
			if(timeIndex<time.length)
			{
				break;
			}
			else
			{
				System.out.println(RED+"Invalid Time.."+RESET);
				System.out.print("Re-enter Valid Time in the above list : ");
			}
		}
		System.out.println("\n\n" + GREEN + "--- Select Theater ---" + RESET);
		movieTheater();
		System.out.print(YELLOW + " \n\nChoose the Theater: " + RESET);
		int TheaterIndex;
		while(true)
		{
			TheaterIndex = sc.nextInt() - 1;
			if(TheaterIndex<mT.size())
			{
				break;
			}
			else {
				System.out.println(RED+"Invalid Theater in above list"+RESET);
				System.out.print("Please Enter valid Theater in above list : ");
			}
		}
		System.out.print(CYAN + "\n\nHow many Seats? (1-6): " + RESET);
		while(true)
		{
			noSeats = sc.nextInt();
			if(noSeats<=6)
			{
				break;
			}
			else {
				System.out.println(RED+"Only 6 Seats per Single Booking"+RESET);
				System.out.print("Please Re-enter Seats : ");
			}
		}
		for (int i = 1; i <= noSeats; ) {
			seatLayout();
			System.out.print(YELLOW + " \n\nEnter Seat Number (A1-M21): " + RESET);
			String seat = sc.next().toUpperCase();
			if (!Seats.containsKey(seat)) {
				System.out.println(RED + " Invalid Seat...." + RESET);
			} else if (Seats.get(seat) || TempSeats.contains(seat)) {
				System.out.println(RED + " Seat Already Booked, please try another..." + RESET);
			} else {
				TempSeats.add(seat);
				System.out.println(GREEN + " Successfully selected seat...." + RESET);
				i++;
			}
		}
		System.out.print("Proceed to payment?(Y/N): ");
		if(sc.next().toUpperCase().charAt(0)=='Y')
		{
			int cart=250*noSeats;
			double tax=cart*0.08;
			if (dbobj.getBalance() >= (cart+tax)) {
        		double b = dbobj.getBalance() - (cart+tax);
        		dbobj.setBalance(b);
        		System.out.println(GREEN + "\n\n Tickets booked Successfully......" + RESET);
			receipt(cart,movieIndex,TheaterIndex,dateIndex,timeIndex,tax);
        	for (String s : TempSeats) {
				Seats.put(s, true);
				System.out.println("Do you Book another Ticket ?....(y/n)");
				char jt=sc.next().charAt(0);
				if(jt=='y')
				{
					movieBooking();
				}
				else
					User.menu();
			}
			TempSeats.clear();
    	} 	
		else {
        	System.out.println(RED + " Insufficient Funds, please try again....." + RESET);
			TempSeats.clear();
    		}
		}
		else{
			System.out.println("Your Booking has been cancelled..! Thank you for Visiting.");
			TempSeats.clear();
		}
		

	}

	//Main Method
	public static void main (String[] args) {
		Movie m=new Movie();
		m.movieBooking();

	}
}