package com.coveiot.android.sportsnotificationsdk.models.livematch;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b&\u0010'R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveScore;", "", "", "a", "Ljava/lang/Integer;", "getRuns", "()Ljava/lang/Integer;", "setRuns", "(Ljava/lang/Integer;)V", "runs", "", "b", "Ljava/lang/Double;", "getOvers", "()Ljava/lang/Double;", "setOvers", "(Ljava/lang/Double;)V", "overs", c.f10260a, "getWickets", "setWickets", "wickets", "d", "getTarget", "setTarget", TypedValues.AttributesType.S_TARGET, RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getRunrate", "setRunrate", "runrate", "", "f", "Ljava/lang/String;", "getRequiredRunrate", "()Ljava/lang/String;", "setRequiredRunrate", "(Ljava/lang/String;)V", "requiredRunrate", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveScore {
    @SerializedName("runs")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5933a;
    @SerializedName("overs")
    @Expose
    @Nullable
    private Double b;
    @SerializedName("wickets")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("runrate")
    @Expose
    @Nullable
    private Double e;
    @SerializedName("required_runrate")
    @Expose
    @Nullable
    private String f;

    @Nullable
    public final Double getOvers() {
        return this.b;
    }

    @Nullable
    public final String getRequiredRunrate() {
        return this.f;
    }

    @Nullable
    public final Double getRunrate() {
        return this.e;
    }

    @Nullable
    public final Integer getRuns() {
        return this.f5933a;
    }

    @Nullable
    public final Integer getTarget() {
        return this.d;
    }

    @Nullable
    public final Integer getWickets() {
        return this.c;
    }

    public final void setOvers(@Nullable Double d) {
        this.b = d;
    }

    public final void setRequiredRunrate(@Nullable String str) {
        this.f = str;
    }

    public final void setRunrate(@Nullable Double d) {
        this.e = d;
    }

    public final void setRuns(@Nullable Integer num) {
        this.f5933a = num;
    }

    public final void setTarget(@Nullable Integer num) {
        this.d = num;
    }

    public final void setWickets(@Nullable Integer num) {
        this.c = num;
    }
}
