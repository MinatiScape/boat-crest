package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CoroutineContextKt {

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
        public static final a INSTANCE = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
            if (element instanceof CopyableThreadContextElement) {
                return coroutineContext.plus(((CopyableThreadContextElement) element).copyForChild());
            }
            return coroutineContext.plus(element);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
        public final /* synthetic */ boolean $isNewCoroutine;
        public final /* synthetic */ Ref.ObjectRef<CoroutineContext> $leftoverContext;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Ref.ObjectRef<CoroutineContext> objectRef, boolean z) {
            super(2);
            this.$leftoverContext = objectRef;
            this.$isNewCoroutine = z;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [T, kotlin.coroutines.CoroutineContext] */
        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
            if (element instanceof CopyableThreadContextElement) {
                CoroutineContext.Element element2 = this.$leftoverContext.element.get(element.getKey());
                if (element2 == null) {
                    CopyableThreadContextElement copyableThreadContextElement = (CopyableThreadContextElement) element;
                    if (this.$isNewCoroutine) {
                        copyableThreadContextElement = copyableThreadContextElement.copyForChild();
                    }
                    return coroutineContext.plus(copyableThreadContextElement);
                }
                Ref.ObjectRef<CoroutineContext> objectRef = this.$leftoverContext;
                objectRef.element = objectRef.element.minusKey(element.getKey());
                return coroutineContext.plus(((CopyableThreadContextElement) element).mergeForChild(element2));
            }
            return coroutineContext.plus(element);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function2<Boolean, CoroutineContext.Element, Boolean> {
        public static final c INSTANCE = new c();

        public c() {
            super(2);
        }

        @NotNull
        public final Boolean invoke(boolean z, @NotNull CoroutineContext.Element element) {
            return Boolean.valueOf(z || (element instanceof CopyableThreadContextElement));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool, CoroutineContext.Element element) {
            return invoke(bool.booleanValue(), element);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    public static final CoroutineContext a(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, boolean z) {
        boolean b2 = b(coroutineContext);
        boolean b3 = b(coroutineContext2);
        if (!b2 && !b3) {
            return coroutineContext.plus(coroutineContext2);
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = coroutineContext2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(emptyCoroutineContext, new b(objectRef, z));
        if (b3) {
            objectRef.element = ((CoroutineContext) objectRef.element).fold(emptyCoroutineContext, a.INSTANCE);
        }
        return coroutineContext3.plus((CoroutineContext) objectRef.element);
    }

    public static final boolean b(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.fold(Boolean.FALSE, c.INSTANCE)).booleanValue();
    }

    @Nullable
    public static final String getCoroutineName(@NotNull CoroutineContext coroutineContext) {
        CoroutineId coroutineId;
        String name;
        if (DebugKt.getDEBUG() && (coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key)) != null) {
            CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
            String str = "coroutine";
            if (coroutineName != null && (name = coroutineName.getName()) != null) {
                str = name;
            }
            return str + '#' + coroutineId.getId();
        }
        return null;
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        CoroutineContext a2 = a(coroutineScope.getCoroutineContext(), coroutineContext, true);
        CoroutineContext plus = DebugKt.getDEBUG() ? a2.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) : a2;
        return (a2 == Dispatchers.getDefault() || a2.get(ContinuationInterceptor.Key) != null) ? plus : plus.plus(Dispatchers.getDefault());
    }

    @Nullable
    public static final UndispatchedCoroutine<?> undispatchedCompletion(@NotNull CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    @Nullable
    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(@NotNull Continuation<?> continuation, @NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (continuation instanceof CoroutineStackFrame) {
            if (coroutineContext.get(j0.h) != null) {
                UndispatchedCoroutine<?> undispatchedCompletion = undispatchedCompletion((CoroutineStackFrame) continuation);
                if (undispatchedCompletion != null) {
                    undispatchedCompletion.saveThreadContext(coroutineContext, obj);
                }
                return undispatchedCompletion;
            }
            return null;
        }
        return null;
    }

    public static final <T> T withContinuationContext(@NotNull Continuation<?> continuation, @Nullable Object obj, @NotNull Function0<? extends T> function0) {
        CoroutineContext context = continuation.getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
        UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withCoroutineContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj, @NotNull Function0<? extends T> function0) {
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    @InternalCoroutinesApi
    @NotNull
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2) {
        return !b(coroutineContext2) ? coroutineContext.plus(coroutineContext2) : a(coroutineContext, coroutineContext2, false);
    }
}
