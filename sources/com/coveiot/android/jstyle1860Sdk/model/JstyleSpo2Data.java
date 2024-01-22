package com.coveiot.android.jstyle1860Sdk.model;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleSpo2Data;", "", "", "a", "Ljava/lang/Integer;", "getSpo2", "()Ljava/lang/Integer;", "setSpo2", "(Ljava/lang/Integer;)V", "spo2", "", "b", "Ljava/lang/Long;", "getDateTime", "()Ljava/lang/Long;", "setDateTime", "(Ljava/lang/Long;)V", "dateTime", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleSpo2Data {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f4612a;
    @Nullable
    public Long b;

    @Nullable
    public final Long getDateTime() {
        return this.b;
    }

    @Nullable
    public final Integer getSpo2() {
        return this.f4612a;
    }

    public final void setDateTime(@Nullable Long l) {
        this.b = l;
    }

    public final void setSpo2(@Nullable Integer num) {
        this.f4612a = num;
    }
}
