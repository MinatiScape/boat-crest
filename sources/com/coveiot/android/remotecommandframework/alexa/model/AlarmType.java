package com.coveiot.android.remotecommandframework.alexa.model;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum AlarmType {
    MEDICINE("TAKE_MEDICINE"),
    DRINK("DRINK_WATER"),
    FOOD("HAVE_FOOD"),
    VIBRATION_ALARM("VIBRATION_ALARM");
    
    @NotNull
    private final String type;

    AlarmType(String str) {
        this.type = str;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }
}
