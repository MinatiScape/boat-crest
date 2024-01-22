package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\"\u0010#R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\b¨\u0006$"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveExtraRuns;", "", "", "a", "Ljava/lang/Integer;", "getByes", "()Ljava/lang/Integer;", "setByes", "(Ljava/lang/Integer;)V", "byes", "b", "getLegbyes", "setLegbyes", "legbyes", c.f10260a, "getWides", "setWides", "wides", "d", "getNoballs", "setNoballs", "noballs", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/String;", "getPenalty", "()Ljava/lang/String;", "setPenalty", "(Ljava/lang/String;)V", "penalty", "f", "getTotal", "setTotal", "total", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveExtraRuns {
    @SerializedName("byes")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5929a;
    @SerializedName("legbyes")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("wides")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("noballs")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("penalty")
    @Expose
    @Nullable
    private String e;
    @SerializedName("total")
    @Expose
    @Nullable
    private Integer f;

    @Nullable
    public final Integer getByes() {
        return this.f5929a;
    }

    @Nullable
    public final Integer getLegbyes() {
        return this.b;
    }

    @Nullable
    public final Integer getNoballs() {
        return this.d;
    }

    @Nullable
    public final String getPenalty() {
        return this.e;
    }

    @Nullable
    public final Integer getTotal() {
        return this.f;
    }

    @Nullable
    public final Integer getWides() {
        return this.c;
    }

    public final void setByes(@Nullable Integer num) {
        this.f5929a = num;
    }

    public final void setLegbyes(@Nullable Integer num) {
        this.b = num;
    }

    public final void setNoballs(@Nullable Integer num) {
        this.d = num;
    }

    public final void setPenalty(@Nullable String str) {
        this.e = str;
    }

    public final void setTotal(@Nullable Integer num) {
        this.f = num;
    }

    public final void setWides(@Nullable Integer num) {
        this.c = num;
    }
}
