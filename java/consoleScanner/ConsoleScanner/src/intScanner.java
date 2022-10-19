import java.util.Scanner;

public class intScanner {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int a,b;

        System.out.print("A Sayısını giriniz: ");
        a = input.nextInt();

        System.out.print("B Sayısını giriniz: ");
        b = input.nextInt();

        System.out.println("Sayıların toplamı: " + (a + b));

        
    }

}
