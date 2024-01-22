package com.coveiot.covepreferences.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class AutoSPO2SettingsData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static AutoSPO2SettingsData b;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7013a;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final AutoSPO2SettingsData getInstance() {
            if (AutoSPO2SettingsData.b == null) {
                AutoSPO2SettingsData.b = new AutoSPO2SettingsData(null);
            }
            return AutoSPO2SettingsData.b;
        }
    }

    public AutoSPO2SettingsData() {
    }

    public /* synthetic */ AutoSPO2SettingsData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final boolean isAutoSpO2() {
        return this.f7013a;
    }

    @NotNull
    public final AutoSPO2SettingsData setAutoSpO2(boolean z) {
        this.f7013a = z;
        return this;
    }
}
