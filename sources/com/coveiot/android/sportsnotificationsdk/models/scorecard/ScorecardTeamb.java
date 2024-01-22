package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b&\u0010'R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010¨\u0006("}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeamb;", "", "", "a", "Ljava/lang/Integer;", "getTeamId", "()Ljava/lang/Integer;", "setTeamId", "(Ljava/lang/Integer;)V", "teamId", "", "b", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, c.f10260a, "getShortName", "setShortName", "shortName", "d", "getLogoUrl", "setLogoUrl", "logoUrl", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getScoresFull", "setScoresFull", "scoresFull", "f", "getScores", "setScores", "scores", "g", "getOvers", "setOvers", "overs", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardTeamb {
    @SerializedName("team_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5973a;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable
    private String b;
    @SerializedName("short_name")
    @Expose
    @Nullable
    private String c;
    @SerializedName("logo_url")
    @Expose
    @Nullable
    private String d;
    @SerializedName("scores_full")
    @Expose
    @Nullable
    private String e;
    @SerializedName("scores")
    @Expose
    @Nullable
    private String f;
    @SerializedName("overs")
    @Expose
    @Nullable
    private String g;

    @Nullable
    public final String getLogoUrl() {
        return this.d;
    }

    @Nullable
    public final String getName() {
        return this.b;
    }

    @Nullable
    public final String getOvers() {
        return this.g;
    }

    @Nullable
    public final String getScores() {
        return this.f;
    }

    @Nullable
    public final String getScoresFull() {
        return this.e;
    }

    @Nullable
    public final String getShortName() {
        return this.c;
    }

    @Nullable
    public final Integer getTeamId() {
        return this.f5973a;
    }

    public final void setLogoUrl(@Nullable String str) {
        this.d = str;
    }

    public final void setName(@Nullable String str) {
        this.b = str;
    }

    public final void setOvers(@Nullable String str) {
        this.g = str;
    }

    public final void setScores(@Nullable String str) {
        this.f = str;
    }

    public final void setScoresFull(@Nullable String str) {
        this.e = str;
    }

    public final void setShortName(@Nullable String str) {
        this.c = str;
    }

    public final void setTeamId(@Nullable Integer num) {
        this.f5973a = num;
    }
}
