package bridge;

public class GameCommand {

    private static final String RESTART = "R";
    private static final String QUIT = "Q";

    private final String firstLetter;

    public GameCommand(String firstLetter) {
        validate(firstLetter);
        this.firstLetter = firstLetter;
    }

    private void validate(String firstLetter) {
        boolean notGameCommand = !(firstLetter.equals(RESTART) || firstLetter.equals(QUIT));
        if (notGameCommand) {
            throw new IllegalArgumentException("[ERROR] R 또는 Q를 입력해야 합니다.");
        }
    }

    public boolean isRestart() {
        return this.firstLetter.equals(RESTART);
    }

    public boolean isQuit() {
        return this.firstLetter.equals(QUIT);
    }
}
