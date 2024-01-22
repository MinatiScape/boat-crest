package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class e {
    @JvmField
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineContext f14190a;
    @NotNull
    public final Object[] b;
    @NotNull
    public final ThreadContextElement<Object>[] c;
    public int d;

    public e(@NotNull CoroutineContext coroutineContext, int i) {
        this.f14190a = coroutineContext;
        this.b = new Object[i];
        this.c = new ThreadContextElement[i];
    }

    public final void a(@NotNull ThreadContextElement<?> threadContextElement, @Nullable Object obj) {
        Object[] objArr = this.b;
        int i = this.d;
        objArr[i] = obj;
        ThreadContextElement<Object>[] threadContextElementArr = this.c;
        this.d = i + 1;
        threadContextElementArr[i] = threadContextElement;
    }

    public final void b(@NotNull CoroutineContext coroutineContext) {
        int length = this.c.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i = length - 1;
            ThreadContextElement<Object> threadContextElement = this.c[length];
            Intrinsics.checkNotNull(threadContextElement);
            threadContextElement.restoreThreadContext(coroutineContext, this.b[length]);
            if (i < 0) {
                return;
            }
            length = i;
        }
    }
}
