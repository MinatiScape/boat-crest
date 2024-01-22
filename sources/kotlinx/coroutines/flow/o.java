package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.BufferOverflow;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class o<T> {
    @JvmField
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Flow<T> f14163a;
    @JvmField
    public final int b;
    @JvmField
    @NotNull
    public final BufferOverflow c;
    @JvmField
    @NotNull
    public final CoroutineContext d;

    /* JADX WARN: Multi-variable type inference failed */
    public o(@NotNull Flow<? extends T> flow, int i, @NotNull BufferOverflow bufferOverflow, @NotNull CoroutineContext coroutineContext) {
        this.f14163a = flow;
        this.b = i;
        this.c = bufferOverflow;
        this.d = coroutineContext;
    }
}
