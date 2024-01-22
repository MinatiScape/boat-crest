package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b&\u0010'R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\b¨\u0006("}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBatsman;", "", "", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "b", "Ljava/lang/Integer;", "getBatsmanId", "()Ljava/lang/Integer;", "setBatsmanId", "(Ljava/lang/Integer;)V", "batsmanId", c.f10260a, "getRuns", "setRuns", "runs", "d", "getBallsFaced", "setBallsFaced", "ballsFaced", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getFours", "setFours", "fours", "f", "getSixes", "setSixes", "sixes", "g", "getStrikeRate", "setStrikeRate", "strikeRate", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveBatsman {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5922a;
    @SerializedName("batsman_id")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("runs")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("balls_faced")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("fours")
    @Expose
    @Nullable
    private Integer e;
    @SerializedName("sixes")
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("strike_rate")
    @Expose
    @Nullable
    private String g;

    @Nullable
    public final Integer getBallsFaced() {
        return this.d;
    }

    @Nullable
    public final Integer getBatsmanId() {
        return this.b;
    }

    @Nullable
    public final Integer getFours() {
        return this.e;
    }

    @Nullable
    public final String getName() {
        return this.f5922a;
    }

    @Nullable
    public final Integer getRuns() {
        return this.c;
    }

    @Nullable
    public final Integer getSixes() {
        return this.f;
    }

    @Nullable
    public final String getStrikeRate() {
        return this.g;
    }

    public final void setBallsFaced(@Nullable Integer num) {
        this.d = num;
    }

    public final void setBatsmanId(@Nullable Integer num) {
        this.b = num;
    }

    public final void setFours(@Nullable Integer num) {
        this.e = num;
    }

    public final void setName(@Nullable String str) {
        this.f5922a = str;
    }

    public final void setRuns(@Nullable Integer num) {
        this.c = num;
    }

    public final void setSixes(@Nullable Integer num) {
        this.f = num;
    }

    public final void setStrikeRate(@Nullable String str) {
        this.g = str;
    }
}
