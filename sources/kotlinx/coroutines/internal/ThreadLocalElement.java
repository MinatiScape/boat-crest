package kotlinx.coroutines.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ThreadLocalElement<T> implements ThreadContextElement<T> {
    public final T h;
    @NotNull
    public final ThreadLocal<T> i;
    @NotNull
    public final CoroutineContext.Key<?> j;

    public ThreadLocalElement(T t, @NotNull ThreadLocal<T> threadLocal) {
        this.h = t;
        this.i = threadLocal;
        this.j = new ThreadLocalKey(threadLocal);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) ThreadContextElement.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        if (Intrinsics.areEqual(getKey(), key)) {
            return this;
        }
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    @NotNull
    public CoroutineContext.Key<?> getKey() {
        return this.j;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return Intrinsics.areEqual(getKey(), key) ? EmptyCoroutineContext.INSTANCE : this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        return ThreadContextElement.DefaultImpls.plus(this, coroutineContext);
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public void restoreThreadContext(@NotNull CoroutineContext coroutineContext, T t) {
        this.i.set(t);
    }

    @NotNull
    public String toString() {
        return "ThreadLocal(value=" + this.h + ", threadLocal = " + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public T updateThreadContext(@NotNull CoroutineContext coroutineContext) {
        T t = this.i.get();
        this.i.set(this.h);
        return t;
    }
}
