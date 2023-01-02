import javax.xml.transform.Result;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddResults {
    private int year;
    private String courseCode;
    private String grade;
    private int credits;

    private float  gpa;
    private  int noOfSub;
    private int totalCredit;

    Scanner sc = new Scanner(System.in);
    AddResults(){
        while(true) {
            System.out.println("----Add Results----");
            System.out.print("Year(1,2,3,4): ");
            this.year = sc.nextInt();
            sc.nextLine();
            System.out.print("Course code: ");
            this.courseCode = sc.nextLine();
            System.out.print("Grade: ");
            this.grade = sc.nextLine();
            System.out.print("Credits: ");
            this.credits = sc.nextInt();
            sc.nextLine();
            System.out.println();
            System.out.print("Confirm (y/n): ");
            String ans = sc.nextLine();
            if(ans.equals("y")){
                break;
            }
            else if(!(ans.equals("n"))){
                System.out.println("You have entered a wrong letter(Enter y or n). ");
            }
        }

    }
    public void addRecord(Connection conn, InetAddress add, int stdID) throws SQLException {
        /*String sql = "SELECT ID FROM registration WHERE ip_address = 'Masith/192.168.119.145';";
        //System.out.println(sql);
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        String ID = null;
        while (result.next()){
            ID = result.getString(1);
            //System.out.println(ID);
        }
        int stdID =Integer.parseInt(ID);*/

        String sql = "INSERT INTO results VALUES ("+ stdID + ",'"+courseCode+"',"+year+",'"+grade+"',"+credits+ ");" ;
        System.out.println(sql);
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("\nRecord was added!");

    }



}
