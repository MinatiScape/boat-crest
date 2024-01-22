package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001f\u0010 R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R*\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardCurrentPartnership;", "", "", "a", "Ljava/lang/Integer;", "getRuns", "()Ljava/lang/Integer;", "setRuns", "(Ljava/lang/Integer;)V", "runs", "b", "getBalls", "setBalls", "balls", "", c.f10260a, "Ljava/lang/Double;", "getOvers", "()Ljava/lang/Double;", "setOvers", "(Ljava/lang/Double;)V", "overs", "", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBatsman1;", "d", "Ljava/util/List;", "getBatsmen", "()Ljava/util/List;", "setBatsmen", "(Ljava/util/List;)V", "batsmen", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardCurrentPartnership {
    @SerializedName("runs")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5958a;
    @SerializedName("balls")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("overs")
    @Expose
    @Nullable
    private Double c;
    @SerializedName("batsmen")
    @Expose
    @Nullable
    private List<ScorecardBatsman1> d;

    @Nullable
    public final Integer getBalls() {
        return this.b;
    }

    @Nullable
    public final List<ScorecardBatsman1> getBatsmen() {
        return this.d;
    }

    @Nullable
    public final Double getOvers() {
        return this.c;
    }

    @Nullable
    public final Integer getRuns() {
        return this.f5958a;
    }

    public final void setBalls(@Nullable Integer num) {
        this.b = num;
    }

    public final void setBatsmen(@Nullable List<ScorecardBatsman1> list) {
        this.d = list;
    }

    public final void setOvers(@Nullable Double d) {
        this.c = d;
    }

    public final void setRuns(@Nullable Integer num) {
        this.f5958a = num;
    }
}
