package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\bZ\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bZ\u0010[R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\bR$\u0010-\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\u0004\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR$\u00101\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0004\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR$\u00105\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b2\u0010\u0004\u001a\u0004\b3\u0010\u0006\"\u0004\b4\u0010\bR$\u00109\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b6\u0010\u0004\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR$\u0010=\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b:\u0010\u0004\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR$\u0010A\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b>\u0010\u0004\u001a\u0004\b?\u0010\u0006\"\u0004\b@\u0010\bR$\u0010E\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bB\u0010\u0004\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR$\u0010I\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bF\u0010\u0004\u001a\u0004\bG\u0010\u0006\"\u0004\bH\u0010\bR$\u0010M\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bJ\u0010\u0004\u001a\u0004\bK\u0010\u0006\"\u0004\bL\u0010\bR$\u0010Q\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bN\u0010\u0004\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR$\u0010U\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u0010\u0004\u001a\u0004\bS\u0010\u0006\"\u0004\bT\u0010\bR$\u0010Y\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u0010\u0004\u001a\u0004\bW\u0010\u0006\"\u0004\bX\u0010\b¨\u0006\\"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBatsman;", "", "", "a", "Ljava/lang/String;", "getBatsmanId", "()Ljava/lang/String;", "setBatsmanId", "(Ljava/lang/String;)V", "batsmanId", "b", "getBatting", "setBatting", "batting", c.f10260a, "getPosition", "setPosition", DeviceKey.position, "d", "getRole", "setRole", "role", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getRoleStr", "setRoleStr", "roleStr", "f", "getRuns", "setRuns", "runs", "g", "getBallsFaced", "setBallsFaced", "ballsFaced", "h", "getFours", "setFours", "fours", "i", "getSixes", "setSixes", "sixes", "j", "getRun0", "setRun0", "run0", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getRun1", "setRun1", "run1", "l", "getRun2", "setRun2", "run2", "m", "getRun3", "setRun3", "run3", "n", "getRun5", "setRun5", "run5", "o", "getHowOut", "setHowOut", "howOut", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getDismissal", "setDismissal", "dismissal", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getStrikeRate", "setStrikeRate", "strikeRate", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getBowlerId", "setBowlerId", "bowlerId", "s", "getFirstFielderId", "setFirstFielderId", "firstFielderId", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getSecondFielderId", "setSecondFielderId", "secondFielderId", "u", "getThirdFielderId", "setThirdFielderId", "thirdFielderId", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardBatsman {
    @SerializedName("batsman_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5953a;
    @SerializedName("batting")
    @Expose
    @Nullable
    private String b;
    @SerializedName(DeviceKey.position)
    @Expose
    @Nullable
    private String c;
    @SerializedName("role")
    @Expose
    @Nullable
    private String d;
    @SerializedName("role_str")
    @Expose
    @Nullable
    private String e;
    @SerializedName("runs")
    @Expose
    @Nullable
    private String f;
    @SerializedName("balls_faced")
    @Expose
    @Nullable
    private String g;
    @SerializedName("fours")
    @Expose
    @Nullable
    private String h;
    @SerializedName("sixes")
    @Expose
    @Nullable
    private String i;
    @SerializedName("run0")
    @Expose
    @Nullable
    private String j;
    @SerializedName("run1")
    @Expose
    @Nullable
    private String k;
    @SerializedName("run2")
    @Expose
    @Nullable
    private String l;
    @SerializedName("run3")
    @Expose
    @Nullable
    private String m;
    @SerializedName("run5")
    @Expose
    @Nullable
    private String n;
    @SerializedName("how_out")
    @Expose
    @Nullable
    private String o;
    @SerializedName("dismissal")
    @Expose
    @Nullable
    private String p;
    @SerializedName("strike_rate")
    @Expose
    @Nullable
    private String q;
    @SerializedName("bowler_id")
    @Expose
    @Nullable
    private String r;
    @SerializedName("first_fielder_id")
    @Expose
    @Nullable
    private String s;
    @SerializedName("second_fielder_id")
    @Expose
    @Nullable
    private String t;
    @SerializedName("third_fielder_id")
    @Expose
    @Nullable
    private String u;

    @Nullable
    public final String getBallsFaced() {
        return this.g;
    }

    @Nullable
    public final String getBatsmanId() {
        return this.f5953a;
    }

    @Nullable
    public final String getBatting() {
        return this.b;
    }

    @Nullable
    public final String getBowlerId() {
        return this.r;
    }

    @Nullable
    public final String getDismissal() {
        return this.p;
    }

    @Nullable
    public final String getFirstFielderId() {
        return this.s;
    }

    @Nullable
    public final String getFours() {
        return this.h;
    }

    @Nullable
    public final String getHowOut() {
        return this.o;
    }

    @Nullable
    public final String getPosition() {
        return this.c;
    }

    @Nullable
    public final String getRole() {
        return this.d;
    }

    @Nullable
    public final String getRoleStr() {
        return this.e;
    }

    @Nullable
    public final String getRun0() {
        return this.j;
    }

    @Nullable
    public final String getRun1() {
        return this.k;
    }

    @Nullable
    public final String getRun2() {
        return this.l;
    }

    @Nullable
    public final String getRun3() {
        return this.m;
    }

    @Nullable
    public final String getRun5() {
        return this.n;
    }

    @Nullable
    public final String getRuns() {
        return this.f;
    }

    @Nullable
    public final String getSecondFielderId() {
        return this.t;
    }

    @Nullable
    public final String getSixes() {
        return this.i;
    }

    @Nullable
    public final String getStrikeRate() {
        return this.q;
    }

    @Nullable
    public final String getThirdFielderId() {
        return this.u;
    }

    public final void setBallsFaced(@Nullable String str) {
        this.g = str;
    }

    public final void setBatsmanId(@Nullable String str) {
        this.f5953a = str;
    }

    public final void setBatting(@Nullable String str) {
        this.b = str;
    }

    public final void setBowlerId(@Nullable String str) {
        this.r = str;
    }

    public final void setDismissal(@Nullable String str) {
        this.p = str;
    }

    public final void setFirstFielderId(@Nullable String str) {
        this.s = str;
    }

    public final void setFours(@Nullable String str) {
        this.h = str;
    }

    public final void setHowOut(@Nullable String str) {
        this.o = str;
    }

    public final void setPosition(@Nullable String str) {
        this.c = str;
    }

    public final void setRole(@Nullable String str) {
        this.d = str;
    }

    public final void setRoleStr(@Nullable String str) {
        this.e = str;
    }

    public final void setRun0(@Nullable String str) {
        this.j = str;
    }

    public final void setRun1(@Nullable String str) {
        this.k = str;
    }

    public final void setRun2(@Nullable String str) {
        this.l = str;
    }

    public final void setRun3(@Nullable String str) {
        this.m = str;
    }

    public final void setRun5(@Nullable String str) {
        this.n = str;
    }

    public final void setRuns(@Nullable String str) {
        this.f = str;
    }

    public final void setSecondFielderId(@Nullable String str) {
        this.t = str;
    }

    public final void setSixes(@Nullable String str) {
        this.i = str;
    }

    public final void setStrikeRate(@Nullable String str) {
        this.q = str;
    }

    public final void setThirdFielderId(@Nullable String str) {
        this.u = str;
    }
}
