package com.coveiot.android.sportsnotification.utils;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SoccerSummary {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Integer[] f5891a;
    @NotNull
    public final Integer[] b;
    @NotNull
    public final Integer[] c;
    @NotNull
    public final Integer[] d;
    @NotNull
    public final Integer[] e;

    public SoccerSummary(@NotNull Integer[] shotsOnTarget, @NotNull Integer[] possession, @NotNull Integer[] yellowCards, @NotNull Integer[] redCards, @NotNull Integer[] fouls) {
        Intrinsics.checkNotNullParameter(shotsOnTarget, "shotsOnTarget");
        Intrinsics.checkNotNullParameter(possession, "possession");
        Intrinsics.checkNotNullParameter(yellowCards, "yellowCards");
        Intrinsics.checkNotNullParameter(redCards, "redCards");
        Intrinsics.checkNotNullParameter(fouls, "fouls");
        this.f5891a = shotsOnTarget;
        this.b = possession;
        this.c = yellowCards;
        this.d = redCards;
        this.e = fouls;
    }

    public static /* synthetic */ SoccerSummary copy$default(SoccerSummary soccerSummary, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, Integer[] numArr4, Integer[] numArr5, int i, Object obj) {
        if ((i & 1) != 0) {
            numArr = soccerSummary.f5891a;
        }
        if ((i & 2) != 0) {
            numArr2 = soccerSummary.b;
        }
        Integer[] numArr6 = numArr2;
        if ((i & 4) != 0) {
            numArr3 = soccerSummary.c;
        }
        Integer[] numArr7 = numArr3;
        if ((i & 8) != 0) {
            numArr4 = soccerSummary.d;
        }
        Integer[] numArr8 = numArr4;
        if ((i & 16) != 0) {
            numArr5 = soccerSummary.e;
        }
        return soccerSummary.copy(numArr, numArr6, numArr7, numArr8, numArr5);
    }

    @NotNull
    public final Integer[] component1() {
        return this.f5891a;
    }

    @NotNull
    public final Integer[] component2() {
        return this.b;
    }

    @NotNull
    public final Integer[] component3() {
        return this.c;
    }

    @NotNull
    public final Integer[] component4() {
        return this.d;
    }

    @NotNull
    public final Integer[] component5() {
        return this.e;
    }

    @NotNull
    public final SoccerSummary copy(@NotNull Integer[] shotsOnTarget, @NotNull Integer[] possession, @NotNull Integer[] yellowCards, @NotNull Integer[] redCards, @NotNull Integer[] fouls) {
        Intrinsics.checkNotNullParameter(shotsOnTarget, "shotsOnTarget");
        Intrinsics.checkNotNullParameter(possession, "possession");
        Intrinsics.checkNotNullParameter(yellowCards, "yellowCards");
        Intrinsics.checkNotNullParameter(redCards, "redCards");
        Intrinsics.checkNotNullParameter(fouls, "fouls");
        return new SoccerSummary(shotsOnTarget, possession, yellowCards, redCards, fouls);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(SoccerSummary.class, obj != null ? obj.getClass() : null)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.sportsnotification.utils.SoccerSummary");
            SoccerSummary soccerSummary = (SoccerSummary) obj;
            return Arrays.equals(this.f5891a, soccerSummary.f5891a) && Arrays.equals(this.b, soccerSummary.b) && Arrays.equals(this.c, soccerSummary.c) && Arrays.equals(this.d, soccerSummary.d) && Arrays.equals(this.e, soccerSummary.e);
        }
        return false;
    }

    @NotNull
    public final Integer[] getFouls() {
        return this.e;
    }

    @NotNull
    public final Integer[] getPossession() {
        return this.b;
    }

    @NotNull
    public final Integer[] getRedCards() {
        return this.d;
    }

    @NotNull
    public final Integer[] getShotsOnTarget() {
        return this.f5891a;
    }

    @NotNull
    public final Integer[] getYellowCards() {
        return this.c;
    }

    public int hashCode() {
        return (((((((Arrays.hashCode(this.f5891a) * 31) + Arrays.hashCode(this.b)) * 31) + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.hashCode(this.e);
    }

    @NotNull
    public String toString() {
        return "SoccerSummary(shotsOnTarget=" + Arrays.toString(this.f5891a) + ", possession=" + Arrays.toString(this.b) + ", yellowCards=" + Arrays.toString(this.c) + ", redCards=" + Arrays.toString(this.d) + ", fouls=" + Arrays.toString(this.e) + HexStringBuilder.COMMENT_END_CHAR;
    }
}
