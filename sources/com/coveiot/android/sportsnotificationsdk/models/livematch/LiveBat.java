package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBat;", "", "", "a", "Ljava/lang/Integer;", "getRuns", "()Ljava/lang/Integer;", "setRuns", "(Ljava/lang/Integer;)V", "runs", "b", "getBallsFaced", "setBallsFaced", "ballsFaced", c.f10260a, "getFours", "setFours", "fours", "d", "getSixes", "setSixes", "sixes", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getBatsmanId", "setBatsmanId", "batsmanId", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveBat {
    @SerializedName("runs")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5921a;
    @SerializedName("balls_faced")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("fours")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("sixes")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("batsman_id")
    @Expose
    @Nullable
    private Integer e;

    @Nullable
    public final Integer getBallsFaced() {
        return this.b;
    }

    @Nullable
    public final Integer getBatsmanId() {
        return this.e;
    }

    @Nullable
    public final Integer getFours() {
        return this.c;
    }

    @Nullable
    public final Integer getRuns() {
        return this.f5921a;
    }

    @Nullable
    public final Integer getSixes() {
        return this.d;
    }

    public final void setBallsFaced(@Nullable Integer num) {
        this.b = num;
    }

    public final void setBatsmanId(@Nullable Integer num) {
        this.e = num;
    }

    public final void setFours(@Nullable Integer num) {
        this.c = num;
    }

    public final void setRuns(@Nullable Integer num) {
        this.f5921a = num;
    }

    public final void setSixes(@Nullable Integer num) {
        this.d = num;
    }
}
