package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBatsman1;", "", "", "a", "Ljava/lang/Integer;", "getBatsmanId", "()Ljava/lang/Integer;", "setBatsmanId", "(Ljava/lang/Integer;)V", "batsmanId", "b", "getRuns", "setRuns", "runs", c.f10260a, "getBalls", "setBalls", "balls", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardBatsman1 {
    @SerializedName("batsman_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5954a;
    @SerializedName("runs")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("balls")
    @Expose
    @Nullable
    private Integer c;

    @Nullable
    public final Integer getBalls() {
        return this.c;
    }

    @Nullable
    public final Integer getBatsmanId() {
        return this.f5954a;
    }

    @Nullable
    public final Integer getRuns() {
        return this.b;
    }

    public final void setBalls(@Nullable Integer num) {
        this.c = num;
    }

    public final void setBatsmanId(@Nullable Integer num) {
        this.f5954a = num;
    }

    public final void setRuns(@Nullable Integer num) {
        this.b = num;
    }
}
