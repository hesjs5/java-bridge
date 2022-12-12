package bridge;

import java.util.Objects;

public class BridgeMoveResult {

    private final String moving;

    private final String moveResult;

    public BridgeMoveResult(String moving, String moveResult) {
        this.moving = moving;
        this.moveResult = moveResult;
    }

    public boolean isDownMoving() {
        return this.moving.equals("D");
    }

    public boolean isUpMoving() {
        return this.moving.equals("U");
    }

    public String getMoveResult() {
        return moveResult;
    }

    public boolean canNotMove() {
        return this.moveResult.equals("X");
    }

    public boolean canMove() {
        return this.moveResult.equals("O");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BridgeMoveResult that = (BridgeMoveResult) o;
        return Objects.equals(moving, that.moving) && Objects.equals(moveResult, that.moveResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moving, moveResult);
    }
}
