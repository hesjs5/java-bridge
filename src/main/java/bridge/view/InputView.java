package bridge.view;

import static bridge.util.TypeConversion.stringToInt;

import bridge.util.Validator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        int bridgeSize;
        do {
            System.out.println("다리의 길이를 입력해주세요.");
            String readLine = Console.readLine();
            bridgeSize = checkBridgeSize(readLine);
            System.out.println();
        } while (!((3 <= bridgeSize) && (bridgeSize <= 20)));

        return bridgeSize;
    }

    private int checkBridgeSize(String readLine) {
        int bridgeSize = Integer.MIN_VALUE;
        try {
            bridgeSize = stringToInt(readLine);
            Validator.validateBridgeSize(bridgeSize);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return bridgeSize;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String moving;
        do {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            moving = Console.readLine();
            try {
                Validator.validateMoving(moving);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!(moving.equals("D") || (moving.equals("U"))));

        return moving;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        String gameCommand;
        do {
            System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
            gameCommand = Console.readLine();
            if (!(gameCommand.equals("R") || (gameCommand.equals("Q")))) {
                try {
                    throw new IllegalArgumentException("[ERROR]");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!(gameCommand.equals("R") || (gameCommand.equals("Q"))));

        return gameCommand;
    }
}
