package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001e\u0010\u001fR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014¨\u0006 "}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardEquations;", "", "", "a", "Ljava/lang/Integer;", "getRuns", "()Ljava/lang/Integer;", "setRuns", "(Ljava/lang/Integer;)V", "runs", "b", "getWickets", "setWickets", "wickets", "", c.f10260a, "Ljava/lang/String;", "getOvers", "()Ljava/lang/String;", "setOvers", "(Ljava/lang/String;)V", "overs", "d", "getBowlersUsed", "setBowlersUsed", "bowlersUsed", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getRunrate", "setRunrate", "runrate", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardEquations {
    @SerializedName("runs")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5960a;
    @SerializedName("wickets")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("overs")
    @Expose
    @Nullable
    private String c;
    @SerializedName("bowlers_used")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("runrate")
    @Expose
    @Nullable
    private String e;

    @Nullable
    public final Integer getBowlersUsed() {
        return this.d;
    }

    @Nullable
    public final String getOvers() {
        return this.c;
    }

    @Nullable
    public final String getRunrate() {
        return this.e;
    }

    @Nullable
    public final Integer getRuns() {
        return this.f5960a;
    }

    @Nullable
    public final Integer getWickets() {
        return this.b;
    }

    public final void setBowlersUsed(@Nullable Integer num) {
        this.d = num;
    }

    public final void setOvers(@Nullable String str) {
        this.c = str;
    }

    public final void setRunrate(@Nullable String str) {
        this.e = str;
    }

    public final void setRuns(@Nullable Integer num) {
        this.f5960a = num;
    }

    public final void setWickets(@Nullable Integer num) {
        this.b = num;
    }
}
