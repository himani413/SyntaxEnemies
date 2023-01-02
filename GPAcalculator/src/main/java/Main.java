import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/gpacalculator";
        String username = "root";
        String password = "masith23";
        InetAddress ip;
        String hostname;
        try {
            //database connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");

            //  and recognize pc's local host address
            ip = InetAddress.getLocalHost();
            //hostname = ip.getHostName();
            System.out.println("------------------GPA Calculator------------------");
            int ans1 = -1;
            while (ans1 == -1){
                ans1 = RegistrationMenu.registrationMenu(connection, ip);
            }
            while(true) {
                int ans2 = MainMenu.menu();
                if(ans2 == 5){
                    break;
                }
                switch (ans2) {
                    case 1:

                        ViewResults view = new ViewResults();
                        view.viewResults(connection,RegistrationMenu.stdID);
                        break;
                    case 2:
                        AddResults add = new AddResults();
                        add.addRecord(connection, ip, RegistrationMenu.stdID);
                        break;
                    case 3:
                        ExportData exp = new ExportData();
                        exp.exporting(connection,RegistrationMenu.stdID);
                        break;
                    case 4:
                        ClearData cld = new ClearData();
                        cld.clearAll(connection, RegistrationMenu.stdID);
                }
            }

        }
        catch(SQLException e){
            System.out.println("Error of connecting database!");
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host!");
            e.printStackTrace();
        }



    }
}
