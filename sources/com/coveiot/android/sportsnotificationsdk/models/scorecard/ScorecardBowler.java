package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b2\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b2\u00103R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR$\u0010-\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0004\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR$\u00101\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0004\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\b¨\u00064"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBowler;", "", "", "a", "Ljava/lang/String;", "getBowlerId", "()Ljava/lang/String;", "setBowlerId", "(Ljava/lang/String;)V", "bowlerId", "b", "getBowling", "setBowling", "bowling", c.f10260a, "getPosition", "setPosition", DeviceKey.position, "d", "getOvers", "setOvers", "overs", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getMaidens", "setMaidens", "maidens", "f", "getRunsConceded", "setRunsConceded", "runsConceded", "g", "getWickets", "setWickets", "wickets", "h", "getNoballs", "setNoballs", "noballs", "i", "getWides", "setWides", "wides", "j", "getEcon", "setEcon", "econ", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getRun0", "setRun0", "run0", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardBowler {
    @SerializedName("bowler_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5956a;
    @SerializedName("bowling")
    @Expose
    @Nullable
    private String b;
    @SerializedName(DeviceKey.position)
    @Expose
    @Nullable
    private String c;
    @SerializedName("overs")
    @Expose
    @Nullable
    private String d;
    @SerializedName("maidens")
    @Expose
    @Nullable
    private String e;
    @SerializedName("runs_conceded")
    @Expose
    @Nullable
    private String f;
    @SerializedName("wickets")
    @Expose
    @Nullable
    private String g;
    @SerializedName("noballs")
    @Expose
    @Nullable
    private String h;
    @SerializedName("wides")
    @Expose
    @Nullable
    private String i;
    @SerializedName("econ")
    @Expose
    @Nullable
    private String j;
    @SerializedName("run0")
    @Expose
    @Nullable
    private String k;

    @Nullable
    public final String getBowlerId() {
        return this.f5956a;
    }

    @Nullable
    public final String getBowling() {
        return this.b;
    }

    @Nullable
    public final String getEcon() {
        return this.j;
    }

    @Nullable
    public final String getMaidens() {
        return this.e;
    }

    @Nullable
    public final String getNoballs() {
        return this.h;
    }

    @Nullable
    public final String getOvers() {
        return this.d;
    }

    @Nullable
    public final String getPosition() {
        return this.c;
    }

    @Nullable
    public final String getRun0() {
        return this.k;
    }

    @Nullable
    public final String getRunsConceded() {
        return this.f;
    }

    @Nullable
    public final String getWickets() {
        return this.g;
    }

    @Nullable
    public final String getWides() {
        return this.i;
    }

    public final void setBowlerId(@Nullable String str) {
        this.f5956a = str;
    }

    public final void setBowling(@Nullable String str) {
        this.b = str;
    }

    public final void setEcon(@Nullable String str) {
        this.j = str;
    }

    public final void setMaidens(@Nullable String str) {
        this.e = str;
    }

    public final void setNoballs(@Nullable String str) {
        this.h = str;
    }

    public final void setOvers(@Nullable String str) {
        this.d = str;
    }

    public final void setPosition(@Nullable String str) {
        this.c = str;
    }

    public final void setRun0(@Nullable String str) {
        this.k = str;
    }

    public final void setRunsConceded(@Nullable String str) {
        this.f = str;
    }

    public final void setWickets(@Nullable String str) {
        this.g = str;
    }

    public final void setWides(@Nullable String str) {
        this.i = str;
    }
}
