package bridge;

public class GameResult {

    private BridgeMap map;

    private boolean gameStatus;

    private int countOfTry;

    public GameResult() {
        this.map = new BridgeMap();
        this.gameStatus = true;
        this.countOfTry = 1;
    }

    public boolean isGameOver() {
        return !this.gameStatus;
    }

    public void gameOver() {
        this.gameStatus = false;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

    public String toGameStatus() {
        if (gameStatus) {
            return "성공";
        }

        return "실패";
    }

    public void addCountOfTry() {
        countOfTry++;
    }

    public void update(BridgeMap bridgeMap, boolean gameStatus) {
        this.map = bridgeMap;
        this.gameStatus = gameStatus;
    }

    public BridgeMap getMap() {
        return map;
    }

    public int getCountOfTry() {
        return countOfTry;
    }
}
