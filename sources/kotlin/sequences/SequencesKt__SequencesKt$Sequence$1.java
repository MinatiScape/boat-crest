package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SequencesKt__SequencesKt$Sequence$1 implements Sequence<Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function0<Iterator<Object>> f14100a;

    /* JADX WARN: Multi-variable type inference failed */
    public SequencesKt__SequencesKt$Sequence$1(Function0<? extends Iterator<Object>> function0) {
        this.f14100a = function0;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<Object> iterator() {
        return this.f14100a.invoke();
    }
}
