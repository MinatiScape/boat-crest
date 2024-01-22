package com.coveiot.android.jstyle1860Sdk.model;

import com.google.android.material.color.c;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b&\u0010'R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010¨\u0006("}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleHrvData;", "", "", "a", "Ljava/lang/Long;", "getDateTime", "()Ljava/lang/Long;", "setDateTime", "(Ljava/lang/Long;)V", "dateTime", "", "b", "Ljava/lang/Integer;", "getSystolicbp", "()Ljava/lang/Integer;", "setSystolicbp", "(Ljava/lang/Integer;)V", "systolicbp", c.f10260a, "getDiastolicbp", "setDiastolicbp", "diastolicbp", "d", "getHr", "setHr", "hr", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getHrv", "setHrv", DeviceKey.HRV, "f", "getStress", "setStress", DeviceKey.Stress, "g", "getVascularAging", "setVascularAging", DeviceKey.VascularAging, "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleHrvData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f4609a;
    @Nullable
    public Integer b;
    @Nullable
    public Integer c;
    @Nullable
    public Integer d;
    @Nullable
    public Integer e;
    @Nullable
    public Integer f;
    @Nullable
    public Integer g;

    @Nullable
    public final Long getDateTime() {
        return this.f4609a;
    }

    @Nullable
    public final Integer getDiastolicbp() {
        return this.c;
    }

    @Nullable
    public final Integer getHr() {
        return this.d;
    }

    @Nullable
    public final Integer getHrv() {
        return this.e;
    }

    @Nullable
    public final Integer getStress() {
        return this.f;
    }

    @Nullable
    public final Integer getSystolicbp() {
        return this.b;
    }

    @Nullable
    public final Integer getVascularAging() {
        return this.g;
    }

    public final void setDateTime(@Nullable Long l) {
        this.f4609a = l;
    }

    public final void setDiastolicbp(@Nullable Integer num) {
        this.c = num;
    }

    public final void setHr(@Nullable Integer num) {
        this.d = num;
    }

    public final void setHrv(@Nullable Integer num) {
        this.e = num;
    }

    public final void setStress(@Nullable Integer num) {
        this.f = num;
    }

    public final void setSystolicbp(@Nullable Integer num) {
        this.b = num;
    }

    public final void setVascularAging(@Nullable Integer num) {
        this.g = num;
    }
}
