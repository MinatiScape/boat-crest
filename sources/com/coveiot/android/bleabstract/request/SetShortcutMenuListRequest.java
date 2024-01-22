package com.coveiot.android.bleabstract.request;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetShortcutMenuListRequest extends BleBaseRequest {
    @NotNull
    public final List<Integer> f;

    public SetShortcutMenuListRequest(@NotNull List<Integer> menuList) {
        Intrinsics.checkNotNullParameter(menuList, "menuList");
        this.f = menuList;
    }

    @NotNull
    public final List<Integer> getMenuList() {
        return this.f;
    }
}
