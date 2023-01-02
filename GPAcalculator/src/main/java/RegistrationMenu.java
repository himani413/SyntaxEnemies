import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RegistrationMenu {
    public static int stdID;
    public static String userName;

    public static int registrationMenu(Connection conn, InetAddress add) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String address = add.toString();
        //System.out.println(address);
        String sql = "SELECT * FROM registration";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        //Check if the host address in already registered
        while (result.next()){
            String host_address = result.getString(1);
            String user_name = result.getString(2);
            String sID = result.getString(4);
            if(host_address.equals(address)){
                System.out.println("Hello "+ user_name +", welcome back");
                statement.close();
                stdID =Integer.parseInt(sID);
                userName = user_name;
                return 0;
            }

        }

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Course duration(in years): ");
        int years = sc.nextInt();
        if((years < 3) || (years > 4) ){
            System.out.println("You have entered wrong duration (duration should be 3 or 4 years)");
            return  -1;
        }
        sql = "INSERT INTO registration VALUES ('"+add+"','"+name+"',"+years+")";
        statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("\n\nCongratulation! Registration was completed.");
        statement.close();
        return 1;



    }
}
