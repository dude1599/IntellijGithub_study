package lesson3;
import java.util.Arrays;
public class SimpleArray {
    private static int[] arrays = new int[10];

    private static int[] answer = new int[]{0, 1, 4, 16, 25, 49, 64, 81, 121, 144};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            fillArray(i);
        }
        boolean isMatched = true;
        for (int i = 0; i < 10; i++) {
            if (arrays[i] != answer[i]) {
                System.out.printf("값이 일차하지 않습니다. (인덱스 %d)\n", i);
                isMatched = false;
            }
        }
        if (isMatched) {
            System.out.println("정답입니다.");
        } else {
            System.out.println("오답입니다.");
        }
    }
    /*
     * (index x 7 / 5) ^ 2
     */
    private static void fillArray(int index) {
        arrays[index] = (index * 7 / 5) * (index * 7 / 5);  // index가 정수이므로 괄호 안의 식이 계산되면서 정수로 변환된다.
    }
}
