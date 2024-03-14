package kr.easw.lesson01;

import java.util.Scanner;

// 입력된 달에 따라 해당하는 달의 일수를 출력하는 예제입니다.
public class DayInMonthSwitchExample {
    public static void main(String[] args) {
        System.out.println("일수를 알고 싶은 달을 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextInt()) {
                // switch문은 "case 데이터:" 혹은 "default:"로 구성됩니다.
                //
                // 혹은, Java 14부터 추가된 화살표(arrow switch) 블럭을 통해 간단히 break 없이 구현할 수도 있습니다.
                // 해당 구문은 표시를 위한 테스트입니다.
                //
                // 화살표 블럭은 해당 블럭 안에서만 코드가 실행되며, break가 있는 것 처럼 작동합니다.
                // 다음은 화살표 블럭의 예제입니다 :
                //
                // case 4, 6, 9, 11 -> System.out.println("30일 입니다.");
                // default -> System.out.println("잘못된 입력입니다.");
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("30일 입니다.");
                    break;
                case 2: {
                    System.out.println("28일 입니다.");
                    break;
                }
                // default문은 해당하는 case가 없을 경우 실행됩니다.
                // 이 경우, 12월을 벗어나는 경어에 대한 처리가 없기 때문에 대부분의 경우 if문을 통해 비교해야 합니다.
                default: {
                    System.out.println("31일 입니다.");
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("숫자가 아닙니다.");
        }
    }
}