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
        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println();

        int bridgeSize = inputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        Bridge bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));

        int countOfTry = 1;
        int round = 0;
        BridgeMap bridgeMap = new BridgeMap();
        GameResult gameResult = new GameResult();
        System.out.println(bridge);
        do {
            String moving = inputView.readMoving();
            String moveResult = bridgeGame.move(bridge, moving, round);
            BridgeMoveResult bridgeMoveResult = new BridgeMoveResult(moving, moveResult);
            bridgeMap.add(bridgeMoveResult);
            outputView.printMap(bridgeMap);

            if (bridgeMoveResult.canNotMove()) {
                String gameCommand = inputView.readGameCommand();
                if (gameCommand.equals("R")) {
                    bridgeGame.retry();
                    bridgeMap = new BridgeMap();
                    round = 0;
                    countOfTry++;
                }

                if (gameCommand.equals("Q")) {
                    gameResult.gameOver();
                }
            }

            if (bridgeMoveResult.canMove()) {
                round++;
            }

            gameResult = new GameResult(bridgeMap, gameResult.getGameStatus(), countOfTry);
        } while (round < bridgeSize && !gameResult.isGameOver());

        outputView.printResult(gameResult);
    }
}
