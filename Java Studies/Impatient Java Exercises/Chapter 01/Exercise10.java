import java.util.Random;

public class Exercise10 {
    public static void main(String[] args) {
        var random = new Random();
        var randomLong = random.nextLong();

        System.out.println(Long.toString(randomLong, 36));
    }
}
