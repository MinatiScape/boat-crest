package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/*  JADX ERROR: JadxRuntimeException in pass: ClassModifier
    jadx.core.utils.exceptions.JadxRuntimeException: Not class type: E
    	at jadx.core.dex.info.ClassInfo.checkClassType(ClassInfo.java:53)
    	at jadx.core.dex.info.ClassInfo.fromType(ClassInfo.java:31)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:83)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:61)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:55)
    */
/* loaded from: classes12.dex */
public final class OnUndeliveredElementKt {

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CoroutineContext $context;
        public final /* synthetic */ E $element;
        public final /* synthetic */ Function1<E, Unit> $this_bindCancellationFun;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Function1<? super E, Unit> function1, E e, CoroutineContext coroutineContext) {
            super(1);
            this.$this_bindCancellationFun = function1;
            this.$element = e;
            this.$context = coroutineContext;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Throwable th) {
            OnUndeliveredElementKt.callUndeliveredElement(this.$this_bindCancellationFun, this.$element, this.$context);
        }
    }

    @NotNull
    public static final <E> Function1<Throwable, Unit> bindCancellationFun(@NotNull Function1<? super E, Unit> function1, E e, @NotNull CoroutineContext coroutineContext) {
        return new a(function1, e, coroutineContext);
    }

    public static final <E> void callUndeliveredElement(@NotNull Function1<? super E, Unit> function1, E e, @NotNull CoroutineContext coroutineContext) {
        UndeliveredElementException callUndeliveredElementCatchingException = callUndeliveredElementCatchingException(function1, e, null);
        if (callUndeliveredElementCatchingException == null) {
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, callUndeliveredElementCatchingException);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <E> UndeliveredElementException callUndeliveredElementCatchingException(@NotNull Function1<? super E, Unit> function1, E e, @Nullable UndeliveredElementException undeliveredElementException) {
        try {
            function1.invoke(e);
        } catch (Throwable th) {
            if (undeliveredElementException != null && undeliveredElementException.getCause() != th) {
                kotlin.a.addSuppressed(undeliveredElementException, th);
            } else {
                return new UndeliveredElementException(Intrinsics.stringPlus("Exception in undelivered element handler for ", e), th);
            }
        }
        return undeliveredElementException;
    }

    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i, Object obj2) {
        if ((i & 2) != 0) {
            undeliveredElementException = null;
        }
        return callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
    }
}
