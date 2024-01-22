package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBowl;", "", "", "a", "Ljava/lang/Integer;", "getRunsConceded", "()Ljava/lang/Integer;", "setRunsConceded", "(Ljava/lang/Integer;)V", "runsConceded", "b", "getOvers", "setOvers", "overs", c.f10260a, "getWickets", "setWickets", "wickets", "d", "getMaidens", "setMaidens", "maidens", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getBowlerId", "setBowlerId", "bowlerId", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveBowl {
    @SerializedName("runs_conceded")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5924a;
    @SerializedName("overs")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("wickets")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("maidens")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("bowler_id")
    @Expose
    @Nullable
    private Integer e;

    @Nullable
    public final Integer getBowlerId() {
        return this.e;
    }

    @Nullable
    public final Integer getMaidens() {
        return this.d;
    }

    @Nullable
    public final Integer getOvers() {
        return this.b;
    }

    @Nullable
    public final Integer getRunsConceded() {
        return this.f5924a;
    }

    @Nullable
    public final Integer getWickets() {
        return this.c;
    }

    public final void setBowlerId(@Nullable Integer num) {
        this.e = num;
    }

    public final void setMaidens(@Nullable Integer num) {
        this.d = num;
    }

    public final void setOvers(@Nullable Integer num) {
        this.b = num;
    }

    public final void setRunsConceded(@Nullable Integer num) {
        this.f5924a = num;
    }

    public final void setWickets(@Nullable Integer num) {
        this.c = num;
    }
}
