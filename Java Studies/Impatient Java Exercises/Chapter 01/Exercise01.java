import java.util.Random;
import java.util.Scanner;

public class Exercise01 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        int decimalNumber = scanner.nextInt();

        String intToBinary = Integer.toBinaryString(decimalNumber);

        String intToOctal = Integer.toOctalString(decimalNumber);

        String intToHexadecimal = Integer.toHexString(decimalNumber);

        System.out.printf("Binary value is: %s\n", intToBinary);
        System.out.printf("Octal value is: %s\n", intToOctal);
        System.out.printf("Hexadecimal value is: %s\n", intToHexadecimal);

        scanner.close();
    }
}