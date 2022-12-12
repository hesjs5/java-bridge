package bridge;

public class GameResult {

    private BridgeMap map;

    private boolean gameStatus;

    private int countOfTry;

    public GameResult() {
        this.map = new BridgeMap();
        this.gameStatus = true;
        this.countOfTry = 0;
    }

    public GameResult(BridgeMap map, boolean gameStatus, int countOfTry) {
        this.map = map;
        this.gameStatus = gameStatus;
        this.countOfTry = countOfTry;
    }

    public boolean isGameOver() {
        return !this.gameStatus;
    }

    public void gameOver() {
        this.gameStatus = false;
    }

    public void gameWin() {
        this.gameStatus = true;
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

    public BridgeMap getMap() {
        return map;
    }

    public int getCountOfTry() {
        return countOfTry;
    }
}
