package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MatchState {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4791a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;

    public MatchState(@NotNull String bottomState, @NotNull String teamAScore, @NotNull String teamBScore) {
        Intrinsics.checkNotNullParameter(bottomState, "bottomState");
        Intrinsics.checkNotNullParameter(teamAScore, "teamAScore");
        Intrinsics.checkNotNullParameter(teamBScore, "teamBScore");
        this.f4791a = bottomState;
        this.b = teamAScore;
        this.c = teamBScore;
    }

    public static /* synthetic */ MatchState copy$default(MatchState matchState, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = matchState.f4791a;
        }
        if ((i & 2) != 0) {
            str2 = matchState.b;
        }
        if ((i & 4) != 0) {
            str3 = matchState.c;
        }
        return matchState.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.f4791a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final MatchState copy(@NotNull String bottomState, @NotNull String teamAScore, @NotNull String teamBScore) {
        Intrinsics.checkNotNullParameter(bottomState, "bottomState");
        Intrinsics.checkNotNullParameter(teamAScore, "teamAScore");
        Intrinsics.checkNotNullParameter(teamBScore, "teamBScore");
        return new MatchState(bottomState, teamAScore, teamBScore);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MatchState) {
            MatchState matchState = (MatchState) obj;
            return Intrinsics.areEqual(this.f4791a, matchState.f4791a) && Intrinsics.areEqual(this.b, matchState.b) && Intrinsics.areEqual(this.c, matchState.c);
        }
        return false;
    }

    @NotNull
    public final String getBottomState() {
        return this.f4791a;
    }

    @NotNull
    public final String getTeamAScore() {
        return this.b;
    }

    @NotNull
    public final String getTeamBScore() {
        return this.c;
    }

    public int hashCode() {
        return (((this.f4791a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    @NotNull
    public String toString() {
        return "MatchState(bottomState=" + this.f4791a + ", teamAScore=" + this.b + ", teamBScore=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
