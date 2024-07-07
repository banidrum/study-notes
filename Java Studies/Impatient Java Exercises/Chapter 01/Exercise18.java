import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Exercise18 {
    public static void main(String[] args) {
        ArrayList<Integer> lotteryNumbers = new ArrayList<>(49);
        ArrayList<Integer> drawnNumbers = new ArrayList<>();

        for (int number = 1; number <= 49; number++) {
            lotteryNumbers.add(number);
        }

        while (drawnNumbers.size() < 7) {
            var randomIndex = new Random().nextInt(lotteryNumbers.size());
            randomIndex = lotteryNumbers.remove(randomIndex);
            drawnNumbers.add(randomIndex);
        }

        Collections.sort(drawnNumbers);

        System.out.println(drawnNumbers);
    }
}
