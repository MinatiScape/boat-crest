package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Sequence<T> f14108a;
    public final int b;
    public final int c;

    /* JADX WARN: Multi-variable type inference failed */
    public SubSequence(@NotNull Sequence<? extends T> sequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.f14108a = sequence;
        this.b = i;
        this.c = i2;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + i).toString());
        }
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + i2).toString());
        }
        if (i2 >= i) {
            return;
        }
        throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + i2 + " < " + i).toString());
    }

    public final int a() {
        return this.c - this.b;
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> drop(int i) {
        return i >= a() ? SequencesKt__SequencesKt.emptySequence() : new SubSequence(this.f14108a, this.b + i, this.c);
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new SubSequence$iterator$1(this);
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> take(int i) {
        if (i >= a()) {
            return this;
        }
        Sequence<T> sequence = this.f14108a;
        int i2 = this.b;
        return new SubSequence(sequence, i2, i + i2);
    }
}
