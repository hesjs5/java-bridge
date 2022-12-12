package bridge.controller;

import bridge.Bridge;
import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeMap;
import bridge.BridgeMoveResult;
import bridge.BridgeRandomNumberGenerator;
import bridge.GameResult;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeController {

    private final BridgeGame bridgeGame;

    private final InputView inputView;

    private final OutputView outputView;

    public BridgeController(BridgeGame bridgeGame, InputView inputView, OutputView outputView) {
        this.bridgeGame = bridgeGame;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        System.out.println("다리 건너기 게임을 시작합니다.\n");

        int bridgeSize = inputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        Bridge bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));

        GameResult gameResult = play(bridgeSize, bridge);

        outputView.printResult(gameResult);
    }

    private BridgeMoveResult getBridgeMoveResult(Bridge bridge, BridgeMap bridgeMap) {
        String moving = inputView.readMoving();
        String moveResult = bridgeGame.move(bridge, moving);
        BridgeMoveResult bridgeMoveResult = new BridgeMoveResult(moving, moveResult);
        bridgeMap.add(bridgeMoveResult);
        outputView.printMap(bridgeMap);
        return bridgeMoveResult;
    }

    private void checkCanNotMove(BridgeMap bridgeMap, GameResult gameResult, BridgeMoveResult bridgeMoveResult) {
        if (bridgeMoveResult.canNotMove()) {
            String gameCommand = inputView.readGameCommand();
            if (gameCommand.equals("R")) {
                bridgeGame.retry(bridgeMap, gameResult);
            }
            if (gameCommand.equals("Q")) {
                gameResult.gameOver();
            }
        }
    }

    private GameResult play(int bridgeSize, Bridge bridge) {
        BridgeMap bridgeMap = new BridgeMap();
        GameResult gameResult = new GameResult();
        do {
            BridgeMoveResult bridgeMoveResult = getBridgeMoveResult(bridge, bridgeMap);
            checkCanNotMove(bridgeMap, gameResult, bridgeMoveResult);
            gameResult.update(bridgeMap, gameResult.getGameStatus());
        } while (bridgeGame.isRoundLessThan(bridgeSize) && !gameResult.isGameOver());

        return gameResult;
    }
}
