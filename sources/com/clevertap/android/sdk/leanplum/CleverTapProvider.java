package com.clevertap.android.sdk.leanplum;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CleverTapProvider {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public CleverTapAPI f2641a;
    @Nullable
    public CleverTapAPI b;

    public CleverTapProvider(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.b = CleverTapAPI.getDefaultInstance(context);
    }

    @Nullable
    public final CleverTapAPI getCleverTap() {
        CleverTapAPI cleverTapAPI = this.f2641a;
        if (cleverTapAPI != null) {
            return cleverTapAPI;
        }
        CleverTapAPI cleverTapAPI2 = this.b;
        if (cleverTapAPI2 != null) {
            return cleverTapAPI2;
        }
        Logger.i("CTWrapper", "Please initialize LeanplumCT, because CleverTap instance is missing.");
        return null;
    }

    public CleverTapProvider(@NotNull CleverTapAPI customInstance) {
        Intrinsics.checkNotNullParameter(customInstance, "customInstance");
        this.f2641a = customInstance;
    }
}
