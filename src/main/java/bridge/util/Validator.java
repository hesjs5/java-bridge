package bridge.util;

public class Validator {

    public static void validateBridgeSize(int bridgeSize) {
        boolean inRange = 3 <= bridgeSize && bridgeSize <= 20;
        if (!inRange) {
            throw new IllegalArgumentException("[ERROR] 다리의 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }
}
