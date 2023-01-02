import java.util.Scanner;

public class MainMenu {
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Menu----");
        System.out.println("1. View results");
        System.out.println("2. Add results");
        System.out.println("3. Export data");
        System.out.println("4. Clear data");
        System.out.println("5. Exit");
        int ans = 0;
        while(true) {
            System.out.print("\nEnter your choice: ");
            ans = sc.nextInt();
            if((ans>=1) && (ans<=5)){
                return ans;
            }
            System.out.println("you have entered a wrong choice!");
        }


    }
}
