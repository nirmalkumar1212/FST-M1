public class Activity2 {
    public static void main(String[] args) {
        int numbers[] = { 10, 77, 10, 54, -11, 10 };

        int checkNum = 10;
        int expectedNum = 30;
        int temp = 0;

        for (int i : numbers) {

            if (i == checkNum) {
                temp += i;

            }

        }

        System.out.println("Result: "+(temp == expectedNum ? "true" : "false"));

    }
}