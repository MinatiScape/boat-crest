package com.coveiot.android.theme.calendardaterangepicker.models;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class InvalidCalendarAttributeException extends IllegalArgumentException {
    @Nullable
    private final String message;

    public InvalidCalendarAttributeException(@Nullable String str) {
        super(str);
        this.message = str;
    }

    @Override // java.lang.Throwable
    @Nullable
    public String getMessage() {
        return this.message;
    }
}
