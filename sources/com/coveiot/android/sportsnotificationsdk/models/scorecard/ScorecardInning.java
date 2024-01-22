package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b:\u0010;R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0019\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR$\u0010-\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0010\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014R$\u00101\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0010\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014R$\u00109\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u0006<"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardInning;", "", "", "a", "Ljava/lang/Integer;", "getIid", "()Ljava/lang/Integer;", "setIid", "(Ljava/lang/Integer;)V", "iid", "b", "getNumber", "setNumber", CTVariableUtils.NUMBER, "", c.f10260a, "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, "d", "getShortName", "setShortName", "shortName", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "f", "getResult", "setResult", "result", "g", "getBattingTeamId", "setBattingTeamId", "battingTeamId", "h", "getFieldingTeamId", "setFieldingTeamId", "fieldingTeamId", "i", "getScores", "setScores", "scores", "j", "getScoresFull", "setScoresFull", "scoresFull", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardEquations;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardEquations;", "getEquations", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardEquations;", "setEquations", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardEquations;)V", "equations", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardInning {
    @SerializedName("iid")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5965a;
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
    @SerializedName("equations")
    @Expose
    @Nullable
    private ScorecardEquations k;

    @Nullable
    public final Integer getBattingTeamId() {
        return this.g;
    }

    @Nullable
    public final ScorecardEquations getEquations() {
        return this.k;
    }

    @Nullable
    public final Integer getFieldingTeamId() {
        return this.h;
    }

    @Nullable
    public final Integer getIid() {
        return this.f5965a;
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

    public final void setEquations(@Nullable ScorecardEquations scorecardEquations) {
        this.k = scorecardEquations;
    }

    public final void setFieldingTeamId(@Nullable Integer num) {
        this.h = num;
    }

    public final void setIid(@Nullable Integer num) {
        this.f5965a = num;
    }

    public final void setName(@Nullable String str) {
        this.c = str;
    }

    public final void setNumber(@Nullable Integer num) {
        this.b = num;
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
