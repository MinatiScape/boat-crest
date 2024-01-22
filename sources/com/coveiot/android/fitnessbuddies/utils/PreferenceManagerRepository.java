package com.coveiot.android.fitnessbuddies.utils;

import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class PreferenceManagerRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String b = "repository_preference";
    @NotNull
    public static final String c = "LAST_MEASURED_STEPS_INFO";
    @NotNull
    public static final String d = "LAST_MEASURED_SLEEP_INFO";
    @NotNull
    public static final String e = "LAST_MEASURED_HEARTRATE_INFO";
    @NotNull
    public static final String f = "LAST_MEASURED_TEMPERATURE_INFO";
    @NotNull
    public static final String g = "LAST_MEASURED_SPO2_PERIOIDC_INFO";
    @NotNull
    public static final String h = "LAST_MEASURED_STRESS_PERIOIDC_INFO";
    @NotNull
    public static final String i = "LAST_MEASURED_HRV_PERIOIDC_INFO";
    @NotNull
    public static final String j = "LAST_MEASURED_ENERGYMETER_INFO";
    @NotNull
    public static final String k = "LAST_MEASURED_SLEEPSCORE_INFO";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4474a;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getLAST_MEASURED_ENERGYMETER_INFO() {
            return PreferenceManagerRepository.j;
        }

        @NotNull
        public final String getLAST_MEASURED_HEARTRATE_INFO() {
            return PreferenceManagerRepository.e;
        }

        @NotNull
        public final String getLAST_MEASURED_HRV_PERIOIDC_INFO() {
            return PreferenceManagerRepository.i;
        }

        @NotNull
        public final String getLAST_MEASURED_SLEEPSCORE_INFO() {
            return PreferenceManagerRepository.k;
        }

        @NotNull
        public final String getLAST_MEASURED_SLEEP_INFO() {
            return PreferenceManagerRepository.d;
        }

        @NotNull
        public final String getLAST_MEASURED_SPO2_PERIOIDC_INFO() {
            return PreferenceManagerRepository.g;
        }

        @NotNull
        public final String getLAST_MEASURED_STEPS_INFO() {
            return PreferenceManagerRepository.c;
        }

        @NotNull
        public final String getLAST_MEASURED_STRESS_PERIOIDC_INFO() {
            return PreferenceManagerRepository.h;
        }

        @NotNull
        public final String getLAST_MEASURED_TEMPERATURE_INFO() {
            return PreferenceManagerRepository.f;
        }

        @Nullable
        public final String getLastMeasuredInfo(@NotNull Context context, @NotNull String vitalKey) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vitalKey, "vitalKey");
            return context.getSharedPreferences(PreferenceManagerRepository.b, 0).getString(vitalKey, null);
        }

        public final void saveLastMeasuredInfo(@NotNull Context context, @NotNull String vitalKey, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vitalKey, "vitalKey");
            context.getSharedPreferences(PreferenceManagerRepository.b, 0).edit().putString(vitalKey, str).commit();
        }
    }

    public PreferenceManagerRepository(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4474a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4474a;
    }
}
