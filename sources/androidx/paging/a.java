package androidx.paging;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class a<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReentrantLock f1563a = new ReentrantLock();
    @NotNull
    public final MutableStateFlow<LoadStates> b = StateFlowKt.MutableStateFlow(LoadStates.Companion.getIDLE());
    @NotNull
    public final AccessorState<Key, Value> c = new AccessorState<>();

    @NotNull
    public final StateFlow<LoadStates> a() {
        return this.b;
    }

    public final <R> R b(@NotNull Function1<? super AccessorState<Key, Value>, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ReentrantLock reentrantLock = this.f1563a;
        reentrantLock.lock();
        try {
            R invoke = block.invoke(this.c);
            this.b.setValue(this.c.e());
            return invoke;
        } finally {
            reentrantLock.unlock();
        }
    }
}
