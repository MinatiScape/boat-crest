package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b:\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bB\u0010CR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R$\u0010)\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\f\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R$\u0010-\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\f\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010\u0010R$\u00101\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\f\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0010R$\u00105\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b2\u0010\f\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R$\u00109\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b6\u0010\f\u001a\u0004\b7\u0010\u000e\"\u0004\b8\u0010\u0010R$\u0010=\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b:\u0010\f\u001a\u0004\b;\u0010\u000e\"\u0004\b<\u0010\u0010R$\u0010A\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b>\u0010\f\u001a\u0004\b?\u0010\u000e\"\u0004\b@\u0010\u0010¨\u0006D"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardCompetition;", "", "", "a", "Ljava/lang/Integer;", "getCid", "()Ljava/lang/Integer;", "setCid", "(Ljava/lang/Integer;)V", "cid", "", "b", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title", c.f10260a, "getAbbr", "setAbbr", "abbr", "d", "getType", "setType", "type", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getCategory", "setCategory", SavingTrackHelper.POINT_COL_CATEGORY, "f", "getMatchFormat", "setMatchFormat", "matchFormat", "g", "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "h", "getSeason", "setSeason", "season", "i", "getDatestart", "setDatestart", "datestart", "j", "getDateend", "setDateend", "dateend", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getTotalMatches", "setTotalMatches", "totalMatches", "l", "getTotalRounds", "setTotalRounds", "totalRounds", "m", "getTotalTeams", "setTotalTeams", "totalTeams", "n", "getCountry", "setCountry", "country", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardCompetition {
    @SerializedName("cid")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5957a;
    @SerializedName("title")
    @Expose
    @Nullable
    private String b;
    @SerializedName("abbr")
    @Expose
    @Nullable
    private String c;
    @SerializedName("type")
    @Expose
    @Nullable
    private String d;
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    @Expose
    @Nullable
    private String e;
    @SerializedName("match_format")
    @Expose
    @Nullable
    private String f;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable
    private String g;
    @SerializedName("season")
    @Expose
    @Nullable
    private String h;
    @SerializedName("datestart")
    @Expose
    @Nullable
    private String i;
    @SerializedName("dateend")
    @Expose
    @Nullable
    private String j;
    @SerializedName("total_matches")
    @Expose
    @Nullable
    private String k;
    @SerializedName("total_rounds")
    @Expose
    @Nullable
    private String l;
    @SerializedName("total_teams")
    @Expose
    @Nullable
    private String m;
    @SerializedName("country")
    @Expose
    @Nullable
    private String n;

    @Nullable
    public final String getAbbr() {
        return this.c;
    }

    @Nullable
    public final String getCategory() {
        return this.e;
    }

    @Nullable
    public final Integer getCid() {
        return this.f5957a;
    }

    @Nullable
    public final String getCountry() {
        return this.n;
    }

    @Nullable
    public final String getDateend() {
        return this.j;
    }

    @Nullable
    public final String getDatestart() {
        return this.i;
    }

    @Nullable
    public final String getMatchFormat() {
        return this.f;
    }

    @Nullable
    public final String getSeason() {
        return this.h;
    }

    @Nullable
    public final String getStatus() {
        return this.g;
    }

    @Nullable
    public final String getTitle() {
        return this.b;
    }

    @Nullable
    public final String getTotalMatches() {
        return this.k;
    }

    @Nullable
    public final String getTotalRounds() {
        return this.l;
    }

    @Nullable
    public final String getTotalTeams() {
        return this.m;
    }

    @Nullable
    public final String getType() {
        return this.d;
    }

    public final void setAbbr(@Nullable String str) {
        this.c = str;
    }

    public final void setCategory(@Nullable String str) {
        this.e = str;
    }

    public final void setCid(@Nullable Integer num) {
        this.f5957a = num;
    }

    public final void setCountry(@Nullable String str) {
        this.n = str;
    }

    public final void setDateend(@Nullable String str) {
        this.j = str;
    }

    public final void setDatestart(@Nullable String str) {
        this.i = str;
    }

    public final void setMatchFormat(@Nullable String str) {
        this.f = str;
    }

    public final void setSeason(@Nullable String str) {
        this.h = str;
    }

    public final void setStatus(@Nullable String str) {
        this.g = str;
    }

    public final void setTitle(@Nullable String str) {
        this.b = str;
    }

    public final void setTotalMatches(@Nullable String str) {
        this.k = str;
    }

    public final void setTotalRounds(@Nullable String str) {
        this.l = str;
    }

    public final void setTotalTeams(@Nullable String str) {
        this.m = str;
    }

    public final void setType(@Nullable String str) {
        this.d = str;
    }
}
