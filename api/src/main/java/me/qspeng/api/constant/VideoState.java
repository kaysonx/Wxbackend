package me.qspeng.api.constant;

public enum VideoState {
    SUCCESS(1),
    FORBIDDEN(2);

    public final int value;

    VideoState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
