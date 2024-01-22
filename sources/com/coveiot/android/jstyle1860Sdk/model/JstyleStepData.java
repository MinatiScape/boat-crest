package com.coveiot.android.jstyle1860Sdk.model;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001e\u0010\u001fR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\b¨\u0006 "}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleStepData;", "", "", "a", "Ljava/lang/String;", "getCal", "()Ljava/lang/String;", "setCal", "(Ljava/lang/String;)V", "cal", "b", "getDate", "setDate", "date", c.f10260a, "getTime", "setTime", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "d", "getDistance", "setDistance", "distance", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getTotalStep", "setTotalStep", "totalStep", "f", "getDetailStep", "setDetailStep", "detailStep", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleStepData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4613a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public String e;
    @Nullable
    public String f;

    @Nullable
    public final String getCal() {
        return this.f4613a;
    }

    @Nullable
    public final String getDate() {
        return this.b;
    }

    @Nullable
    public final String getDetailStep() {
        return this.f;
    }

    @Nullable
    public final String getDistance() {
        return this.d;
    }

    @Nullable
    public final String getTime() {
        return this.c;
    }

    @Nullable
    public final String getTotalStep() {
        return this.e;
    }

    public final void setCal(@Nullable String str) {
        this.f4613a = str;
    }

    public final void setDate(@Nullable String str) {
        this.b = str;
    }

    public final void setDetailStep(@Nullable String str) {
        this.f = str;
    }

    public final void setDistance(@Nullable String str) {
        this.d = str;
    }

    public final void setTime(@Nullable String str) {
        this.c = str;
    }

    public final void setTotalStep(@Nullable String str) {
        this.e = str;
    }
}
