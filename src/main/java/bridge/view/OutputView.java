package bridge.view;

import bridge.BridgeMap;
import bridge.BridgeMoveResult;
import bridge.GameResult;
import java.text.MessageFormat;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(BridgeMap bridgeMap) {
        StringBuilder upStringBuilder = new StringBuilder();
        StringBuilder downStringBuilder = new StringBuilder();

        appendPrefix(upStringBuilder, downStringBuilder);
        makeSentence(bridgeMap, upStringBuilder, downStringBuilder);
        appendSuffix(upStringBuilder, downStringBuilder);
        concatStringBuilder(upStringBuilder, downStringBuilder);

        System.out.println(upStringBuilder);
    }

    private void makeSentence(BridgeMap bridgeMap, StringBuilder upStringBuilder, StringBuilder downStringBuilder) {
        int index = 0;
        for (BridgeMoveResult bridgeMoveResult : bridgeMap) {
            appendMoveResult(upStringBuilder, downStringBuilder, bridgeMoveResult);
            if (bridgeMap.isAppendableDividingLine(index)) {
                upStringBuilder.append("|");
                downStringBuilder.append("|");
            }
            index++;
        }
    }

    private void appendPrefix(StringBuilder upStringBuilder, StringBuilder downStringBuilder) {
        upStringBuilder.append("[");
        downStringBuilder.append("[");
    }

    private void appendSpace(StringBuilder upStringBuilder, StringBuilder downStringBuilder) {
        upStringBuilder.append(" ");
        downStringBuilder.append(" ");
    }

    private void appendMoveResult(StringBuilder upStringBuilder, StringBuilder downStringBuilder,
                                  BridgeMoveResult bridgeMoveResult) {
        appendSpace(upStringBuilder, downStringBuilder);
        appendUpMoving(upStringBuilder, downStringBuilder, bridgeMoveResult);
        appendDownMoving(upStringBuilder, downStringBuilder, bridgeMoveResult);
        appendSpace(upStringBuilder, downStringBuilder);
    }

    private void appendUpMoving(StringBuilder upStringBuilder, StringBuilder downStringBuilder,
                                BridgeMoveResult bridgeMoveResult) {
        if (bridgeMoveResult.isUpMoving()) {
            upStringBuilder.append(bridgeMoveResult.getMoveResult());
            downStringBuilder.append(" ");
        }
    }

    private void appendDownMoving(StringBuilder upStringBuilder, StringBuilder downStringBuilder,
                                  BridgeMoveResult bridgeMoveResult) {
        if (bridgeMoveResult.isDownMoving()) {
            downStringBuilder.append(bridgeMoveResult.getMoveResult());
            upStringBuilder.append(" ");
        }
    }

    private void appendSuffix(StringBuilder upStringBuilder, StringBuilder downStringBuilder) {
        upStringBuilder.append("]");
        downStringBuilder.append("]");
    }

    private void concatStringBuilder(StringBuilder upStringBuilder, StringBuilder downStringBuilder) {
        upStringBuilder.append("\n");
        upStringBuilder.append(downStringBuilder);
        upStringBuilder.append("\n");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(GameResult gameResult) {
        System.out.println("최종 게임 결과");
        printMap(gameResult.getMap());

        System.out.println(MessageFormat.format("게임 성공 여부: {0}", gameResult.toGameStatus()));
        System.out.println(MessageFormat.format("총 시도한 횟수: {0}", gameResult.getCountOfTry()));
    }
}
