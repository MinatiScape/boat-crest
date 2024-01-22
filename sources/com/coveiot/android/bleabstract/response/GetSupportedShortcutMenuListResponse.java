package com.coveiot.android.bleabstract.response;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetSupportedShortcutMenuListResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<Integer> f3626a;
    @NotNull
    public final List<Integer> b;
    public boolean c;

    public GetSupportedShortcutMenuListResponse(@NotNull List<Integer> supportedMenuList, @NotNull List<Integer> watchMenuList) {
        Intrinsics.checkNotNullParameter(supportedMenuList, "supportedMenuList");
        Intrinsics.checkNotNullParameter(watchMenuList, "watchMenuList");
        this.f3626a = supportedMenuList;
        this.b = watchMenuList;
    }

    @NotNull
    public final List<Integer> getSupportedMenuList() {
        return this.f3626a;
    }

    @NotNull
    public final List<Integer> getWatchMenuList() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }
}
