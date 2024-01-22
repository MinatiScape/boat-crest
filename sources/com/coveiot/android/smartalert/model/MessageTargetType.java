package com.coveiot.android.smartalert.model;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum MessageTargetType {
    NUDGE("NUDGE"),
    SMART_ALERT("SMART_ALERT");
    
    @NotNull
    private final String type;

    MessageTargetType(String str) {
        this.type = str;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }
}
