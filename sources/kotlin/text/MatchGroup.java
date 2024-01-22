package kotlin.text;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MatchGroup {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14121a;
    @NotNull
    public final IntRange b;

    public MatchGroup(@NotNull String value, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(range, "range");
        this.f14121a = value;
        this.b = range;
    }

    public static /* synthetic */ MatchGroup copy$default(MatchGroup matchGroup, String str, IntRange intRange, int i, Object obj) {
        if ((i & 1) != 0) {
            str = matchGroup.f14121a;
        }
        if ((i & 2) != 0) {
            intRange = matchGroup.b;
        }
        return matchGroup.copy(str, intRange);
    }

    @NotNull
    public final String component1() {
        return this.f14121a;
    }

    @NotNull
    public final IntRange component2() {
        return this.b;
    }

    @NotNull
    public final MatchGroup copy(@NotNull String value, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(range, "range");
        return new MatchGroup(value, range);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MatchGroup) {
            MatchGroup matchGroup = (MatchGroup) obj;
            return Intrinsics.areEqual(this.f14121a, matchGroup.f14121a) && Intrinsics.areEqual(this.b, matchGroup.b);
        }
        return false;
    }

    @NotNull
    public final IntRange getRange() {
        return this.b;
    }

    @NotNull
    public final String getValue() {
        return this.f14121a;
    }

    public int hashCode() {
        return (this.f14121a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "MatchGroup(value=" + this.f14121a + ", range=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
