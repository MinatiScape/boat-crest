package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b&\u0010'R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010¨\u0006("}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBowler;", "", "", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "b", "Ljava/lang/Integer;", "getBowlerId", "()Ljava/lang/Integer;", "setBowlerId", "(Ljava/lang/Integer;)V", "bowlerId", c.f10260a, "getOvers", "setOvers", "overs", "d", "getRunsConceded", "setRunsConceded", "runsConceded", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getWickets", "setWickets", "wickets", "f", "getMaidens", "setMaidens", "maidens", "g", "getEcon", "setEcon", "econ", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveBowler {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5925a;
    @SerializedName("bowler_id")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("overs")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("runs_conceded")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("wickets")
    @Expose
    @Nullable
    private Integer e;
    @SerializedName("maidens")
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("econ")
    @Expose
    @Nullable
    private Integer g;

    @Nullable
    public final Integer getBowlerId() {
        return this.b;
    }

    @Nullable
    public final Integer getEcon() {
        return this.g;
    }

    @Nullable
    public final Integer getMaidens() {
        return this.f;
    }

    @Nullable
    public final String getName() {
        return this.f5925a;
    }

    @Nullable
    public final Integer getOvers() {
        return this.c;
    }

    @Nullable
    public final Integer getRunsConceded() {
        return this.d;
    }

    @Nullable
    public final Integer getWickets() {
        return this.e;
    }

    public final void setBowlerId(@Nullable Integer num) {
        this.b = num;
    }

    public final void setEcon(@Nullable Integer num) {
        this.g = num;
    }

    public final void setMaidens(@Nullable Integer num) {
        this.f = num;
    }

    public final void setName(@Nullable String str) {
        this.f5925a = str;
    }

    public final void setOvers(@Nullable Integer num) {
        this.c = num;
    }

    public final void setRunsConceded(@Nullable Integer num) {
        this.d = num;
    }

    public final void setWickets(@Nullable Integer num) {
        this.e = num;
    }
}
