package bridge.util;

public class TypeConversion {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String SPACE = " ";
    private static final String INTEGER_ERROR_MESSAGE = "다리의 길이는 숫자를 입력해야 합니다.";

    public static int stringToInt(String str) {
        int result;
        try {
            result = Integer.parseInt(str);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE + SPACE + INTEGER_ERROR_MESSAGE);
        }
        return result;
    }
}
