package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNonSmartAlertApplicationContentRequest extends BleBaseRequest {
    public final int f;
    public final int g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;

    public SetNonSmartAlertApplicationContentRequest(int i, int i2, @NotNull String title, @NotNull String content) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(content, "content");
        this.f = i;
        this.g = i2;
        this.h = title;
        this.i = content;
    }

    public final int getAppId() {
        return this.f;
    }

    @NotNull
    public final String getContent() {
        return this.i;
    }

    public final int getDisplayPosition() {
        return this.g;
    }

    @NotNull
    public final String getTitle() {
        return this.h;
    }
}
