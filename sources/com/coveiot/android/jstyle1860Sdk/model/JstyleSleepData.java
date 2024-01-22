package com.coveiot.android.jstyle1860Sdk.model;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleSleepData;", "", "", "a", "Ljava/lang/String;", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "date", "b", "getTime", "setTime", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, c.f10260a, "getDetailSleep", "setDetailSleep", "detailSleep", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleSleepData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4611a;
    @Nullable
    public String b;
    @Nullable
    public String c;

    @Nullable
    public final String getDate() {
        return this.f4611a;
    }

    @Nullable
    public final String getDetailSleep() {
        return this.c;
    }

    @Nullable
    public final String getTime() {
        return this.b;
    }

    public final void setDate(@Nullable String str) {
        this.f4611a = str;
    }

    public final void setDetailSleep(@Nullable String str) {
        this.c = str;
    }

    public final void setTime(@Nullable String str) {
        this.b = str;
    }
}
