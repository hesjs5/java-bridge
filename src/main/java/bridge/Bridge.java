package bridge;

import java.util.List;

public class Bridge {

    private final List<String> bridge;

    public Bridge(List<String> bridge) {
        validateBridgeSize(bridge);
        validateBridgeValue(bridge);
        this.bridge = bridge;
    }

    private void validateBridgeSize(List<String> bridge) {
        boolean inRange = 3 <= bridge.size() && bridge.size() <= 20;
        if (!inRange) {
            throw new IllegalArgumentException("[ERROR] 다리의 길이는 3부터 20 사이여야 합니다.");
        }
    }

    private void validateBridgeValue(List<String> bridge) {
        boolean notRightValue = bridge.stream()
                .anyMatch(s -> !(s.equals("U") || s.equals("D")));

        if (notRightValue) {
            throw new IllegalArgumentException("[ERROR] 다리의 값은 U 또는 D여야 합니다.");
        }
    }

    public boolean match(String moving, int round) {
        if (bridge.contains(moving)) {
            return bridge.get(round).equals(moving);
        }

        return false;
    }

    @Override
    public String toString() {
        return bridge.toString();
    }
}
