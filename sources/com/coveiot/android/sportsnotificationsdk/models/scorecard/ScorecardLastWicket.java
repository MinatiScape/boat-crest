package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b.\u0010/R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR$\u0010-\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0018\u001a\u0004\b+\u0010\u001a\"\u0004\b,\u0010\u001c¨\u00060"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLastWicket;", "", "", "a", "Ljava/lang/String;", "getBatsmanId", "()Ljava/lang/String;", "setBatsmanId", "(Ljava/lang/String;)V", "batsmanId", "b", "getRuns", "setRuns", "runs", c.f10260a, "getBalls", "setBalls", "balls", "d", "getHowOut", "setHowOut", "howOut", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/Integer;", "getScoreAtDismissal", "()Ljava/lang/Integer;", "setScoreAtDismissal", "(Ljava/lang/Integer;)V", "scoreAtDismissal", "f", "getOversAtDismissal", "setOversAtDismissal", "oversAtDismissal", "g", "getBowlerId", "setBowlerId", "bowlerId", "h", "getDismissal", "setDismissal", "dismissal", "i", "getNumber", "setNumber", CTVariableUtils.NUMBER, "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardLastWicket {
    @SerializedName("batsman_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5966a;
    @SerializedName("runs")
    @Expose
    @Nullable
    private String b;
    @SerializedName("balls")
    @Expose
    @Nullable
    private String c;
    @SerializedName("how_out")
    @Expose
    @Nullable
    private String d;
    @SerializedName("score_at_dismissal")
    @Expose
    @Nullable
    private Integer e;
    @SerializedName("overs_at_dismissal")
    @Expose
    @Nullable
    private String f;
    @SerializedName("bowler_id")
    @Expose
    @Nullable
    private String g;
    @SerializedName("dismissal")
    @Expose
    @Nullable
    private String h;
    @SerializedName(CTVariableUtils.NUMBER)
    @Expose
    @Nullable
    private Integer i;

    @Nullable
    public final String getBalls() {
        return this.c;
    }

    @Nullable
    public final String getBatsmanId() {
        return this.f5966a;
    }

    @Nullable
    public final String getBowlerId() {
        return this.g;
    }

    @Nullable
    public final String getDismissal() {
        return this.h;
    }

    @Nullable
    public final String getHowOut() {
        return this.d;
    }

    @Nullable
    public final Integer getNumber() {
        return this.i;
    }

    @Nullable
    public final String getOversAtDismissal() {
        return this.f;
    }

    @Nullable
    public final String getRuns() {
        return this.b;
    }

    @Nullable
    public final Integer getScoreAtDismissal() {
        return this.e;
    }

    public final void setBalls(@Nullable String str) {
        this.c = str;
    }

    public final void setBatsmanId(@Nullable String str) {
        this.f5966a = str;
    }

    public final void setBowlerId(@Nullable String str) {
        this.g = str;
    }

    public final void setDismissal(@Nullable String str) {
        this.h = str;
    }

    public final void setHowOut(@Nullable String str) {
        this.d = str;
    }

    public final void setNumber(@Nullable Integer num) {
        this.i = num;
    }

    public final void setOversAtDismissal(@Nullable String str) {
        this.f = str;
    }

    public final void setRuns(@Nullable String str) {
        this.b = str;
    }

    public final void setScoreAtDismissal(@Nullable Integer num) {
        this.e = num;
    }
}
