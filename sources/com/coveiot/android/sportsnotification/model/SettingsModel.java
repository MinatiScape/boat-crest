package com.coveiot.android.sportsnotification.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SettingsModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f5875a;
    public boolean b;
    public int c;

    public SettingsModel(@NotNull String name, boolean z, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.f5875a = name;
        this.b = z;
        this.c = i;
    }

    @NotNull
    public final String getName() {
        return this.f5875a;
    }

    public final int getValue() {
        return this.c;
    }

    public final boolean isSelected() {
        return this.b;
    }

    public final void setSelected(boolean z) {
        this.b = z;
    }

    public final void setValue(int i) {
        this.c = i;
    }
}
