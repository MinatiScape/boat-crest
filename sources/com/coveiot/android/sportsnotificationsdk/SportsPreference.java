package com.coveiot.android.sportsnotificationsdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/SportsPreference;", "", "Landroid/content/SharedPreferences;", "a", "Landroid/content/SharedPreferences;", "getPref", "()Landroid/content/SharedPreferences;", "setPref", "(Landroid/content/SharedPreferences;)V", "pref", "Landroid/content/SharedPreferences$Editor;", "b", "Landroid/content/SharedPreferences$Editor;", "getEditor", "()Landroid/content/SharedPreferences$Editor;", "setEditor", "(Landroid/content/SharedPreferences$Editor;)V", "editor", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class SportsPreference {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static SportsPreference e;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f5917a;
    @Nullable
    public SharedPreferences.Editor b;
    @NotNull
    public Context c;
    @NotNull
    public final String d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/SportsPreference$Companion;", "", "Landroid/content/Context;", "context", "Lcom/coveiot/android/sportsnotificationsdk/SportsPreference;", "getInstance", "INSTANCE", "Lcom/coveiot/android/sportsnotificationsdk/SportsPreference;", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SportsPreference getInstance(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (SportsPreference.e == null) {
                SportsPreference.e = new SportsPreference(context, null);
            }
            SportsPreference sportsPreference = SportsPreference.e;
            Objects.requireNonNull(sportsPreference, "null cannot be cast to non-null type com.coveiot.android.sportsnotificationsdk.SportsPreference");
            return sportsPreference;
        }
    }

    public SportsPreference(Context context) {
        this.d = "SportsPreference";
        this.c = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SportsPreference", 0);
        this.f5917a = sharedPreferences;
        this.b = sharedPreferences == null ? null : sharedPreferences.edit();
    }

    public /* synthetic */ SportsPreference(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final SharedPreferences.Editor getEditor() {
        return this.b;
    }

    @Nullable
    public final SharedPreferences getPref() {
        return this.f5917a;
    }

    public final void setEditor(@Nullable SharedPreferences.Editor editor) {
        this.b = editor;
    }

    public final void setPref(@Nullable SharedPreferences sharedPreferences) {
        this.f5917a = sharedPreferences;
    }
}
