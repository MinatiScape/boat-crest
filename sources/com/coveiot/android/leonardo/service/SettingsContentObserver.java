package com.coveiot.android.leonardo.service;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SettingsContentObserver extends ContentObserver {
    @NotNull
    public static final Companion Companion;
    public static final String c;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5404a;
    public int b;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return SettingsContentObserver.c;
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        c = companion.getClass().getSimpleName();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsContentObserver(@NotNull Context context, @Nullable Handler handler) {
        super(handler);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5404a = context;
        Object systemService = context.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager audioManager = (AudioManager) systemService;
        Objects.requireNonNull(audioManager);
        this.b = audioManager.getStreamVolume(3);
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @NotNull
    public final Context getContext() {
        return this.f5404a;
    }

    public final int getPreviousVolume() {
        return this.b;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5404a = context;
    }

    public final void setPreviousVolume(int i) {
        this.b = i;
    }
}
