package com.coveiot.android.sportsnotificationsdk.models.livematch;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010 \n\u0002\b6\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bb\u0010cR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010!\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R$\u0010%\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R$\u0010)\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\f\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R$\u0010-\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010\f\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010\u0010R*\u00105\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010.8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00109\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b6\u0010\f\u001a\u0004\b7\u0010\u000e\"\u0004\b8\u0010\u0010R$\u0010=\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b:\u0010\f\u001a\u0004\b;\u0010\u000e\"\u0004\b<\u0010\u0010R$\u0010A\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b>\u0010\f\u001a\u0004\b?\u0010\u000e\"\u0004\b@\u0010\u0010R$\u0010E\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bB\u0010\f\u001a\u0004\bC\u0010\u000e\"\u0004\bD\u0010\u0010R$\u0010I\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bF\u0010\f\u001a\u0004\bG\u0010\u000e\"\u0004\bH\u0010\u0010R$\u0010M\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bJ\u0010\f\u001a\u0004\bK\u0010\u000e\"\u0004\bL\u0010\u0010R$\u0010Q\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bN\u0010\u0004\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR$\u0010U\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bR\u0010\u0004\u001a\u0004\bS\u0010\u0006\"\u0004\bT\u0010\bR$\u0010Y\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u0010\u0004\u001a\u0004\bW\u0010\u0006\"\u0004\bX\u0010\bR$\u0010]\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bZ\u0010\f\u001a\u0004\b[\u0010\u000e\"\u0004\b\\\u0010\u0010R$\u0010a\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b^\u0010\f\u001a\u0004\b_\u0010\u000e\"\u0004\b`\u0010\u0010¨\u0006d"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/livematch/LiveMatchPlayer;", "", "", "a", "Ljava/lang/Integer;", "getPid", "()Ljava/lang/Integer;", "setPid", "(Ljava/lang/Integer;)V", "pid", "", "b", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title", c.f10260a, "getShortName", "setShortName", "shortName", "d", "getFirstName", "setFirstName", "firstName", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getLastName", "setLastName", "lastName", "f", "getMiddleName", "setMiddleName", "middleName", "g", "getBirthdate", "setBirthdate", "birthdate", "h", "getBirthplace", "setBirthplace", "birthplace", "i", "getCountry", "setCountry", "country", "", "j", "Ljava/util/List;", "getPrimaryTeam", "()Ljava/util/List;", "setPrimaryTeam", "(Ljava/util/List;)V", "primaryTeam", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getThumbUrl", "setThumbUrl", "thumbUrl", "l", "getLogoUrl", "setLogoUrl", "logoUrl", "m", "getPlayingRole", "setPlayingRole", "playingRole", "n", "getBattingStyle", "setBattingStyle", "battingStyle", "o", "getBowlingStyle", "setBowlingStyle", "bowlingStyle", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getFieldingPosition", "setFieldingPosition", "fieldingPosition", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getRecentMatch", "setRecentMatch", "recentMatch", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getRecentAppearance", "setRecentAppearance", "recentAppearance", "s", "getFantasyPlayerRating", "setFantasyPlayerRating", "fantasyPlayerRating", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getNationality", "setNationality", "nationality", "u", "getRole", "setRole", "role", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class LiveMatchPlayer {
    @SerializedName("pid")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5931a;
    @SerializedName("title")
    @Expose
    @Nullable
    private String b;
    @SerializedName("short_name")
    @Expose
    @Nullable
    private String c;
    @SerializedName("first_name")
    @Expose
    @Nullable
    private String d;
    @SerializedName("last_name")
    @Expose
    @Nullable
    private String e;
    @SerializedName("middle_name")
    @Expose
    @Nullable
    private String f;
    @SerializedName("birthdate")
    @Expose
    @Nullable
    private String g;
    @SerializedName("birthplace")
    @Expose
    @Nullable
    private String h;
    @SerializedName("country")
    @Expose
    @Nullable
    private String i;
    @SerializedName("primary_team")
    @Expose
    @Nullable
    private List<? extends Object> j;
    @SerializedName("thumb_url")
    @Expose
    @Nullable
    private String k;
    @SerializedName("logo_url")
    @Expose
    @Nullable
    private String l;
    @SerializedName("playing_role")
    @Expose
    @Nullable
    private String m;
    @SerializedName("batting_style")
    @Expose
    @Nullable
    private String n;
    @SerializedName("bowling_style")
    @Expose
    @Nullable
    private String o;
    @SerializedName("fielding_position")
    @Expose
    @Nullable
    private String p;
    @SerializedName("recent_match")
    @Expose
    @Nullable
    private Integer q;
    @SerializedName("recent_appearance")
    @Expose
    @Nullable
    private Integer r;
    @SerializedName("fantasy_player_rating")
    @Expose
    @Nullable
    private Integer s;
    @SerializedName("nationality")
    @Expose
    @Nullable
    private String t;
    @SerializedName("role")
    @Expose
    @Nullable
    private String u;

    @Nullable
    public final String getBattingStyle() {
        return this.n;
    }

    @Nullable
    public final String getBirthdate() {
        return this.g;
    }

    @Nullable
    public final String getBirthplace() {
        return this.h;
    }

    @Nullable
    public final String getBowlingStyle() {
        return this.o;
    }

    @Nullable
    public final String getCountry() {
        return this.i;
    }

    @Nullable
    public final Integer getFantasyPlayerRating() {
        return this.s;
    }

    @Nullable
    public final String getFieldingPosition() {
        return this.p;
    }

    @Nullable
    public final String getFirstName() {
        return this.d;
    }

    @Nullable
    public final String getLastName() {
        return this.e;
    }

    @Nullable
    public final String getLogoUrl() {
        return this.l;
    }

    @Nullable
    public final String getMiddleName() {
        return this.f;
    }

    @Nullable
    public final String getNationality() {
        return this.t;
    }

    @Nullable
    public final Integer getPid() {
        return this.f5931a;
    }

    @Nullable
    public final String getPlayingRole() {
        return this.m;
    }

    @Nullable
    public final List<Object> getPrimaryTeam() {
        return this.j;
    }

    @Nullable
    public final Integer getRecentAppearance() {
        return this.r;
    }

    @Nullable
    public final Integer getRecentMatch() {
        return this.q;
    }

    @Nullable
    public final String getRole() {
        return this.u;
    }

    @Nullable
    public final String getShortName() {
        return this.c;
    }

    @Nullable
    public final String getThumbUrl() {
        return this.k;
    }

    @Nullable
    public final String getTitle() {
        return this.b;
    }

    public final void setBattingStyle(@Nullable String str) {
        this.n = str;
    }

    public final void setBirthdate(@Nullable String str) {
        this.g = str;
    }

    public final void setBirthplace(@Nullable String str) {
        this.h = str;
    }

    public final void setBowlingStyle(@Nullable String str) {
        this.o = str;
    }

    public final void setCountry(@Nullable String str) {
        this.i = str;
    }

    public final void setFantasyPlayerRating(@Nullable Integer num) {
        this.s = num;
    }

    public final void setFieldingPosition(@Nullable String str) {
        this.p = str;
    }

    public final void setFirstName(@Nullable String str) {
        this.d = str;
    }

    public final void setLastName(@Nullable String str) {
        this.e = str;
    }

    public final void setLogoUrl(@Nullable String str) {
        this.l = str;
    }

    public final void setMiddleName(@Nullable String str) {
        this.f = str;
    }

    public final void setNationality(@Nullable String str) {
        this.t = str;
    }

    public final void setPid(@Nullable Integer num) {
        this.f5931a = num;
    }

    public final void setPlayingRole(@Nullable String str) {
        this.m = str;
    }

    public final void setPrimaryTeam(@Nullable List<? extends Object> list) {
        this.j = list;
    }

    public final void setRecentAppearance(@Nullable Integer num) {
        this.r = num;
    }

    public final void setRecentMatch(@Nullable Integer num) {
        this.q = num;
    }

    public final void setRole(@Nullable String str) {
        this.u = str;
    }

    public final void setShortName(@Nullable String str) {
        this.c = str;
    }

    public final void setThumbUrl(@Nullable String str) {
        this.k = str;
    }

    public final void setTitle(@Nullable String str) {
        this.b = str;
    }
}
