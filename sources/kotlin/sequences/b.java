package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.EmptyIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class b implements Sequence, DropTakeSequence {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final b f14113a = new b();

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    /* renamed from: a */
    public b drop(int i) {
        return f14113a;
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    /* renamed from: b */
    public b take(int i) {
        return f14113a;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }
}
