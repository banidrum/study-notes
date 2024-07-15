public class Exercise09 {

    public record TimeOfDay(int hour, int minute) {
        public TimeOfDay {
            hour %= 24;
            hour += minute / 60;
            minute %= 60;
        }

        public TimeOfDay plusMinutes(int minutes) {
            return new TimeOfDay(this.hour, this.minute + minutes);
        }

        public int minutesFrom(TimeOfDay other) {
            int hourDifference = 0;
            int minuteDifference = 0;

            return Math.abs(hourDifference + minuteDifference);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d", hour, minute);
        }

    }

    public static void main(String args[]) {
        TimeOfDay noon = new TimeOfDay(12, 19);
        TimeOfDay thirteenOClock = noon.plusMinutes(41);

        System.out.println(thirteenOClock.toString());
    }
}