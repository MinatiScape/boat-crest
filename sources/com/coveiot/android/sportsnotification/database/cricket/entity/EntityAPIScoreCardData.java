package com.coveiot.android.sportsnotification.database.cricket.entity;

import androidx.room.Entity;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"match_id"})
/* loaded from: classes7.dex */
public final class EntityAPIScoreCardData {

    /* renamed from: a  reason: collision with root package name */
    public int f5845a;
    @NotNull
    public String b;
    @NotNull
    public String c;
    public long d;
    public long e;
    @NotNull
    public String f;
    @NotNull
    public String g;
    @NotNull
    public String h;
    public int i;
    public int j;
    @NotNull
    public String k;
    public int l;
    @NotNull
    public String m;
    @NotNull
    public String n;
    @NotNull
    public String o;
    @NotNull
    public String p;
    @NotNull
    public String q;
    public int r;
    @NotNull
    public String s;
    @NotNull
    public String t;
    @NotNull
    public String u;
    @NotNull
    public String v;
    @NotNull
    public String w;
    @NotNull
    public String x;
    @NotNull
    public String y;
    @NotNull
    public String z;

    public EntityAPIScoreCardData(int i, @NotNull String match_start_date, @NotNull String match_end_date, long j, long j2, @NotNull String title, @NotNull String subtitle, @NotNull String match_format, int i2, int i3, @NotNull String game_state_str, int i4, @NotNull String team_a_name, @NotNull String team_a_short_name, @NotNull String team_a_current_score, @NotNull String team_a_current_over, @NotNull String team_a_full_score, int i5, @NotNull String team_b_name, @NotNull String team_b_short_name, @NotNull String team_b_current_score, @NotNull String team_b_current_over, @NotNull String team_b_full_score, @NotNull String toss_details, @NotNull String toss_winner, @NotNull String toss_decision) {
        Intrinsics.checkNotNullParameter(match_start_date, "match_start_date");
        Intrinsics.checkNotNullParameter(match_end_date, "match_end_date");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(match_format, "match_format");
        Intrinsics.checkNotNullParameter(game_state_str, "game_state_str");
        Intrinsics.checkNotNullParameter(team_a_name, "team_a_name");
        Intrinsics.checkNotNullParameter(team_a_short_name, "team_a_short_name");
        Intrinsics.checkNotNullParameter(team_a_current_score, "team_a_current_score");
        Intrinsics.checkNotNullParameter(team_a_current_over, "team_a_current_over");
        Intrinsics.checkNotNullParameter(team_a_full_score, "team_a_full_score");
        Intrinsics.checkNotNullParameter(team_b_name, "team_b_name");
        Intrinsics.checkNotNullParameter(team_b_short_name, "team_b_short_name");
        Intrinsics.checkNotNullParameter(team_b_current_score, "team_b_current_score");
        Intrinsics.checkNotNullParameter(team_b_current_over, "team_b_current_over");
        Intrinsics.checkNotNullParameter(team_b_full_score, "team_b_full_score");
        Intrinsics.checkNotNullParameter(toss_details, "toss_details");
        Intrinsics.checkNotNullParameter(toss_winner, "toss_winner");
        Intrinsics.checkNotNullParameter(toss_decision, "toss_decision");
        this.f5845a = i;
        this.b = match_start_date;
        this.c = match_end_date;
        this.d = j;
        this.e = j2;
        this.f = title;
        this.g = subtitle;
        this.h = match_format;
        this.i = i2;
        this.j = i3;
        this.k = game_state_str;
        this.l = i4;
        this.m = team_a_name;
        this.n = team_a_short_name;
        this.o = team_a_current_score;
        this.p = team_a_current_over;
        this.q = team_a_full_score;
        this.r = i5;
        this.s = team_b_name;
        this.t = team_b_short_name;
        this.u = team_b_current_score;
        this.v = team_b_current_over;
        this.w = team_b_full_score;
        this.x = toss_details;
        this.y = toss_winner;
        this.z = toss_decision;
    }

    public final int component1() {
        return this.f5845a;
    }

    public final int component10() {
        return this.j;
    }

    @NotNull
    public final String component11() {
        return this.k;
    }

    public final int component12() {
        return this.l;
    }

    @NotNull
    public final String component13() {
        return this.m;
    }

    @NotNull
    public final String component14() {
        return this.n;
    }

    @NotNull
    public final String component15() {
        return this.o;
    }

    @NotNull
    public final String component16() {
        return this.p;
    }

    @NotNull
    public final String component17() {
        return this.q;
    }

    public final int component18() {
        return this.r;
    }

    @NotNull
    public final String component19() {
        return this.s;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component20() {
        return this.t;
    }

    @NotNull
    public final String component21() {
        return this.u;
    }

    @NotNull
    public final String component22() {
        return this.v;
    }

    @NotNull
    public final String component23() {
        return this.w;
    }

    @NotNull
    public final String component24() {
        return this.x;
    }

    @NotNull
    public final String component25() {
        return this.y;
    }

    @NotNull
    public final String component26() {
        return this.z;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    public final long component4() {
        return this.d;
    }

    public final long component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final String component7() {
        return this.g;
    }

    @NotNull
    public final String component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final EntityAPIScoreCardData copy(int i, @NotNull String match_start_date, @NotNull String match_end_date, long j, long j2, @NotNull String title, @NotNull String subtitle, @NotNull String match_format, int i2, int i3, @NotNull String game_state_str, int i4, @NotNull String team_a_name, @NotNull String team_a_short_name, @NotNull String team_a_current_score, @NotNull String team_a_current_over, @NotNull String team_a_full_score, int i5, @NotNull String team_b_name, @NotNull String team_b_short_name, @NotNull String team_b_current_score, @NotNull String team_b_current_over, @NotNull String team_b_full_score, @NotNull String toss_details, @NotNull String toss_winner, @NotNull String toss_decision) {
        Intrinsics.checkNotNullParameter(match_start_date, "match_start_date");
        Intrinsics.checkNotNullParameter(match_end_date, "match_end_date");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(match_format, "match_format");
        Intrinsics.checkNotNullParameter(game_state_str, "game_state_str");
        Intrinsics.checkNotNullParameter(team_a_name, "team_a_name");
        Intrinsics.checkNotNullParameter(team_a_short_name, "team_a_short_name");
        Intrinsics.checkNotNullParameter(team_a_current_score, "team_a_current_score");
        Intrinsics.checkNotNullParameter(team_a_current_over, "team_a_current_over");
        Intrinsics.checkNotNullParameter(team_a_full_score, "team_a_full_score");
        Intrinsics.checkNotNullParameter(team_b_name, "team_b_name");
        Intrinsics.checkNotNullParameter(team_b_short_name, "team_b_short_name");
        Intrinsics.checkNotNullParameter(team_b_current_score, "team_b_current_score");
        Intrinsics.checkNotNullParameter(team_b_current_over, "team_b_current_over");
        Intrinsics.checkNotNullParameter(team_b_full_score, "team_b_full_score");
        Intrinsics.checkNotNullParameter(toss_details, "toss_details");
        Intrinsics.checkNotNullParameter(toss_winner, "toss_winner");
        Intrinsics.checkNotNullParameter(toss_decision, "toss_decision");
        return new EntityAPIScoreCardData(i, match_start_date, match_end_date, j, j2, title, subtitle, match_format, i2, i3, game_state_str, i4, team_a_name, team_a_short_name, team_a_current_score, team_a_current_over, team_a_full_score, i5, team_b_name, team_b_short_name, team_b_current_score, team_b_current_over, team_b_full_score, toss_details, toss_winner, toss_decision);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityAPIScoreCardData) {
            EntityAPIScoreCardData entityAPIScoreCardData = (EntityAPIScoreCardData) obj;
            return this.f5845a == entityAPIScoreCardData.f5845a && Intrinsics.areEqual(this.b, entityAPIScoreCardData.b) && Intrinsics.areEqual(this.c, entityAPIScoreCardData.c) && this.d == entityAPIScoreCardData.d && this.e == entityAPIScoreCardData.e && Intrinsics.areEqual(this.f, entityAPIScoreCardData.f) && Intrinsics.areEqual(this.g, entityAPIScoreCardData.g) && Intrinsics.areEqual(this.h, entityAPIScoreCardData.h) && this.i == entityAPIScoreCardData.i && this.j == entityAPIScoreCardData.j && Intrinsics.areEqual(this.k, entityAPIScoreCardData.k) && this.l == entityAPIScoreCardData.l && Intrinsics.areEqual(this.m, entityAPIScoreCardData.m) && Intrinsics.areEqual(this.n, entityAPIScoreCardData.n) && Intrinsics.areEqual(this.o, entityAPIScoreCardData.o) && Intrinsics.areEqual(this.p, entityAPIScoreCardData.p) && Intrinsics.areEqual(this.q, entityAPIScoreCardData.q) && this.r == entityAPIScoreCardData.r && Intrinsics.areEqual(this.s, entityAPIScoreCardData.s) && Intrinsics.areEqual(this.t, entityAPIScoreCardData.t) && Intrinsics.areEqual(this.u, entityAPIScoreCardData.u) && Intrinsics.areEqual(this.v, entityAPIScoreCardData.v) && Intrinsics.areEqual(this.w, entityAPIScoreCardData.w) && Intrinsics.areEqual(this.x, entityAPIScoreCardData.x) && Intrinsics.areEqual(this.y, entityAPIScoreCardData.y) && Intrinsics.areEqual(this.z, entityAPIScoreCardData.z);
        }
        return false;
    }

    public final int getGame_state_id() {
        return this.j;
    }

    @NotNull
    public final String getGame_state_str() {
        return this.k;
    }

    @NotNull
    public final String getMatch_end_date() {
        return this.c;
    }

    public final long getMatch_end_timeStamp() {
        return this.e;
    }

    @NotNull
    public final String getMatch_format() {
        return this.h;
    }

    public final int getMatch_id() {
        return this.f5845a;
    }

    @NotNull
    public final String getMatch_start_date() {
        return this.b;
    }

    public final long getMatch_start_timeStamp() {
        return this.d;
    }

    public final int getMatch_status_id() {
        return this.i;
    }

    @NotNull
    public final String getSubtitle() {
        return this.g;
    }

    @NotNull
    public final String getTeam_a_current_over() {
        return this.p;
    }

    @NotNull
    public final String getTeam_a_current_score() {
        return this.o;
    }

    @NotNull
    public final String getTeam_a_full_score() {
        return this.q;
    }

    public final int getTeam_a_id() {
        return this.l;
    }

    @NotNull
    public final String getTeam_a_name() {
        return this.m;
    }

    @NotNull
    public final String getTeam_a_short_name() {
        return this.n;
    }

    @NotNull
    public final String getTeam_b_current_over() {
        return this.v;
    }

    @NotNull
    public final String getTeam_b_current_score() {
        return this.u;
    }

    @NotNull
    public final String getTeam_b_full_score() {
        return this.w;
    }

    public final int getTeam_b_id() {
        return this.r;
    }

    @NotNull
    public final String getTeam_b_name() {
        return this.s;
    }

    @NotNull
    public final String getTeam_b_short_name() {
        return this.t;
    }

    @NotNull
    public final String getTitle() {
        return this.f;
    }

    @NotNull
    public final String getToss_decision() {
        return this.z;
    }

    @NotNull
    public final String getToss_details() {
        return this.x;
    }

    @NotNull
    public final String getToss_winner() {
        return this.y;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.f5845a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Long.hashCode(this.d)) * 31) + Long.hashCode(this.e)) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + Integer.hashCode(this.i)) * 31) + Integer.hashCode(this.j)) * 31) + this.k.hashCode()) * 31) + Integer.hashCode(this.l)) * 31) + this.m.hashCode()) * 31) + this.n.hashCode()) * 31) + this.o.hashCode()) * 31) + this.p.hashCode()) * 31) + this.q.hashCode()) * 31) + Integer.hashCode(this.r)) * 31) + this.s.hashCode()) * 31) + this.t.hashCode()) * 31) + this.u.hashCode()) * 31) + this.v.hashCode()) * 31) + this.w.hashCode()) * 31) + this.x.hashCode()) * 31) + this.y.hashCode()) * 31) + this.z.hashCode();
    }

    public final void setGame_state_id(int i) {
        this.j = i;
    }

    public final void setGame_state_str(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.k = str;
    }

    public final void setMatch_end_date(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setMatch_end_timeStamp(long j) {
        this.e = j;
    }

    public final void setMatch_format(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.h = str;
    }

    public final void setMatch_id(int i) {
        this.f5845a = i;
    }

    public final void setMatch_start_date(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setMatch_start_timeStamp(long j) {
        this.d = j;
    }

    public final void setMatch_status_id(int i) {
        this.i = i;
    }

    public final void setSubtitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setTeam_a_current_over(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.p = str;
    }

    public final void setTeam_a_current_score(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.o = str;
    }

    public final void setTeam_a_full_score(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.q = str;
    }

    public final void setTeam_a_id(int i) {
        this.l = i;
    }

    public final void setTeam_a_name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.m = str;
    }

    public final void setTeam_a_short_name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.n = str;
    }

    public final void setTeam_b_current_over(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.v = str;
    }

    public final void setTeam_b_current_score(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.u = str;
    }

    public final void setTeam_b_full_score(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.w = str;
    }

    public final void setTeam_b_id(int i) {
        this.r = i;
    }

    public final void setTeam_b_name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.s = str;
    }

    public final void setTeam_b_short_name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.t = str;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setToss_decision(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.z = str;
    }

    public final void setToss_details(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.x = str;
    }

    public final void setToss_winner(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }

    @NotNull
    public String toString() {
        return "EntityAPIScoreCardData(match_id=" + this.f5845a + ", match_start_date=" + this.b + ", match_end_date=" + this.c + ", match_start_timeStamp=" + this.d + ", match_end_timeStamp=" + this.e + ", title=" + this.f + ", subtitle=" + this.g + ", match_format=" + this.h + ", match_status_id=" + this.i + ", game_state_id=" + this.j + ", game_state_str=" + this.k + ", team_a_id=" + this.l + ", team_a_name=" + this.m + ", team_a_short_name=" + this.n + ", team_a_current_score=" + this.o + ", team_a_current_over=" + this.p + ", team_a_full_score=" + this.q + ", team_b_id=" + this.r + ", team_b_name=" + this.s + ", team_b_short_name=" + this.t + ", team_b_current_score=" + this.u + ", team_b_current_over=" + this.v + ", team_b_full_score=" + this.w + ", toss_details=" + this.x + ", toss_winner=" + this.y + ", toss_decision=" + this.z + HexStringBuilder.COMMENT_END_CHAR;
    }
}
