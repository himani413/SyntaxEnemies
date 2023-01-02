import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExportData {
    public void exporting(Connection conn,int sdtID){
        System.out.println("----Export Data---");
        System.out.println("Exporting your data...");


        try{
            String query =" SELECT * FROM results WHERE id = "+ sdtID+";"; // take the user's course module details.

            // create the java statement
            Statement statement = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = statement.executeQuery(query);

            FileWriter writter = new FileWriter(RegistrationMenu.userName+".txt", true);
            writter.append("Year");
            writter.append("\tcourse code");
            writter.append("\tgrade");
            writter.append("\tcredits");
            writter.append("\n");
            // iterate through the java resultset
            while (rs.next()) {

                int year = rs.getInt(3);
                String course_code = rs.getString(2);
                String grade = rs.getString(4);
                int credits = rs.getInt(5);

                writter.append(String.valueOf(year));
                writter.append("\t" +course_code);
                writter.append("\t" +grade);
                writter.append("\t"+String.valueOf(credits));
                writter.append("\n");
            }

            writter.close();

        }catch (Exception e){
            System.out.println(e);
        }


        System.out.println("Done!");
    }
}
