package kotlin.text;

import java.util.Iterator;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DelimitedRangesSequence implements Sequence<IntRange> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CharSequence f14120a;
    public final int b;
    public final int c;
    @NotNull
    public final Function2<CharSequence, Integer, Pair<Integer, Integer>> d;

    /* JADX WARN: Multi-variable type inference failed */
    public DelimitedRangesSequence(@NotNull CharSequence input, int i, int i2, @NotNull Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> getNextMatch) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(getNextMatch, "getNextMatch");
        this.f14120a = input;
        this.b = i;
        this.c = i2;
        this.d = getNextMatch;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<IntRange> iterator() {
        return new DelimitedRangesSequence$iterator$1(this);
    }
}
