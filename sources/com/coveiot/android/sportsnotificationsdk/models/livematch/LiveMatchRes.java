package com.coveiot.android.sportsnotificationsdk.models.livematch;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b^\u0010_R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R$\u0010!\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R$\u0010%\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0010\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R$\u0010)\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0010\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010\u0014R$\u0010-\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0004\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR$\u00105\u001a\u0004\u0018\u00010.8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R*\u0010>\u001a\n\u0012\u0004\u0012\u000207\u0018\u0001068\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R*\u0010C\u001a\n\u0012\u0004\u0012\u00020?\u0018\u0001068\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b@\u00109\u001a\u0004\bA\u0010;\"\u0004\bB\u0010=R$\u0010G\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bD\u0010\u0004\u001a\u0004\bE\u0010\u0006\"\u0004\bF\u0010\bR$\u0010K\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bH\u0010\u0004\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\bR*\u0010P\u001a\n\u0012\u0004\u0012\u00020L\u0018\u0001068\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bM\u00109\u001a\u0004\bN\u0010;\"\u0004\bO\u0010=R$\u0010X\u001a\u0004\u0018\u00010Q8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR*\u0010]\u001a\n\u0012\u0004\u0012\u00020Y\u0018\u0001068\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bZ\u00109\u001a\u0004\b[\u0010;\"\u0004\b\\\u0010=¨\u0006`"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveMatchRes;", "", "", "a", "Ljava/lang/Integer;", "getMid", "()Ljava/lang/Integer;", "setMid", "(Ljava/lang/Integer;)V", "mid", "b", "getStatus", "setStatus", NotificationCompat.CATEGORY_STATUS, "", c.f10260a, "Ljava/lang/String;", "getStatusStr", "()Ljava/lang/String;", "setStatusStr", "(Ljava/lang/String;)V", "statusStr", "d", "getGameState", "setGameState", "gameState", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getGameStateStr", "setGameStateStr", "gameStateStr", "f", "getStatusNote", "setStatusNote", "statusNote", "g", "getTeamBatting", "setTeamBatting", "teamBatting", "h", "getTeamBowling", "setTeamBowling", "teamBowling", "i", "getLiveInningNumber", "setLiveInningNumber", "liveInningNumber", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveScore;", "j", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveScore;", "getLiveScore", "()Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveScore;", "setLiveScore", "(Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveScore;)V", "liveScore", "", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBatsman;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Ljava/util/List;", "getBatsmen", "()Ljava/util/List;", "setBatsmen", "(Ljava/util/List;)V", "batsmen", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBowler;", "l", "getBowlers", "setBowlers", "bowlers", "m", "getCommentary", "setCommentary", "commentary", "n", "getWagon", "setWagon", "wagon", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCommentary;", "o", "getCommentaries", "setCommentaries", "commentaries", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveInning;", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveInning;", "getLiveInning", "()Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveInning;", "setLiveInning", "(Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveInning;)V", "liveInning", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveMatchPlayer;", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getPlayers", "setPlayers", "players", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveMatchRes {
    @SerializedName("mid")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5932a;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("status_str")
    @Expose
    @Nullable
    private String c;
    @SerializedName("game_state")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("game_state_str")
    @Expose
    @Nullable
    private String e;
    @SerializedName("status_note")
    @Expose
    @Nullable
    private String f;
    @SerializedName("team_batting")
    @Expose
    @Nullable
    private String g;
    @SerializedName("team_bowling")
    @Expose
    @Nullable
    private String h;
    @SerializedName("live_inning_number")
    @Expose
    @Nullable
    private Integer i;
    @SerializedName("live_score")
    @Expose
    @Nullable
    private LiveScore j;
    @SerializedName("batsmen")
    @Expose
    @Nullable
    private List<LiveBatsman> k;
    @SerializedName("bowlers")
    @Expose
    @Nullable
    private List<LiveBowler> l;
    @SerializedName("commentary")
    @Expose
    @Nullable
    private Integer m;
    @SerializedName("wagon")
    @Expose
    @Nullable
    private Integer n;
    @SerializedName("commentaries")
    @Expose
    @Nullable
    private List<LiveCommentary> o;
    @SerializedName("live_inning")
    @Expose
    @Nullable
    private LiveInning p;
    @SerializedName("players")
    @Expose
    @Nullable
    private List<LiveMatchPlayer> q;

    @Nullable
    public final List<LiveBatsman> getBatsmen() {
        return this.k;
    }

    @Nullable
    public final List<LiveBowler> getBowlers() {
        return this.l;
    }

    @Nullable
    public final List<LiveCommentary> getCommentaries() {
        return this.o;
    }

    @Nullable
    public final Integer getCommentary() {
        return this.m;
    }

    @Nullable
    public final Integer getGameState() {
        return this.d;
    }

    @Nullable
    public final String getGameStateStr() {
        return this.e;
    }

    @Nullable
    public final LiveInning getLiveInning() {
        return this.p;
    }

    @Nullable
    public final Integer getLiveInningNumber() {
        return this.i;
    }

    @Nullable
    public final LiveScore getLiveScore() {
        return this.j;
    }

    @Nullable
    public final Integer getMid() {
        return this.f5932a;
    }

    @Nullable
    public final List<LiveMatchPlayer> getPlayers() {
        return this.q;
    }

    @Nullable
    public final Integer getStatus() {
        return this.b;
    }

    @Nullable
    public final String getStatusNote() {
        return this.f;
    }

    @Nullable
    public final String getStatusStr() {
        return this.c;
    }

    @Nullable
    public final String getTeamBatting() {
        return this.g;
    }

    @Nullable
    public final String getTeamBowling() {
        return this.h;
    }

    @Nullable
    public final Integer getWagon() {
        return this.n;
    }

    public final void setBatsmen(@Nullable List<LiveBatsman> list) {
        this.k = list;
    }

    public final void setBowlers(@Nullable List<LiveBowler> list) {
        this.l = list;
    }

    public final void setCommentaries(@Nullable List<LiveCommentary> list) {
        this.o = list;
    }

    public final void setCommentary(@Nullable Integer num) {
        this.m = num;
    }

    public final void setGameState(@Nullable Integer num) {
        this.d = num;
    }

    public final void setGameStateStr(@Nullable String str) {
        this.e = str;
    }

    public final void setLiveInning(@Nullable LiveInning liveInning) {
        this.p = liveInning;
    }

    public final void setLiveInningNumber(@Nullable Integer num) {
        this.i = num;
    }

    public final void setLiveScore(@Nullable LiveScore liveScore) {
        this.j = liveScore;
    }

    public final void setMid(@Nullable Integer num) {
        this.f5932a = num;
    }

    public final void setPlayers(@Nullable List<LiveMatchPlayer> list) {
        this.q = list;
    }

    public final void setStatus(@Nullable Integer num) {
        this.b = num;
    }

    public final void setStatusNote(@Nullable String str) {
        this.f = str;
    }

    public final void setStatusStr(@Nullable String str) {
        this.c = str;
    }

    public final void setTeamBatting(@Nullable String str) {
        this.g = str;
    }

    public final void setTeamBowling(@Nullable String str) {
        this.h = str;
    }

    public final void setWagon(@Nullable Integer num) {
        this.n = num;
    }
}
