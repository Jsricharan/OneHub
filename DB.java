import java.util.Random;

public class DB {
    private String username="Yash";
    private String password="Yash";
    private long mobileno=9876543211l;
    private double balance=5000;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getMobileno() {
        return mobileno;
    }
    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    } 

    public int otp()
    {
        int otp=1000+(int)(Math.random()*8999);
        return otp;
    }
    
}
