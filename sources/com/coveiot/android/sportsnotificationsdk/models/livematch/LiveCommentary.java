package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b@\u0010AR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010-\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u00101\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\f\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0010R*\u0010:\u001a\n\u0012\u0004\u0012\u000203\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R*\u0010?\u001a\n\u0012\u0004\u0012\u00020;\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b<\u00105\u001a\u0004\b=\u00107\"\u0004\b>\u00109¨\u0006B"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveCommentary;", "", "", "a", "Ljava/lang/String;", "getEvent", "()Ljava/lang/String;", "setEvent", "(Ljava/lang/String;)V", "event", "", "b", "Ljava/lang/Integer;", "getBatsmanId", "()Ljava/lang/Integer;", "setBatsmanId", "(Ljava/lang/Integer;)V", "batsmanId", c.f10260a, "getBowlerId", "setBowlerId", "bowlerId", "d", "getOver", "setOver", "over", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getBall", "setBall", "ball", "f", "getScore", "setScore", FirebaseAnalytics.Param.SCORE, "g", "getCommentary", "setCommentary", "commentary", "", "h", "Ljava/lang/Boolean;", "getNoballDismissal", "()Ljava/lang/Boolean;", "setNoballDismissal", "(Ljava/lang/Boolean;)V", "noballDismissal", "i", "getRuns", "setRuns", "runs", "", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBat;", "j", "Ljava/util/List;", "getBats", "()Ljava/util/List;", "setBats", "(Ljava/util/List;)V", "bats", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveBowl;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getBowls", "setBowls", "bowls", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveCommentary {
    @SerializedName("event")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5926a;
    @SerializedName("batsman_id")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("bowler_id")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("over")
    @Expose
    @Nullable
    private String d;
    @SerializedName("ball")
    @Expose
    @Nullable
    private String e;
    @SerializedName(FirebaseAnalytics.Param.SCORE)
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("commentary")
    @Expose
    @Nullable
    private String g;
    @SerializedName("noball_dismissal")
    @Expose
    @Nullable
    private Boolean h;
    @SerializedName("runs")
    @Expose
    @Nullable
    private Integer i;
    @SerializedName("bats")
    @Expose
    @Nullable
    private List<LiveBat> j;
    @SerializedName("bowls")
    @Expose
    @Nullable
    private List<LiveBowl> k;

    @Nullable
    public final String getBall() {
        return this.e;
    }

    @Nullable
    public final List<LiveBat> getBats() {
        return this.j;
    }

    @Nullable
    public final Integer getBatsmanId() {
        return this.b;
    }

    @Nullable
    public final Integer getBowlerId() {
        return this.c;
    }

    @Nullable
    public final List<LiveBowl> getBowls() {
        return this.k;
    }

    @Nullable
    public final String getCommentary() {
        return this.g;
    }

    @Nullable
    public final String getEvent() {
        return this.f5926a;
    }

    @Nullable
    public final Boolean getNoballDismissal() {
        return this.h;
    }

    @Nullable
    public final String getOver() {
        return this.d;
    }

    @Nullable
    public final Integer getRuns() {
        return this.i;
    }

    @Nullable
    public final Integer getScore() {
        return this.f;
    }

    public final void setBall(@Nullable String str) {
        this.e = str;
    }

    public final void setBats(@Nullable List<LiveBat> list) {
        this.j = list;
    }

    public final void setBatsmanId(@Nullable Integer num) {
        this.b = num;
    }

    public final void setBowlerId(@Nullable Integer num) {
        this.c = num;
    }

    public final void setBowls(@Nullable List<LiveBowl> list) {
        this.k = list;
    }

    public final void setCommentary(@Nullable String str) {
        this.g = str;
    }

    public final void setEvent(@Nullable String str) {
        this.f5926a = str;
    }

    public final void setNoballDismissal(@Nullable Boolean bool) {
        this.h = bool;
    }

    public final void setOver(@Nullable String str) {
        this.d = str;
    }

    public final void setRuns(@Nullable Integer num) {
        this.i = num;
    }

    public final void setScore(@Nullable Integer num) {
        this.f = num;
    }
}
