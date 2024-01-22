package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class HIDValue {
    public static final int HOME = 5;
    @NotNull
    public static final HIDValue INSTANCE = new HIDValue();
    public static final int NEXT_TRACK = 0;
    public static final int PLAY_OR_PAUSE = 2;
    public static final int PREVIOUS_TRACK = 1;
    public static final int VOLUME_DEC = 4;
    public static final int VOLUME_INC = 3;

    private HIDValue() {
    }

    @NotNull
    public final String getValue(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "Unknown" : "HOME" : "VOLUME_DEC" : "VOLUME_INC" : "PLAY_OR_PAUSE" : "PREVIOUS_TRACK" : "NEXT_TRACK";
    }
}
