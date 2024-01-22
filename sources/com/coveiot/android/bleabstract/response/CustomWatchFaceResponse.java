package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CustomWatchFaceResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3591a;
    public boolean b;

    @Nullable
    public final String getResponse() {
        return this.f3591a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setResponse(@Nullable String str) {
        this.f3591a = str;
    }
}
