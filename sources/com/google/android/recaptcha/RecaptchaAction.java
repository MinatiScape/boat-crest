package com.google.android.recaptcha;

import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class RecaptchaAction {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final RecaptchaAction LOGIN = new RecaptchaAction(FirebaseAnalytics.Event.LOGIN);
    @JvmField
    @NotNull
    public static final RecaptchaAction SIGNUP = new RecaptchaAction("signup");
    @NotNull
    private final String action;

    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(@NonNull DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final RecaptchaAction custom(@NonNull String str) {
            return new RecaptchaAction(str, null);
        }
    }

    private RecaptchaAction(String str) {
        this.action = str;
    }

    public /* synthetic */ RecaptchaAction(@NonNull String str, @NonNull DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NonNull
    public static /* synthetic */ RecaptchaAction copy$default(@NonNull RecaptchaAction recaptchaAction, @NonNull String str, int i, @NonNull Object obj) {
        if ((i & 1) != 0) {
            str = recaptchaAction.action;
        }
        return recaptchaAction.copy(str);
    }

    @JvmStatic
    @NotNull
    public static final RecaptchaAction custom(@NonNull String str) {
        return Companion.custom(str);
    }

    @NotNull
    public final String component1() {
        return this.action;
    }

    @NotNull
    public final RecaptchaAction copy(@NonNull String str) {
        return new RecaptchaAction(str);
    }

    public boolean equals(@NonNull @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RecaptchaAction) && Intrinsics.areEqual(this.action, ((RecaptchaAction) obj).action);
    }

    @NotNull
    public final String getAction() {
        return this.action;
    }

    public int hashCode() {
        return this.action.hashCode();
    }

    @NotNull
    public String toString() {
        String str = this.action;
        return "RecaptchaAction(action=" + str + ")";
    }
}
