package main.java;

public enum ErrorMessage {

    INVALID_DRAW_POSITION("사다리를 그릴 수 없는 위치입니다."),
    INVALID_LADDER_POSITION("유효하지 않은 위치입니다."),
    INVALID_GREATER_THAN_ONE("숫자는 1보다 커야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
