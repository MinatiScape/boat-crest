package com.coveiot.android.sportsnotificationsdk.models.matchinfo;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\t¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R$\u0010)\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\f\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R$\u0010-\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\f\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010\u0010R$\u00101\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\f\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0010R$\u00105\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b2\u0010\u0004\u001a\u0004\b3\u0010\u0006\"\u0004\b4\u0010\bR$\u00109\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b6\u0010\f\u001a\u0004\b7\u0010\u000e\"\u0004\b8\u0010\u0010R$\u0010A\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010I\u001a\u0004\u0018\u00010B8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010Q\u001a\u0004\u0018\u00010J8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR$\u0010U\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u0010\f\u001a\u0004\bS\u0010\u000e\"\u0004\bT\u0010\u0010R$\u0010Y\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u0010\f\u001a\u0004\bW\u0010\u000e\"\u0004\bX\u0010\u0010R$\u0010]\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bZ\u0010\u0004\u001a\u0004\b[\u0010\u0006\"\u0004\b\\\u0010\bR$\u0010a\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b^\u0010\u0004\u001a\u0004\b_\u0010\u0006\"\u0004\b`\u0010\bR$\u0010i\u001a\u0004\u0018\u00010b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR$\u0010m\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bj\u0010\f\u001a\u0004\bk\u0010\u000e\"\u0004\bl\u0010\u0010R$\u0010q\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bn\u0010\f\u001a\u0004\bo\u0010\u000e\"\u0004\bp\u0010\u0010R$\u0010u\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\br\u0010\f\u001a\u0004\bs\u0010\u000e\"\u0004\bt\u0010\u0010R$\u0010y\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bv\u0010\f\u001a\u0004\bw\u0010\u000e\"\u0004\bx\u0010\u0010R$\u0010}\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bz\u0010\f\u001a\u0004\b{\u0010\u000e\"\u0004\b|\u0010\u0010R&\u0010\u0081\u0001\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0013\n\u0004\b~\u0010\f\u001a\u0004\b\u007f\u0010\u000e\"\u0005\b\u0080\u0001\u0010\u0010R(\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0015\n\u0005\b\u0082\u0001\u0010\u0004\u001a\u0005\b\u0083\u0001\u0010\u0006\"\u0005\b\u0084\u0001\u0010\bR(\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0015\n\u0005\b\u0086\u0001\u0010\u0004\u001a\u0005\b\u0087\u0001\u0010\u0006\"\u0005\b\u0088\u0001\u0010\bR(\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0015\n\u0005\b\u008a\u0001\u0010\u0004\u001a\u0005\b\u008b\u0001\u0010\u0006\"\u0005\b\u008c\u0001\u0010\bR,\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u008e\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0006\b\u008f\u0001\u0010\u0090\u0001\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0006\b\u0093\u0001\u0010\u0094\u0001¨\u0006\u0098\u0001"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoResponse;", "", "", "a", "Ljava/lang/Integer;", "getMatchId", "()Ljava/lang/Integer;", "setMatchId", "(Ljava/lang/Integer;)V", "matchId", "", "b", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title", c.f10260a, "getSubtitle", "setSubtitle", "subtitle", "d", "getFormat", "setFormat", "format", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getFormatStr", "setFormatStr", "formatStr", "f", "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "g", "getStatusStr", "setStatusStr", "statusStr", "h", "getStatusNote", "setStatusNote", "statusNote", "i", "getVerified", "setVerified", "verified", "j", "getPreSquad", "setPreSquad", "preSquad", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getGameState", "setGameState", "gameState", "l", "getGameStateStr", "setGameStateStr", "gameStateStr", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoCompetition;", "m", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoCompetition;", "getCompetition", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoCompetition;", "setCompetition", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoCompetition;)V", "competition", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeama;", "n", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeama;", "getTeama", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeama;", "setTeama", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeama;)V", "teama", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeamb;", "o", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeamb;", "getTeamb", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeamb;", "setTeamb", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoTeamb;)V", "teamb", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getDateStart", "setDateStart", "dateStart", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getDateEnd", "setDateEnd", "dateEnd", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getTimestampStart", "setTimestampStart", "timestampStart", "s", "getTimestampEnd", "setTimestampEnd", "timestampEnd", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoVenue;", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoVenue;", "getVenue", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoVenue;", "setVenue", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoVenue;)V", "venue", "u", "getUmpires", "setUmpires", "umpires", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "getReferee", "setReferee", "referee", Constants.INAPP_WINDOW, "getEquation", "setEquation", "equation", "x", "getLive", "setLive", "live", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "getResult", "setResult", "result", "z", "getWinMargin", "setWinMargin", "winMargin", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "getCommentary", "setCommentary", "commentary", "B", "getWagon", "setWagon", "wagon", WeatherCriteria.UNIT_CELSIUS, "getLatestInningNumber", "setLatestInningNumber", "latestInningNumber", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoToss;", "D", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoToss;", "getToss", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoToss;", "setToss", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoToss;)V", "toss", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class MInfoResponse {
    @SerializedName("commentary")
    @Expose
    @Nullable
    private Integer A;
    @SerializedName("wagon")
    @Expose
    @Nullable
    private Integer B;
    @SerializedName("latest_inning_number")
    @Expose
    @Nullable
    private Integer C;
    @SerializedName("toss")
    @Expose
    @Nullable
    private MInfoToss D;
    @SerializedName("match_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5936a;
    @SerializedName("title")
    @Expose
    @Nullable
    private String b;
    @SerializedName("subtitle")
    @Expose
    @Nullable
    private String c;
    @SerializedName("format")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("format_str")
    @Expose
    @Nullable
    private String e;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("status_str")
    @Expose
    @Nullable
    private String g;
    @SerializedName("status_note")
    @Expose
    @Nullable
    private String h;
    @SerializedName("verified")
    @Expose
    @Nullable
    private String i;
    @SerializedName("pre_squad")
    @Expose
    @Nullable
    private String j;
    @SerializedName("game_state")
    @Expose
    @Nullable
    private Integer k;
    @SerializedName("game_state_str")
    @Expose
    @Nullable
    private String l;
    @SerializedName("competition")
    @Expose
    @Nullable
    private MInfoCompetition m;
    @SerializedName("teama")
    @Expose
    @Nullable
    private MInfoTeama n;
    @SerializedName("teamb")
    @Expose
    @Nullable
    private MInfoTeamb o;
    @SerializedName("date_start")
    @Expose
    @Nullable
    private String p;
    @SerializedName("date_end")
    @Expose
    @Nullable
    private String q;
    @SerializedName("timestamp_start")
    @Expose
    @Nullable
    private Integer r;
    @SerializedName("timestamp_end")
    @Expose
    @Nullable
    private Integer s;
    @SerializedName("venue")
    @Expose
    @Nullable
    private MInfoVenue t;
    @SerializedName("umpires")
    @Expose
    @Nullable
    private String u;
    @SerializedName("referee")
    @Expose
    @Nullable
    private String v;
    @SerializedName("equation")
    @Expose
    @Nullable
    private String w;
    @SerializedName("live")
    @Expose
    @Nullable
    private String x;
    @SerializedName("result")
    @Expose
    @Nullable
    private String y;
    @SerializedName("win_margin")
    @Expose
    @Nullable
    private String z;

    @Nullable
    public final Integer getCommentary() {
        return this.A;
    }

    @Nullable
    public final MInfoCompetition getCompetition() {
        return this.m;
    }

    @Nullable
    public final String getDateEnd() {
        return this.q;
    }

    @Nullable
    public final String getDateStart() {
        return this.p;
    }

    @Nullable
    public final String getEquation() {
        return this.w;
    }

    @Nullable
    public final Integer getFormat() {
        return this.d;
    }

    @Nullable
    public final String getFormatStr() {
        return this.e;
    }

    @Nullable
    public final Integer getGameState() {
        return this.k;
    }

    @Nullable
    public final String getGameStateStr() {
        return this.l;
    }

    @Nullable
    public final Integer getLatestInningNumber() {
        return this.C;
    }

    @Nullable
    public final String getLive() {
        return this.x;
    }

    @Nullable
    public final Integer getMatchId() {
        return this.f5936a;
    }

    @Nullable
    public final String getPreSquad() {
        return this.j;
    }

    @Nullable
    public final String getReferee() {
        return this.v;
    }

    @Nullable
    public final String getResult() {
        return this.y;
    }

    @Nullable
    public final Integer getStatus() {
        return this.f;
    }

    @Nullable
    public final String getStatusNote() {
        return this.h;
    }

    @Nullable
    public final String getStatusStr() {
        return this.g;
    }

    @Nullable
    public final String getSubtitle() {
        return this.c;
    }

    @Nullable
    public final MInfoTeama getTeama() {
        return this.n;
    }

    @Nullable
    public final MInfoTeamb getTeamb() {
        return this.o;
    }

    @Nullable
    public final Integer getTimestampEnd() {
        return this.s;
    }

    @Nullable
    public final Integer getTimestampStart() {
        return this.r;
    }

    @Nullable
    public final String getTitle() {
        return this.b;
    }

    @Nullable
    public final MInfoToss getToss() {
        return this.D;
    }

    @Nullable
    public final String getUmpires() {
        return this.u;
    }

    @Nullable
    public final MInfoVenue getVenue() {
        return this.t;
    }

    @Nullable
    public final String getVerified() {
        return this.i;
    }

    @Nullable
    public final Integer getWagon() {
        return this.B;
    }

    @Nullable
    public final String getWinMargin() {
        return this.z;
    }

    public final void setCommentary(@Nullable Integer num) {
        this.A = num;
    }

    public final void setCompetition(@Nullable MInfoCompetition mInfoCompetition) {
        this.m = mInfoCompetition;
    }

    public final void setDateEnd(@Nullable String str) {
        this.q = str;
    }

    public final void setDateStart(@Nullable String str) {
        this.p = str;
    }

    public final void setEquation(@Nullable String str) {
        this.w = str;
    }

    public final void setFormat(@Nullable Integer num) {
        this.d = num;
    }

    public final void setFormatStr(@Nullable String str) {
        this.e = str;
    }

    public final void setGameState(@Nullable Integer num) {
        this.k = num;
    }

    public final void setGameStateStr(@Nullable String str) {
        this.l = str;
    }

    public final void setLatestInningNumber(@Nullable Integer num) {
        this.C = num;
    }

    public final void setLive(@Nullable String str) {
        this.x = str;
    }

    public final void setMatchId(@Nullable Integer num) {
        this.f5936a = num;
    }

    public final void setPreSquad(@Nullable String str) {
        this.j = str;
    }

    public final void setReferee(@Nullable String str) {
        this.v = str;
    }

    public final void setResult(@Nullable String str) {
        this.y = str;
    }

    public final void setStatus(@Nullable Integer num) {
        this.f = num;
    }

    public final void setStatusNote(@Nullable String str) {
        this.h = str;
    }

    public final void setStatusStr(@Nullable String str) {
        this.g = str;
    }

    public final void setSubtitle(@Nullable String str) {
        this.c = str;
    }

    public final void setTeama(@Nullable MInfoTeama mInfoTeama) {
        this.n = mInfoTeama;
    }

    public final void setTeamb(@Nullable MInfoTeamb mInfoTeamb) {
        this.o = mInfoTeamb;
    }

    public final void setTimestampEnd(@Nullable Integer num) {
        this.s = num;
    }

    public final void setTimestampStart(@Nullable Integer num) {
        this.r = num;
    }

    public final void setTitle(@Nullable String str) {
        this.b = str;
    }

    public final void setToss(@Nullable MInfoToss mInfoToss) {
        this.D = mInfoToss;
    }

    public final void setUmpires(@Nullable String str) {
        this.u = str;
    }

    public final void setVenue(@Nullable MInfoVenue mInfoVenue) {
        this.t = mInfoVenue;
    }

    public final void setVerified(@Nullable String str) {
        this.i = str;
    }

    public final void setWagon(@Nullable Integer num) {
        this.B = num;
    }

    public final void setWinMargin(@Nullable String str) {
        this.z = str;
    }
}
