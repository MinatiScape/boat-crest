package kotlin.text;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface MatchResult {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @NotNull
        public static Destructured getDestructured(@NotNull MatchResult matchResult) {
            return new Destructured(matchResult);
        }
    }

    /* loaded from: classes12.dex */
    public static final class Destructured {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MatchResult f14122a;

        public Destructured(@NotNull MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            this.f14122a = match;
        }

        @NotNull
        public final MatchResult getMatch() {
            return this.f14122a;
        }

        @NotNull
        public final List<String> toList() {
            return this.f14122a.getGroupValues().subList(1, this.f14122a.getGroupValues().size());
        }
    }

    @NotNull
    Destructured getDestructured();

    @NotNull
    List<String> getGroupValues();

    @NotNull
    MatchGroupCollection getGroups();

    @NotNull
    IntRange getRange();

    @NotNull
    String getValue();

    @Nullable
    MatchResult next();
}
