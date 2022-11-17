package bridge;

import bridge.constant.BridgeMove;
import bridge.constant.GameCommand;
import bridge.util.TypeConversion;
import bridge.util.Validator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        int conversionInput = Integer.MIN_VALUE;
        do {
            System.out.println("다리의 길이를 입력해주세요.");
            String input = Console.readLine();
            conversionInput = getConversionInput(conversionInput, input);
        } while (Validator.isNumberNotInBridgeRange(conversionInput));

        return conversionInput;
    }

    private int getConversionInput(int conversionInput, String input) {
        try {
            conversionInput = TypeConversion.stringToInt(input);
            Validator.validateNumberInBridgeRange(conversionInput);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }

        return conversionInput;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        BridgeMove bridgeMove;
        do {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String input = Console.readLine();
            bridgeMove = getBridgeMoveByString(input);
        } while (bridgeMove.isMiss());

        return bridgeMove.getFirstLetter();
    }

    private BridgeMove getBridgeMoveByString(String input) {
        try {
            Validator.validateMovableInput(input);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }

        return BridgeMove.findByInput(input);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        GameCommand gameCommand;
        do {
            System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
            String input = Console.readLine();
            gameCommand = getGameCommand(input);
        } while (gameCommand.isMiss());

        return gameCommand.getFirstLetter();
    }

    private GameCommand getGameCommand(String input) {
        try {
            Validator.validateIfStringIsGameCommand(input);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }

        return GameCommand.findByString(input);
    }
}
