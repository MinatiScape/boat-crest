package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Spo2Response {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f3663a;
    @Nullable
    public Integer b;
    @Nullable
    public Float c;
    @Nullable
    public Boolean d;
    @Nullable
    public Integer e;
    @Nullable
    public Float f;
    @Nullable
    public Integer g;

    @Nullable
    public final Integer getMode() {
        return this.e;
    }

    @Nullable
    public final Float getPI() {
        return this.c;
    }

    @Nullable
    public final Integer getPR() {
        return this.b;
    }

    @Nullable
    public final Float getPower() {
        return this.f;
    }

    @Nullable
    public final Integer getPowerLevel() {
        return this.g;
    }

    @Nullable
    public final Integer getSpo2() {
        return this.f3663a;
    }

    @Nullable
    public final Boolean getStatus() {
        return this.d;
    }

    public final void setMode(@Nullable Integer num) {
        this.e = num;
    }

    public final void setPI(@Nullable Float f) {
        this.c = f;
    }

    public final void setPR(@Nullable Integer num) {
        this.b = num;
    }

    public final void setPower(@Nullable Float f) {
        this.f = f;
    }

    public final void setPowerLevel(@Nullable Integer num) {
        this.g = num;
    }

    public final void setSpo2(@Nullable Integer num) {
        this.f3663a = num;
    }

    public final void setStatus(@Nullable Boolean bool) {
        this.d = bool;
    }

    @NotNull
    public String toString() {
        return "Spo2Response(spo2=" + this.f3663a + ", PR=" + this.b + ", PI=" + this.c + ", status=" + this.d + ", mode=" + this.e + ", power=" + this.f + ", powerLevel=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }
}
