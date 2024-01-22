package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b?\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\t¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R$\u0010)\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\f\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R$\u0010-\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\f\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010\u0010R$\u00101\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0004\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR$\u00105\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b2\u0010\f\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R$\u0010=\u001a\u0004\u0018\u0001068\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R$\u0010E\u001a\u0004\u0018\u00010>8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR$\u0010I\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bF\u0010\f\u001a\u0004\bG\u0010\u000e\"\u0004\bH\u0010\u0010R$\u0010M\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bJ\u0010\f\u001a\u0004\bK\u0010\u000e\"\u0004\bL\u0010\u0010R$\u0010Q\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bN\u0010\u0004\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR$\u0010U\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u0010\u0004\u001a\u0004\bS\u0010\u0006\"\u0004\bT\u0010\bR$\u0010Y\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u0010\f\u001a\u0004\bW\u0010\u000e\"\u0004\bX\u0010\u0010R$\u0010]\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bZ\u0010\f\u001a\u0004\b[\u0010\u000e\"\u0004\b\\\u0010\u0010R$\u0010a\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b^\u0010\f\u001a\u0004\b_\u0010\u000e\"\u0004\b`\u0010\u0010R$\u0010e\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bb\u0010\f\u001a\u0004\bc\u0010\u000e\"\u0004\bd\u0010\u0010R$\u0010i\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bf\u0010\f\u001a\u0004\bg\u0010\u000e\"\u0004\bh\u0010\u0010R$\u0010m\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bj\u0010\f\u001a\u0004\bk\u0010\u000e\"\u0004\bl\u0010\u0010R$\u0010q\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bn\u0010\u0004\u001a\u0004\bo\u0010\u0006\"\u0004\bp\u0010\bR$\u0010u\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\br\u0010\u0004\u001a\u0004\bs\u0010\u0006\"\u0004\bt\u0010\bR$\u0010y\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bv\u0010\u0004\u001a\u0004\bw\u0010\u0006\"\u0004\bx\u0010\bR$\u0010}\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bz\u0010\u0004\u001a\u0004\b{\u0010\u0006\"\u0004\b|\u0010\bR*\u0010\u0085\u0001\u001a\u0004\u0018\u00010~8\u0006@\u0006X\u0087\u000e¢\u0006\u0017\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R3\u0010\u008e\u0001\u001a\f\u0012\u0005\u0012\u00030\u0087\u0001\u0018\u00010\u0086\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006\u0091\u0001"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardResponse;", "", "", "a", "Ljava/lang/Integer;", "getMatchId", "()Ljava/lang/Integer;", "setMatchId", "(Ljava/lang/Integer;)V", "matchId", "", "b", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title", c.f10260a, "getSubtitle", "setSubtitle", "subtitle", "d", "getFormat", "setFormat", "format", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getFormatStr", "setFormatStr", "formatStr", "f", "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "g", "getStatusStr", "setStatusStr", "statusStr", "h", "getStatusNote", "setStatusNote", "statusNote", "i", "getVerified", "setVerified", "verified", "j", "getGameState", "setGameState", "gameState", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getGameStateStr", "setGameStateStr", "gameStateStr", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeama;", "l", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeama;", "getTeama", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeama;", "setTeama", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeama;)V", "teama", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeamb;", "m", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeamb;", "getTeamb", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeamb;", "setTeamb", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeamb;)V", "teamb", "n", "getDateStart", "setDateStart", "dateStart", "o", "getDateEnd", "setDateEnd", "dateEnd", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getTimestampStart", "setTimestampStart", "timestampStart", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getTimestampEnd", "setTimestampEnd", "timestampEnd", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getUmpires", "setUmpires", "umpires", "s", "getReferee", "setReferee", "referee", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getEquation", "setEquation", "equation", "u", "getLive", "setLive", "live", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "getResult", "setResult", "result", Constants.INAPP_WINDOW, "getWinMargin", "setWinMargin", "winMargin", "x", "getWinningTeamId", "setWinningTeamId", "winningTeamId", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "getCommentary", "setCommentary", "commentary", "z", "getWagon", "setWagon", "wagon", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "getLatestInningNumber", "setLatestInningNumber", "latestInningNumber", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardToss;", "B", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardToss;", "getToss", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardToss;", "setToss", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardToss;)V", "toss", "", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardInning;", WeatherCriteria.UNIT_CELSIUS, "Ljava/util/List;", "getInnings", "()Ljava/util/List;", "setInnings", "(Ljava/util/List;)V", "innings", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardResponse {
    @SerializedName("latest_inning_number")
    @Expose
    @Nullable
    private Integer A;
    @SerializedName("toss")
    @Expose
    @Nullable
    private ScorecardToss B;
    @SerializedName("innings")
    @Expose
    @Nullable
    private List<ScorecardInning> C;
    @SerializedName("match_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5970a;
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
    @SerializedName("game_state")
    @Expose
    @Nullable
    private Integer j;
    @SerializedName("game_state_str")
    @Expose
    @Nullable
    private String k;
    @SerializedName("teama")
    @Expose
    @Nullable
    private ScorecardTeama l;
    @SerializedName("teamb")
    @Expose
    @Nullable
    private ScorecardTeamb m;
    @SerializedName("date_start")
    @Expose
    @Nullable
    private String n;
    @SerializedName("date_end")
    @Expose
    @Nullable
    private String o;
    @SerializedName("timestamp_start")
    @Expose
    @Nullable
    private Integer p;
    @SerializedName("timestamp_end")
    @Expose
    @Nullable
    private Integer q;
    @SerializedName("umpires")
    @Expose
    @Nullable
    private String r;
    @SerializedName("referee")
    @Expose
    @Nullable
    private String s;
    @SerializedName("equation")
    @Expose
    @Nullable
    private String t;
    @SerializedName("live")
    @Expose
    @Nullable
    private String u;
    @SerializedName("result")
    @Expose
    @Nullable
    private String v;
    @SerializedName("win_margin")
    @Expose
    @Nullable
    private String w;
    @SerializedName("winning_team_id")
    @Expose
    @Nullable
    private Integer x;
    @SerializedName("commentary")
    @Expose
    @Nullable
    private Integer y;
    @SerializedName("wagon")
    @Expose
    @Nullable
    private Integer z;

    @Nullable
    public final Integer getCommentary() {
        return this.y;
    }

    @Nullable
    public final String getDateEnd() {
        return this.o;
    }

    @Nullable
    public final String getDateStart() {
        return this.n;
    }

    @Nullable
    public final String getEquation() {
        return this.t;
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
        return this.j;
    }

    @Nullable
    public final String getGameStateStr() {
        return this.k;
    }

    @Nullable
    public final List<ScorecardInning> getInnings() {
        return this.C;
    }

    @Nullable
    public final Integer getLatestInningNumber() {
        return this.A;
    }

    @Nullable
    public final String getLive() {
        return this.u;
    }

    @Nullable
    public final Integer getMatchId() {
        return this.f5970a;
    }

    @Nullable
    public final String getReferee() {
        return this.s;
    }

    @Nullable
    public final String getResult() {
        return this.v;
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
    public final ScorecardTeama getTeama() {
        return this.l;
    }

    @Nullable
    public final ScorecardTeamb getTeamb() {
        return this.m;
    }

    @Nullable
    public final Integer getTimestampEnd() {
        return this.q;
    }

    @Nullable
    public final Integer getTimestampStart() {
        return this.p;
    }

    @Nullable
    public final String getTitle() {
        return this.b;
    }

    @Nullable
    public final ScorecardToss getToss() {
        return this.B;
    }

    @Nullable
    public final String getUmpires() {
        return this.r;
    }

    @Nullable
    public final String getVerified() {
        return this.i;
    }

    @Nullable
    public final Integer getWagon() {
        return this.z;
    }

    @Nullable
    public final String getWinMargin() {
        return this.w;
    }

    @Nullable
    public final Integer getWinningTeamId() {
        return this.x;
    }

    public final void setCommentary(@Nullable Integer num) {
        this.y = num;
    }

    public final void setDateEnd(@Nullable String str) {
        this.o = str;
    }

    public final void setDateStart(@Nullable String str) {
        this.n = str;
    }

    public final void setEquation(@Nullable String str) {
        this.t = str;
    }

    public final void setFormat(@Nullable Integer num) {
        this.d = num;
    }

    public final void setFormatStr(@Nullable String str) {
        this.e = str;
    }

    public final void setGameState(@Nullable Integer num) {
        this.j = num;
    }

    public final void setGameStateStr(@Nullable String str) {
        this.k = str;
    }

    public final void setInnings(@Nullable List<ScorecardInning> list) {
        this.C = list;
    }

    public final void setLatestInningNumber(@Nullable Integer num) {
        this.A = num;
    }

    public final void setLive(@Nullable String str) {
        this.u = str;
    }

    public final void setMatchId(@Nullable Integer num) {
        this.f5970a = num;
    }

    public final void setReferee(@Nullable String str) {
        this.s = str;
    }

    public final void setResult(@Nullable String str) {
        this.v = str;
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

    public final void setTeama(@Nullable ScorecardTeama scorecardTeama) {
        this.l = scorecardTeama;
    }

    public final void setTeamb(@Nullable ScorecardTeamb scorecardTeamb) {
        this.m = scorecardTeamb;
    }

    public final void setTimestampEnd(@Nullable Integer num) {
        this.q = num;
    }

    public final void setTimestampStart(@Nullable Integer num) {
        this.p = num;
    }

    public final void setTitle(@Nullable String str) {
        this.b = str;
    }

    public final void setToss(@Nullable ScorecardToss scorecardToss) {
        this.B = scorecardToss;
    }

    public final void setUmpires(@Nullable String str) {
        this.r = str;
    }

    public final void setVerified(@Nullable String str) {
        this.i = str;
    }

    public final void setWagon(@Nullable Integer num) {
        this.z = num;
    }

    public final void setWinMargin(@Nullable String str) {
        this.w = str;
    }

    public final void setWinningTeamId(@Nullable Integer num) {
        this.x = num;
    }
}
