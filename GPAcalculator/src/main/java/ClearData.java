import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClearData {
    Scanner sc = new Scanner(System.in);
    public void clearAll(Connection conn , int stdID) throws SQLException {
        System.out.println("----Clear Data----");
        System.out.println("Clear all data (y/n) : ");
        String ans = sc.nextLine();
        if(ans.equals("y")){
            System.out.println("Confirm (y/n) : ");
            String ans1 = sc.nextLine();
            if(ans1.equals("y")){
                String sql = "DELETE FROM results WHERE ID = "+stdID+";";
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Successfully deleted all records\n\n");
            }
        }
    }
}
