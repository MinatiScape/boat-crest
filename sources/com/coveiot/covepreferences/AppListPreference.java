package com.coveiot.covepreferences;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class AppListPreference {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f7000a = "SYSTEM_APP_LIST";
    @NotNull
    public static final String b = "AppListPreference";

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final Pair<ArrayList<String>, ArrayList<String>> getSystemAppList(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(AppListPreference.b, 0).getString(AppListPreference.f7000a, ""))) {
                return null;
            }
            try {
                return (Pair) new Gson().fromJson(context.getSharedPreferences(AppListPreference.b, 0).getString(AppListPreference.f7000a, ""), new TypeToken<Pair<? extends ArrayList<String>, ? extends ArrayList<String>>>() { // from class: com.coveiot.covepreferences.AppListPreference$Companion$getSystemAppList$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final void saveSystemAppsList(@NotNull Context context, @NotNull Pair<? extends ArrayList<String>, ? extends ArrayList<String>> pair) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pair, "pair");
            ArrayList<String> first = pair.getFirst();
            boolean z = true;
            if (first == null || first.isEmpty()) {
                return;
            }
            ArrayList<String> second = pair.getSecond();
            if (second != null && !second.isEmpty()) {
                z = false;
            }
            if (z) {
                return;
            }
            context.getSharedPreferences(AppListPreference.b, 0).edit().putString(AppListPreference.f7000a, new Gson().toJson(pair, new TypeToken<Pair<? extends ArrayList<String>, ? extends ArrayList<String>>>() { // from class: com.coveiot.covepreferences.AppListPreference$Companion$saveSystemAppsList$1
            }.getType())).apply();
        }
    }
}
