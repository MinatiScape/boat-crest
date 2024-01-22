package kotlin.sequences;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface DropTakeSequence<T> extends Sequence<T> {
    @NotNull
    Sequence<T> drop(int i);

    @NotNull
    Sequence<T> take(int i);
}
