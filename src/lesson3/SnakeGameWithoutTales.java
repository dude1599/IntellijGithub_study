package lesson3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SnakeGameWithoutTales {
    private static final int BOARD_SIZE = 10;
    // 0 - 빈 타일
    // 1 - 스네이크 블럭
    // 2 - 아이템
    private static final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    //10*10 게임판 생성
    private static final Random RANDOM = new Random();
    // 난수 발생
    private static int score = 0;

    private static SnakeLocation location = new SnakeLocation(0, 0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.print("[우측 (r) | 좌측 (l) | 위 (u) | 아래 (d) | 종료 (0) ] : ");
            if (!nextDirection(scanner.next())) {   //nextDirection()이 false일 때
                System.out.println("게임 오버!");
                System.out.printf("점수: %d\n", score);
                break;
            }
            if (!hasItemOnBoard())                  // hasItemOnBoard()가 false일 때
                placeRandomItem();
        }
    }

    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
     * 사용자의 입력을 받고, 다음 위치로 옮기거나 게임을 종료해야 합니다.
     * <p>
     * 허용되는 입력은 다음과 같습니다 :
     * - 우측(r) | 좌측 (l) | 위 (u) | 아래 (d) | 종료 (0)
     * <p>
     * 다음 좌표는 location 변수에 계속해서 업데이트되어야 합니다.
     * 만약 다음 좌표에 아이템이 존재한다면, 점수를 1 증가하고 다음 좌표의 값을 0으로 되돌려야 합니다.

     * 만약 값이 최대 값 (BOARD_SIZE)이상이 되거나 최소 값(0) 아래로 내려간다면 같은 좌표로 설정하여 이동하지 않도록 해야합니다.
     *
     * 만약 사용자의 입력이 종료(0)였다면, false값을 반환하여 게임을 종료해야 합니다.
     */
    private static boolean nextDirection(String keyword) {
        switch (keyword) {
            case "r":
                location = location.adjust(0, 1); // 오른쪽으로 이동
                break;
            case "l":
                location = location.adjust(0, -1); // 왼쪽으로 이동
                break;
            case "u":
                location = location.adjust(-1, 0); // 위로 이동
                break;
            case "d":
                location = location.adjust(1, 0); // 아래로 이동
                break;
            case "0":
                return false; // 게임 종료
            default:
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                break;
        }

        // 위치가 보드 범위를 벗어나는지 확인
        if (location.getX() < 0)
            location = location.adjust(1, 0); // 왼쪽 벽으로 통과
        else if (location.getX() >= BOARD_SIZE)
            location = location.adjust(-1, 0); // 오른쪽 벽으로 통과
        else if (location.getY() < 0)
            location = location.adjust(0, 1); // 위쪽 벽으로 통과
        else if (location.getY() >= BOARD_SIZE)
            location = location.adjust(0, -1); // 아래쪽 벽으로 통과

        // 아이템을 먹었는지 확인
        if (board[location.getX()][location.getY()] == 2) {
            score++; // 점수 증가
            board[location.getX()][location.getY()] = 0; // 아이템 제거
        }

        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (location.getX() == x && location.getY() == y) {
                    System.out.print("◼ ");
                    continue;
                }
                switch (board[x][y]) {
                    case 0:
                        System.out.print("・ ");
                        break;
                    case 1:
                        System.out.print("◼");
                        break;
                    case 2:
                        System.out.print("* ");
                        break;
                }
            }
            System.out.println();
        }
    }

    private static void placeRandomItem() {
        // for문의 조건으로 랜덤을 넣을 경우, 계속 비교하여 최종 결과값이 바뀌므로 변수로 선언합니다.
        int toPlace = (int) (RANDOM.nextDouble() * 10); // 0부터 9까지 why? Random.nextdouble()이 0.1~1미만
        for (int i = 0; i < toPlace; i++) {
            int retry = 0;
            while (retry < 5) {
                SnakeLocation locate = new SnakeLocation((int) (RANDOM.nextDouble() * (BOARD_SIZE - 1)), (int) (RANDOM.nextDouble() * (BOARD_SIZE - 1)));
                if (board[locate.getX()][locate.getY()] != 0) {
                    retry++;
                    continue;
                }
                board[locate.getX()][locate.getY()] = 2;
                break;
            }
        }
    }

    private static boolean hasItemOnBoard() {       // *(아이템)
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class SnakeLocation {
        private final int x;
        private final int y;

        public SnakeLocation(int x, int y) {        // 위치 설정
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public SnakeLocation adjust(int x, int y)   // 새로운 위치 조정
        {
            return new SnakeLocation(this.x + x, this.y + y);
        }
    }

}
