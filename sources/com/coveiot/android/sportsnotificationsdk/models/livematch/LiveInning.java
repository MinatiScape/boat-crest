package com.coveiot.android.sportsnotificationsdk.models.livematch;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bb\u0010cR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0019\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR$\u0010-\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0010\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014R$\u00101\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0010\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014R*\u00109\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u0010A\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010I\u001a\u0004\u0018\u00010B8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010Q\u001a\u0004\u0018\u00010J8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR*\u0010U\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u00104\u001a\u0004\bS\u00106\"\u0004\bT\u00108R$\u0010Y\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u0010\u0010\u001a\u0004\bW\u0010\u0012\"\u0004\bX\u0010\u0014R$\u0010]\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bZ\u0010\u0010\u001a\u0004\b[\u0010\u0012\"\u0004\b\\\u0010\u0014R$\u0010a\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b^\u0010\u0010\u001a\u0004\b_\u0010\u0012\"\u0004\b`\u0010\u0014¨\u0006d"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveInning;", "", "", "a", "Ljava/lang/Integer;", "getIid", "()Ljava/lang/Integer;", "setIid", "(Ljava/lang/Integer;)V", "iid", "b", "getNumber", "setNumber", CTVariableUtils.NUMBER, "", c.f10260a, "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, "d", "getShortName", "setShortName", "shortName", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "f", "getResult", "setResult", "result", "g", "getBattingTeamId", "setBattingTeamId", "battingTeamId", "h", "getFieldingTeamId", "setFieldingTeamId", "fieldingTeamId", "i", "getScores", "setScores", "scores", "j", "getScoresFull", "setScoresFull", "scoresFull", "", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Ljava/util/List;", "getFielder", "()Ljava/util/List;", "setFielder", "(Ljava/util/List;)V", "fielder", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveExtraRuns;", "l", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveExtraRuns;", "getExtraRuns", "()Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveExtraRuns;", "setExtraRuns", "(Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveExtraRuns;)V", "extraRuns", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveEquations;", "m", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveEquations;", "getEquations", "()Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveEquations;", "setEquations", "(Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveEquations;)V", "equations", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCurrentPartnership;", "n", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCurrentPartnership;", "getCurrentPartnership", "()Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCurrentPartnership;", "setCurrentPartnership", "(Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCurrentPartnership;)V", "currentPartnership", "o", "getDidNotBat", "setDidNotBat", "didNotBat", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getRecentScores", "setRecentScores", "recentScores", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getLastFiveOvers", "setLastFiveOvers", "lastFiveOvers", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getLastTenOvers", "setLastTenOvers", "lastTenOvers", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveInning {
    @SerializedName("iid")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5930a;
    @SerializedName(CTVariableUtils.NUMBER)
    @Expose
    @Nullable
    private Integer b;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable
    private String c;
    @SerializedName("short_name")
    @Expose
    @Nullable
    private String d;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable
    private Integer e;
    @SerializedName("result")
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("batting_team_id")
    @Expose
    @Nullable
    private Integer g;
    @SerializedName("fielding_team_id")
    @Expose
    @Nullable
    private Integer h;
    @SerializedName("scores")
    @Expose
    @Nullable
    private String i;
    @SerializedName("scores_full")
    @Expose
    @Nullable
    private String j;
    @SerializedName("fielder")
    @Expose
    @Nullable
    private List<? extends Object> k;
    @SerializedName("extra_runs")
    @Expose
    @Nullable
    private LiveExtraRuns l;
    @SerializedName("equations")
    @Expose
    @Nullable
    private LiveEquations m;
    @SerializedName("current_partnership")
    @Expose
    @Nullable
    private LiveCurrentPartnership n;
    @SerializedName("did_not_bat")
    @Expose
    @Nullable
    private List<? extends Object> o;
    @SerializedName("recent_scores")
    @Expose
    @Nullable
    private String p;
    @SerializedName("last_five_overs")
    @Expose
    @Nullable
    private String q;
    @SerializedName("last_ten_overs")
    @Expose
    @Nullable
    private String r;

    @Nullable
    public final Integer getBattingTeamId() {
        return this.g;
    }

    @Nullable
    public final LiveCurrentPartnership getCurrentPartnership() {
        return this.n;
    }

    @Nullable
    public final List<Object> getDidNotBat() {
        return this.o;
    }

    @Nullable
    public final LiveEquations getEquations() {
        return this.m;
    }

    @Nullable
    public final LiveExtraRuns getExtraRuns() {
        return this.l;
    }

    @Nullable
    public final List<Object> getFielder() {
        return this.k;
    }

    @Nullable
    public final Integer getFieldingTeamId() {
        return this.h;
    }

    @Nullable
    public final Integer getIid() {
        return this.f5930a;
    }

    @Nullable
    public final String getLastFiveOvers() {
        return this.q;
    }

    @Nullable
    public final String getLastTenOvers() {
        return this.r;
    }

    @Nullable
    public final String getName() {
        return this.c;
    }

    @Nullable
    public final Integer getNumber() {
        return this.b;
    }

    @Nullable
    public final String getRecentScores() {
        return this.p;
    }

    @Nullable
    public final Integer getResult() {
        return this.f;
    }

    @Nullable
    public final String getScores() {
        return this.i;
    }

    @Nullable
    public final String getScoresFull() {
        return this.j;
    }

    @Nullable
    public final String getShortName() {
        return this.d;
    }

    @Nullable
    public final Integer getStatus() {
        return this.e;
    }

    public final void setBattingTeamId(@Nullable Integer num) {
        this.g = num;
    }

    public final void setCurrentPartnership(@Nullable LiveCurrentPartnership liveCurrentPartnership) {
        this.n = liveCurrentPartnership;
    }

    public final void setDidNotBat(@Nullable List<? extends Object> list) {
        this.o = list;
    }

    public final void setEquations(@Nullable LiveEquations liveEquations) {
        this.m = liveEquations;
    }

    public final void setExtraRuns(@Nullable LiveExtraRuns liveExtraRuns) {
        this.l = liveExtraRuns;
    }

    public final void setFielder(@Nullable List<? extends Object> list) {
        this.k = list;
    }

    public final void setFieldingTeamId(@Nullable Integer num) {
        this.h = num;
    }

    public final void setIid(@Nullable Integer num) {
        this.f5930a = num;
    }

    public final void setLastFiveOvers(@Nullable String str) {
        this.q = str;
    }

    public final void setLastTenOvers(@Nullable String str) {
        this.r = str;
    }

    public final void setName(@Nullable String str) {
        this.c = str;
    }

    public final void setNumber(@Nullable Integer num) {
        this.b = num;
    }

    public final void setRecentScores(@Nullable String str) {
        this.p = str;
    }

    public final void setResult(@Nullable Integer num) {
        this.f = num;
    }

    public final void setScores(@Nullable String str) {
        this.i = str;
    }

    public final void setScoresFull(@Nullable String str) {
        this.j = str;
    }

    public final void setShortName(@Nullable String str) {
        this.d = str;
    }

    public final void setStatus(@Nullable Integer num) {
        this.e = num;
    }
}
