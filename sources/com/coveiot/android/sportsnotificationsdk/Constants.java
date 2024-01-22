package com.coveiot.android.sportsnotificationsdk;

import com.coveiot.coveaccess.constants.ErrorConstants;
import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\"\u0010\u0011\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR\"\u0010\u0015\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\"\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\b¨\u0006 "}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/Constants;", "", "", "a", "Ljava/lang/String;", "getGENERIC_ERROR", "()Ljava/lang/String;", "setGENERIC_ERROR", "(Ljava/lang/String;)V", "GENERIC_ERROR", "b", "getSETUP_ERR_GENERIC", "setSETUP_ERR_GENERIC", "SETUP_ERR_GENERIC", c.f10260a, "getSETUP_ERR_NOT_INIT", "setSETUP_ERR_NOT_INIT", "SETUP_ERR_NOT_INIT", "d", "getSETUP_ERR_SETUP_NOT_COMPLETE", "setSETUP_ERR_SETUP_NOT_COMPLETE", "SETUP_ERR_SETUP_NOT_COMPLETE", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getSETUP_ERR_MISSING_DATA", "setSETUP_ERR_MISSING_DATA", "SETUP_ERR_MISSING_DATA", "f", "getAPI_ERR_GENERIC", "setAPI_ERR_GENERIC", "API_ERR_GENERIC", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class Constants {
    @NotNull
    public static final Constants INSTANCE = new Constants();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static String f5906a = ErrorConstants.GENERIC_ERROR;
    @NotNull
    public static String b = ErrorConstants.SETUP_ERR_GENERIC;
    @NotNull
    public static String c = ErrorConstants.SETUP_ERR_NOT_INIT;
    @NotNull
    public static String d = ErrorConstants.SETUP_ERR_SETUP_NOT_COMPLETE;
    @NotNull
    public static String e = ErrorConstants.SETUP_ERR_MISSING_DATA;
    @NotNull
    public static String f = ErrorConstants.API_ERR_GENERIC;

    @NotNull
    public final String getAPI_ERR_GENERIC() {
        return f;
    }

    @NotNull
    public final String getGENERIC_ERROR() {
        return f5906a;
    }

    @NotNull
    public final String getSETUP_ERR_GENERIC() {
        return b;
    }

    @NotNull
    public final String getSETUP_ERR_MISSING_DATA() {
        return e;
    }

    @NotNull
    public final String getSETUP_ERR_NOT_INIT() {
        return c;
    }

    @NotNull
    public final String getSETUP_ERR_SETUP_NOT_COMPLETE() {
        return d;
    }

    public final void setAPI_ERR_GENERIC(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        f = str;
    }

    public final void setGENERIC_ERROR(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        f5906a = str;
    }

    public final void setSETUP_ERR_GENERIC(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        b = str;
    }

    public final void setSETUP_ERR_MISSING_DATA(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        e = str;
    }

    public final void setSETUP_ERR_NOT_INIT(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        c = str;
    }

    public final void setSETUP_ERR_SETUP_NOT_COMPLETE(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        d = str;
    }
}
