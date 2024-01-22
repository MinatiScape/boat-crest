package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ThreadContextKt {
    @JvmField
    @NotNull
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Function2<Object, CoroutineContext.Element, Object> f14186a = a.INSTANCE;
    @NotNull
    public static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> b = b.INSTANCE;
    @NotNull
    public static final Function2<e, CoroutineContext.Element, e> c = c.INSTANCE;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function2<Object, CoroutineContext.Element, Object> {
        public static final a INSTANCE = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@Nullable Object obj, @NotNull CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int intValue = num == null ? 1 : num.intValue();
                return intValue == 0 ? element : Integer.valueOf(intValue + 1);
            }
            return obj;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> {
        public static final b INSTANCE = new b();

        public b() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final ThreadContextElement<?> invoke(@Nullable ThreadContextElement<?> threadContextElement, @NotNull CoroutineContext.Element element) {
            if (threadContextElement != null) {
                return threadContextElement;
            }
            if (element instanceof ThreadContextElement) {
                return (ThreadContextElement) element;
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function2<e, CoroutineContext.Element, e> {
        public static final c INSTANCE = new c();

        public c() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final e invoke(@NotNull e eVar, @NotNull CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                ThreadContextElement<?> threadContextElement = (ThreadContextElement) element;
                eVar.a(threadContextElement, threadContextElement.updateThreadContext(eVar.f14190a));
            }
            return eVar;
        }
    }

    public static final void restoreThreadContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (obj == NO_THREAD_ELEMENTS) {
            return;
        }
        if (obj instanceof e) {
            ((e) obj).b(coroutineContext);
            return;
        }
        Object fold = coroutineContext.fold(null, b);
        Objects.requireNonNull(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        ((ThreadContextElement) fold).restoreThreadContext(coroutineContext, obj);
    }

    @NotNull
    public static final Object threadContextElements(@NotNull CoroutineContext coroutineContext) {
        Object fold = coroutineContext.fold(0, f14186a);
        Intrinsics.checkNotNull(fold);
        return fold;
    }

    @Nullable
    public static final Object updateThreadContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (obj == null) {
            obj = threadContextElements(coroutineContext);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new e(coroutineContext, ((Number) obj).intValue()), c);
        }
        return ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }
}
