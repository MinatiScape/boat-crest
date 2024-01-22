package com.coveiot.android.bleabstract.response;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetGPSTime implements Serializable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3610a;

    @Nullable
    public final String getDate() {
        return this.f3610a;
    }

    public final void setDate(@Nullable String str) {
        this.f3610a = str;
    }
}
