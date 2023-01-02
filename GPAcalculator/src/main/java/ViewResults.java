import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewResults {
    private float  gpa;
    private  int noOfSub;
    private int totalCredit;

    Scanner sc = new Scanner(System.in);

    public int viewResults(Connection conn,int stdID) throws SQLException {
        String AvaGrade;
        String credits;
        String sujectCount = null;
        String creditCount = null;
        String sql = "SELECT COUNT(course_code) FROM results WHERE ID = "+ stdID+";";
        //System.out.println(sql);
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            sujectCount= result.getString(1);
        }
        noOfSub =Integer.parseInt(sujectCount);

        sql = "SELECT SUM(credits) FROM results WHERE ID = "+ stdID+";";
        statement = conn.createStatement();
        result = statement.executeQuery(sql);
        while (result.next()){
            creditCount= result.getString(1);
        }
        totalCredit =Integer.parseInt(creditCount);

        sql = "SELECT grade,credits FROM results WHERE ID = "+ stdID+" ;";
        statement = conn.createStatement();
        result = statement.executeQuery(sql);
        String Grade;
        int credit;
        float calGpa = 0;
        while (result.next()){
            Grade = result.getString(1);
            credits = result.getString(2);
            credit =Integer.parseInt(credits);

            float gpv = 0;
            switch (Grade){
                case "A+":
                case "A":
                    gpv = 4.0F;
                    break;
                case "A-":
                    gpv = 3.7F;
                    break;
                case "B+":
                    gpv = 3.3F;
                    break;
                case "B":
                    gpv = 3.0F;
                    break;
                case "B-":
                    gpv = 2.7F;
                    break;
                case "C+":
                    gpv = 2.3F;
                    break;
                case "C":
                    gpv = 2.0F;
                    break;
                case "C-":
                    gpv = 1.7F;
                    break;
                case "D+":
                    gpv = 1.3F;
                    break;
                case "D":
                    gpv = 1F;
                    break;
            }

            calGpa += gpv*credit;
            //System.out.println(ID);
        }
        gpa =Math.round((calGpa/totalCredit)*100/100);

        System.out.println("Current GPA: "+gpa);
        System.out.println("No. of subjects: "+noOfSub);
        System.out.println("Total credits: "+totalCredit);
        while(true) {
            System.out.println("To view records: ");
            System.out.println("1. Year 1\n2. Year 2\n3. Year 3\n4. Year 4 \n\n0. Back");
            System.out.print("\nEnter your choice: ");
            int ans = sc.nextInt();
            if (ans == 0) {
                return 0;
            }

            sql = "SELECT *  FROM results WHERE ID = " + stdID + " AND year = " + ans + ";";
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("----Year " + ans + "----");
            String courseCode;
            int i = 1;
            while (result.next()) {
                courseCode = result.getString(2);
                Grade = result.getString(4);
                credits = result.getString(5);
                System.out.println(i + ". " + " Course code - " + courseCode + "\n" + "\tGrade - " + Grade + "\n\tCredits - " + credits);
                i++;
            }
            float yearGpa;
            String yearAvaGrade;
            String yearcredits;
            String yearSujectCount = null;
            String yearCreditCount = null;
            sql = "SELECT COUNT(course_code) FROM results WHERE ID = " + stdID + " AND year=" + ans + ";";
            //System.out.println(sql);

            //System.out.println(sql);
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                yearSujectCount = result.getString(1);
            }


            sql = "SELECT SUM(credits) FROM results WHERE ID = " + stdID + " AND year=" + ans + ";";
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                yearCreditCount = result.getString(1);
            }
            int yeartotalCredit = Integer.parseInt(yearCreditCount);

            sql = "SELECT grade,credits FROM results WHERE ID = " + stdID + " AND year=" + ans + ";";
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            int yearCredit;
            float yearCalGpa = 0;
            while (result.next()) {
                Grade = result.getString(1);
                yearcredits = result.getString(2);
                credit = Integer.parseInt(yearcredits);

                float gpv = 0;
                switch (Grade) {
                    case "A+":
                    case "A":
                        gpv = 4.0F;
                        break;
                    case "A-":
                        gpv = 3.7F;
                        break;
                    case "B+":
                        gpv = 3.3F;
                        break;
                    case "B":
                        gpv = 3.0F;
                        break;
                    case "B-":
                        gpv = 2.7F;
                        break;
                    case "C+":
                        gpv = 2.3F;
                        break;
                    case "C":
                        gpv = 2.0F;
                        break;
                    case "C-":
                        gpv = 1.7F;
                        break;
                    case "D+":
                        gpv = 1.3F;
                        break;
                    case "D":
                        gpv = 1F;
                        break;
                }

                yearCalGpa += gpv * credit;
                //System.out.println(ID);
            }
            yearGpa = Math.round(yearCalGpa / yeartotalCredit);
            System.out.println("No. of subjects: " + yearSujectCount);
            System.out.println("Total credit: " + yearCreditCount);
            System.out.println("GPA for year " + ans + ": " + yearGpa);

            System.out.println("\n\nTo delete records press d");
            System.out.println("To go back press 0");
            System.out.println("\nEnter your choice: ");
            sc.nextLine();
            String ans1 = sc.nextLine();
            if(ans1.equals("d")){
                System.out.println("Enter record id to delete record: ");
                int ans2  = sc.nextInt();
                sql = "SELECT * FROM results WHERE ID = " + stdID + " AND year=" + ans + ";";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                i = 1;
                String deleteID = null;
                while (result.next()) {
                    if(i==ans2){
                        deleteID = result.getString(2);
                        //System.out.println(deleteID);
                        break;
                    }
                    i++;
                }
                sql = "DELETE FROM results WHERE ID = "+stdID+" AND course_code = '"+deleteID+"';";
                statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Successfully delete a record\n\n");


            }
        }

    }



}
