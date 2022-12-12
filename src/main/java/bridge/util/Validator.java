package bridge.util;

public class Validator {

    public static void validateBridgeSize(int bridgeSize) {
        boolean inRange = 3 <= bridgeSize && bridgeSize <= 20;
        if (!inRange) {
            throw new IllegalArgumentException("[ERROR] 다리의 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }

    public static void validateMoving(String moving) {
        boolean notRightMoving = !(moving.equals("D") || moving.equals("U"));
        if (notRightMoving) {
            throw new IllegalArgumentException("[ERROR] 이동할 칸은 D 또는 U여야 합니다.");
        }
    }

    public static void validateGameCommand(String gameCommand) {
        boolean notGameCommand = !(gameCommand.equals("R") || (gameCommand.equals("Q")));
        if (notGameCommand) {
            throw new IllegalArgumentException("[ERROR] 게임 명령어는 R 또는 Q여야 합니다.");
        }
    }
}
